package com.prakash.kafka.admin.api.controller;

import com.prakash.kafka.admin.api.service.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
This class provides endpoints to create/delete/alter/clear topic
 */
@RestController
public class TopicController {
    @Autowired
    private MessagingService messagingService;

    @GetMapping("/health")
    public String health(){
        return "Admin api is running";
    }

    @PostMapping("/topic")
    public String createTopic(){
        messagingService.createTopic();
        return "created topic";
    }

    @DeleteMapping ("/topic")
    public String deleteTopic(){
        messagingService.createTopic();
        return "created topic";
    }
    @PostMapping ("/topic")
    public String clearTopic(){
        messagingService.createTopic();
        return "created topic";
    }
    @PutMapping("/topic")
    public String alterTopic(){
        messagingService.createTopic();
        return "created topic";
    }
}
