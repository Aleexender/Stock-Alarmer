package org.example.stockAlarmer.infra.stock.web;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.example.stockAlarmer.global.date.DateFormatter;
import org.example.stockAlarmer.infra.stock.ResponseConverter;
import org.example.stockAlarmer.module.stock.domain.Stock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlphaVantageStockApiTest {

    @TestConfiguration
    static class TestConfig {
        @Bean
        public MockWebServer mockWebServer() {
            return new MockWebServer();
        }
    }

    @Autowired
    private MockWebServer mockWebServer;

    @Autowired
    private DateFormatter dateFormatter;

    @Autowired
    private ResponseConverter responseConverter;

    private AlphaVantageStockApi alphaVantageStockApi;

    private static final String MOCK_LISTING_STATUS_RESPONSE = """
            symbol,name,exchange,assetType,ipoDate,delistingDate,status
            A,Agilent Technologies Inc,NYSE,Stock,1999-11-18,null,Active
            AA,Alcoa Corp,NYSE,Stock,2016-10-18,null,Active
            AAA,ALTERNATIVE ACCESS FIRST PRIORITY CLO BOND ETF ,NYSE ARCA,ETF,2020-09-09,null,Active
            """;

    @BeforeEach
    void setUp() throws Exception {
        mockWebServer.start();

        AlphaVantageProperties testConfig = new AlphaVantageProperties("demo",mockWebServer.url("").toString().replaceAll("/$", ""));

        // 단순한 WebClient 설정
        WebClient webClient = WebClient.builder()
                .baseUrl(testConfig.getBaseurl())
                .build();

        alphaVantageStockApi = new AlphaVantageStockApi(
                webClient,
                testConfig,
                dateFormatter,
                responseConverter
        );

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(MOCK_LISTING_STATUS_RESPONSE));
    }

    @Test
    void whenFetchInfo_thenReturnsCorrectStockList() throws InterruptedException {
        // API 호출
        List<Stock> stocks = alphaVantageStockApi.fetchInfo();

        // 요청 검증
        RecordedRequest recordedRequest = mockWebServer.takeRequest();

        // 기대하는 쿼리 파라미터들이 모두 포함되어 있는지 검증
        String path = recordedRequest.getPath();
        assertThat(path)
                .contains("function=LISTING_STATUS")
                .contains("apikey=demo")
                .contains("state=active")
                .contains("date=")  // 날짜는 동적으로 변하므로 존재 여부만 확인
                .startsWith("/query?");  // 기본 경로 확인

        assertThat(stocks)
                .isNotEmpty()
                .hasSize(3);

        Stock firstStock = stocks.get(0);
        assertThat(firstStock)
                .satisfies(stock -> {
                    assertThat(stock.getSymbol()).isEqualTo("A");
                    assertThat(stock.getName()).isEqualTo("Agilent Technologies Inc");
                    assertThat(stock.getExchange()).isEqualTo("NYSE");
                });
    }

    @AfterEach
    void tearDown() throws Exception {
        mockWebServer.shutdown();
    }
}
