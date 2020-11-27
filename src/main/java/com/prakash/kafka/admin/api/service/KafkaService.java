package com.prakash.kafka.admin.api.service;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class KafkaService implements MessagingService{
    private static final Logger logger
            = LoggerFactory.getLogger(MessagingService.class);
    @Override
    public void createTopic() {
        String brokerUrl="localhost:9092";
        String topic="admin-test";
        Integer partitionCount=1;
        Short replicationFactor=1;
        NewTopic topicDetail=new NewTopic(topic,partitionCount,replicationFactor);
        Map<String,String> topicConfig=new HashMap<>();
        topicConfig.put("cleanup.policy","delete");
        topicConfig.put("retention.ms","12345");
        topicDetail.configs(topicConfig);

        Properties brokerConfig = new Properties();
        brokerConfig.put("bootstrap.servers", brokerUrl);

        AdminClient adminClient = AdminClient.create(brokerConfig);
        CreateTopicsResult result=adminClient.createTopics(Collections.singleton(topicDetail));
        while(!result.all().isDone()){
            logger.debug("waiting for topic to be created");
        }
        logger.info("Topic created");
    }

    @Override
    public void deleteTopic() {

    }

    @Override
    public void clearTopic() {

    }

    @Override
    public void publishMessage() {

    }

    @Override
    public void consumeMessage() {

    }

    @Override
    public void addACL() {

    }

    @Override
    public void removeACL() {

    }

    @Override
    public void getURP() {

    }

    @Override
    public void getOffline() {

    }

    @Override
    public void listTopicsInCluster() {

    }
}
