package com.prakash.kafka.admin.api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/*
This for ACL operations(add/remove) on Kafka Topic
 */
public class AclController {
    @PostMapping ("/topic/{topic}/acl")
    public String addACL(){
        return "created topic";
    }
    @DeleteMapping("/topic")
    public String removeACL(){
        return "created topic";
    }
    @GetMapping("/topic")
    public String listACL(){
        return "created topic";
    }
}
