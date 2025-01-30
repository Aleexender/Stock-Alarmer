package org.example.stockAlarmer.infra.stock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class AlphaVantagePropertiesTest {
    @Autowired
    private AlphaVantageProperties alphaVantageConfig;

    @Test
    void whenPropertiesAreProvided_thenBindingIsCorrect() {
        assertNotNull(alphaVantageConfig.getApiKey());
        assertNotNull(alphaVantageConfig.getBaseurl());

        assertEquals("demo", alphaVantageConfig.getApiKey());
        assertEquals("http://localhost:8888", alphaVantageConfig.getBaseurl());
    }

}
