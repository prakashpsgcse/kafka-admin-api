package com.prakash.kafka.admin.api.service;

import com.prakash.kafka.admin.api.domain.TopicRequest;
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
    public void createTopic(TopicRequest topicDetails) {
        NewTopic topicDetail=new NewTopic(topicDetails.getTopicName(),topicDetails.getPartitionCount(),topicDetails.getReplicasCount());
        if(topicDetails.getCompactedTopic()){
            topicDetails.getTopicConfigs().put("cleanup.policy","compact");
        }else{
            topicDetails.getTopicConfigs().put("cleanup.policy","delete");
        }
        topicDetail.configs(topicDetails.getTopicConfigs());

        Properties brokerConfig = new Properties();
        brokerConfig.put("bootstrap.servers", topicDetails.getBrokerUrl());

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
