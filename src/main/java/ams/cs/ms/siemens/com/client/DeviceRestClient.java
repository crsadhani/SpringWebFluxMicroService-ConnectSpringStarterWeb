package ams.cs.ms.siemens.com.client;

import ams.cs.ms.siemens.com.entity.Device;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class DeviceRestClient {

    private WebClient webClient;

    @Value("${restClient.deviceInfoUrl}")
    private String deviceInfoUrl;

    public DeviceRestClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<Device> returnDevices(Long groupId){
        return webClient.get()
                .uri(deviceInfoUrl)
                .retrieve().bodyToFlux(Device.class);

    }

    public Mono<Device> returnDeviceId(Long deviceId) {
        var url = deviceInfoUrl.concat("/{id}");

        return webClient.get()
                .uri(url, deviceId)
                .retrieve()
                .bodyToMono(Device.class).log();
    }

}


