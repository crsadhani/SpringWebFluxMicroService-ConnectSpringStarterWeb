package ams.cs.ms.siemens.com.client;

import ams.cs.ms.siemens.com.entity.PRP_Group;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GroupRestClient {

    private WebClient webClient;

    @Value("${restClient.groupInfoUrl}")
    private String groupInfoUrl;

    public GroupRestClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<PRP_Group> returnGroupId(Long groupId) {
        var url = groupInfoUrl.concat("/{id}");

        return webClient.get()
                .uri(url, groupId)
                .retrieve()
                .bodyToMono(PRP_Group.class).log();
    }

}
