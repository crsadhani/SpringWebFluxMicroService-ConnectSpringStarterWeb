package ams.cs.ms.siemens.com.controller;

import ams.cs.ms.siemens.com.entity.Port;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping(path = "/sinecams/ms/ports")
public class PortController {
    Logger logger = LoggerFactory.getLogger(PortController.class);
    public static final String SERVICE_ONE_URL = "http://localhost:8081/sinecams/ports/";

    @Autowired
    private WebClient.Builder webClient;

    @GetMapping
    public List<Port> getPorts() {
        return webClient.build()
                .get()
                .uri(SERVICE_ONE_URL)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Port>>() {
                }).block();
    }

    }


