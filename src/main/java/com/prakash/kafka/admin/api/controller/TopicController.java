package com.prakash.kafka.admin.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
This class provides endpoints to create/delete/alter/clear topic
 */
@RestController
public class TopicController {
    @GetMapping("/health")
    public String health(){
        return "Admin api is running";
    }
}
