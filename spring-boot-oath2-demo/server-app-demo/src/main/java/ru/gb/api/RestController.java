package ru.gb.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping
public class RestController {
    private String storage;

    @GetMapping("/storage")
    public String storage() {
        return storage;
    }
    @PostMapping("/storage")
    public String updateStorage(@RequestBody String content) {
        this.storage = content;
        return storage;
    }
}
