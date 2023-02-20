package ru.gb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.gb.service.RequestService;

@SpringBootApplication
public class WebClientApplication {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(WebClientApplication.class);
        RequestService requestService = context.getBean(RequestService.class);
        requestService.doRequest();
    }


}