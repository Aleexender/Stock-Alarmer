package org.example.stockAlarmer.infra.stock;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.example.stockAlarmer.global.date.DateFormatter;
import org.example.stockAlarmer.module.stock.domain.Stock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


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
    void whenFetchInfo_thenReturnsCorrectStockList()  {
        Flux<Stock> stocks = alphaVantageStockApi.fetchInfo();

        StepVerifier.create(stocks)
                // 각 Stock 객체의 값을 출력하면서 검증
                .consumeNextWith(stock -> {
                    assertThat(stock.getSymbol()).isEqualTo("A");
                    assertThat(stock.getName()).isEqualTo("Agilent Technologies Inc");
                    assertThat(stock.getExchange()).isEqualTo("NYSE");
                })
                .consumeNextWith(stock -> {
                    assertThat(stock.getSymbol()).isEqualTo("AA");
                    assertThat(stock.getName()).isEqualTo("Alcoa Corp");
                    assertThat(stock.getExchange()).isEqualTo("NYSE");
                })
                .consumeNextWith(stock -> {
                    assertThat(stock.getSymbol()).isEqualTo("AAA");
                    assertThat(stock.getName().trim()).isEqualTo("ALTERNATIVE ACCESS FIRST PRIORITY CLO BOND ETF");
                    assertThat(stock.getExchange()).isEqualTo("NYSE ARCA");
                })
                .verifyComplete();
    }

    @AfterEach
    void tearDown() throws Exception {
        mockWebServer.shutdown();
    }
}
