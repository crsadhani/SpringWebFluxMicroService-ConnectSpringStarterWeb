package ams.cs.ms.siemens.com.controller;

import ams.cs.ms.siemens.com.client.DeviceRestClient;
import ams.cs.ms.siemens.com.client.PortRestClient;
import ams.cs.ms.siemens.com.entity.Device;
import ams.cs.ms.siemens.com.entity.Port;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(path = "/sinecams/ms/networks")
public class DeviceController {
    Logger logger = LoggerFactory.getLogger(DeviceController.class);
    public static final String SERVICE_ONE_URL = "http://localhost:8081/sinecams/networks/";

    @Autowired
    private WebClient.Builder webClient;

    @Autowired
    private DeviceRestClient deviceRestClient;
    private PortRestClient portRestClient;

    public DeviceController(DeviceRestClient deviceRestClient, PortRestClient portRestClient) {
        this.deviceRestClient = deviceRestClient;
        this.portRestClient = portRestClient;
    }

    @GetMapping
    public Mono<List<Device>> getDevices() {
        return webClient.build()
                .get()
                .uri(SERVICE_ONE_URL)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Device>>() {
                }).log();
    }

    @PostMapping
    public Flux<String> createDevice(@RequestBody Device device) {

            return webClient.build()
                    .post()
                    .uri(SERVICE_ONE_URL)
                    .body(Mono.just(device), Device.class)
                    .retrieve()
                    .bodyToFlux(String.class).log();
        }

        @DeleteMapping("/{id}")
        public Mono<String> deleteDeviceById(@PathVariable("id") Long id){
        return webClient.build().
                     delete()
                    .uri(SERVICE_ONE_URL + id)
                    .retrieve()
                    .bodyToMono(String.class).log();
        }

        @PutMapping("/update")
    public Mono<Device> update(@RequestBody Device d)
    {
        return webClient.build().
                 put()
                .uri( SERVICE_ONE_URL + "/update")
                .body(Mono.just(d), Device.class)
                .retrieve()
                .bodyToMono(Device.class).log();
    }

        @GetMapping("/{id}")
        public Mono<Port> retrieveDeviceById(@PathVariable("id") Long deviceId) {
        return deviceRestClient.returnDeviceId(deviceId)
                .flatMap(portInfo -> {
                    var portListIsMono = portRestClient.returnPorts(deviceId)
                            .collectList();

                    return portListIsMono.map(
                            devices -> new Port());

                });

    }

    }


