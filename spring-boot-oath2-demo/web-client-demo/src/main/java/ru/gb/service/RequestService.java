package ru.gb.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.swing.*;
import java.util.UUID;

@Slf4j
@Service
public class RequestService {

    public void doRequest() {
        WebClient webClient = WebClient.create();

        String response = webClient.get()
                .uri("http://localhost:8180/api/resource")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info("Response from server: {}", response);

        String newStorageValue = UUID.randomUUID().toString();
        log.info("Делаем запрос на обновление с {}", newStorageValue);
        webClient.post()
                .uri("http://localhost:8180/api/resource")
                .bodyValue(newStorageValue)
                .retrieve();

    }
}
