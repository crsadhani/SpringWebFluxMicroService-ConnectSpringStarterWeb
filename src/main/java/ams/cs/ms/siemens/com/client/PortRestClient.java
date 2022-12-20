package ams.cs.ms.siemens.com.client;

import ams.cs.ms.siemens.com.entity.Port;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class PortRestClient {
    private WebClient webClient;
    @Value("${restClient.portInfoUrl}")
    private String portInfoUrl;

    public PortRestClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<Port> returnPorts(Long portId){
        return webClient.get()
                .uri(portInfoUrl)
                .retrieve().bodyToFlux(Port.class);

    }

}
