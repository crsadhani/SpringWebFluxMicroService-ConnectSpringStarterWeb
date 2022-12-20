package ams.cs.ms.siemens.com.controller;

import ams.cs.ms.siemens.com.client.DeviceRestClient;
import ams.cs.ms.siemens.com.client.GroupRestClient;
import ams.cs.ms.siemens.com.entity.PRP_Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(path= "/sinecams/ms/groups")
public class GroupController {

    public static final String SERVICE_ONE_URL = "http://localhost:8081/sinecams/groups/";

    @Autowired
    private WebClient.Builder webClient;

    private DeviceRestClient deviceRestClient;
    private GroupRestClient groupRestClient;

    public GroupController(DeviceRestClient deviceRestClient, GroupRestClient groupRestClient) {
        this.deviceRestClient = deviceRestClient;
        this.groupRestClient = groupRestClient;
    }

    @GetMapping
    public Mono<List<PRP_Group>> getGroups(){
        return webClient.build()
                .get()
                .uri(SERVICE_ONE_URL)
                .retrieve().
                onStatus(status -> status.value() == 401,
                        clientResponse -> Mono.empty())
                .bodyToMono(new ParameterizedTypeReference<List<PRP_Group>>() {
                }).log();
    }


    @GetMapping("/groupDevices")
    public Mono<List<PRP_Group>> getGroupDevices(){
        var url = SERVICE_ONE_URL.concat("/groupDevices");
        return webClient.build()
                .get()
                .uri(url)
                .retrieve().
                onStatus(status -> status.value() == 401,
                        clientResponse -> Mono.empty())
                .bodyToMono(new ParameterizedTypeReference<List<PRP_Group>>() {
                }).log();
    }

    @PostMapping
    public Mono<String> createGroup(@RequestBody PRP_Group group) {

        return webClient.build()
                .post()
                .uri(SERVICE_ONE_URL)
                .body(Mono.just(group), PRP_Group.class)
                .retrieve()
                .bodyToMono(String.class).log();
    }


    @GetMapping("/{id}")
    public Mono<PRP_Group> retrieveGroupById(@PathVariable("id") Long groupId) {
       return groupRestClient.returnGroupId(groupId)
                .flatMap(groupInfo -> {
                    var deviceListIsMono = deviceRestClient.returnDevices(groupId)
                            .collectList();

                    return deviceListIsMono.map(
                            devices -> new PRP_Group());

                });

    }


}

