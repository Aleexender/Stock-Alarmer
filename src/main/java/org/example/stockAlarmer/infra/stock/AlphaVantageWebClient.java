package org.example.stockAlarmer.infra.stock;

import io.netty.channel.ChannelOption;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
@EnableConfigurationProperties(AlphaVantageProperties.class)
 class AlphaVantageWebClient{
    private static final int MAX_BUFFER_SIZE = 10 * 1024 * 1024;

    protected AlphaVantageWebClient() {

    }

   @Bean
   public WebClient createWebClient(AlphaVantageProperties config) {
       HttpClient httpClient = HttpClient.create()
               .resolver(DefaultAddressResolverGroup.INSTANCE)
               .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
               .followRedirect(true)
               .compress(true);

       return WebClient.builder()
               .baseUrl(config.getBaseurl())
               .clientConnector(new ReactorClientHttpConnector(httpClient))
               .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(MAX_BUFFER_SIZE))
               .build();
   }

}
