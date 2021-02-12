package com.prakash.kafka.admin.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

/*
This is for Admin operations like reassignment
Checking URP , Offline partition
 */
public class AdminController {
    @GetMapping("/urp")
    public String getURP(){
        return "created topic";
    }
    @GetMapping("/offline")
    public String getOffline(){
        return "created topic";
    }
    @GetMapping("/topic")
    public String describetopic(){
        return "created topic";
    }
    @PutMapping("/topic")
    public String reassignTopic(){
        return "created topic";
    }
    @PutMapping("/topic")
    public String changeLeader(){
        return "created topic";
    }
}
