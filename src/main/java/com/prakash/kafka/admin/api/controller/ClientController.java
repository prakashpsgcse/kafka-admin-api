package com.prakash.kafka.admin.api.controller;

import org.springframework.web.bind.annotation.PutMapping;

/*
This is for kafka-client operations like produce / consume etc
This provides options to Reset Consumer group offset
 */
public class ClientController {
    @PutMapping("/topic")
    public String publishMessage(){
        return "created topic";
    }
    @PutMapping("/topic")
    public String consumeMessage(){
        return "created topic";
    }
    @PutMapping("/topic")
    public String resetOffsetToEarliest(){
        return "created topic";
    }
    @PutMapping("/reset/offset")
    public String resetOffsetToLatest(){
        return "created topic";
    }
    @PutMapping("/reset/offset")
    public String resetToOffset(){
        return "created topic";
    }
    @PutMapping("/reset/offset/timestamp")
    public String resetOffsetToTimeStamp(){
        return "created topic";
    }
}
