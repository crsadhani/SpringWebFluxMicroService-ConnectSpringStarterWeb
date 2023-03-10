package ams.cs.ms.siemens.com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Configurations {

    /**
     * Prepare rest template object with time out configurations.
     *
     * @return RestTemplate
     */
    @Bean
    public RestTemplate getRestTemplate() {

        HttpComponentsClientHttpRequestFactory clientRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientRequestFactory.setConnectTimeout(5000);
        clientRequestFactory.setReadTimeout(5000);

        return new RestTemplate(clientRequestFactory);
    }
    @Bean
    public WebClient getWebClientBuilder(WebClient.Builder builder){

        return builder.build();
    }

}