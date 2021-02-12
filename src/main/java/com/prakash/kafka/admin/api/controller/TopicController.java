package com.prakash.kafka.admin.api.controller;

import com.prakash.kafka.admin.api.domain.TopicRequest;
import com.prakash.kafka.admin.api.service.MessagingService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
This class provides endpoints to create/delete/alter/clear topic
 */
@Slf4j
@RestController
public class TopicController {
    private static final Logger logger
            = LoggerFactory.getLogger(MessagingService.class);
    @Autowired
    private MessagingService messagingService;

    @GetMapping("/health")
    public String health(){
        return "Admin api is running";
    }

    @PostMapping("/topic")
    public String createTopic(@RequestBody TopicRequest topicDetails){
        logger.info("Topic create request recived : {}",topicDetails);
        messagingService.createTopic(topicDetails);
        return "created topic";
    }

    @DeleteMapping ("/topic")
    public String deleteTopic(@RequestBody TopicRequest topicDetails){
        messagingService.deleteTopic(topicDetails);
        return "created topic";
    }
    @PostMapping ("/topic/clear")
    public String clearTopic(@RequestBody TopicRequest topicDetails){
        messagingService.clearTopic(topicDetails);
        return "created topic";
    }
    @PutMapping("/topic")
    public String alterTopic(){
        //messagingService.createTopic();
        return "created topic";
    }
}
