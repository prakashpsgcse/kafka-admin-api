package com.prakash.kafka.admin.api.service;

import com.prakash.kafka.admin.api.domain.TopicRequest;

public interface MessagingService {

    public void createTopic(TopicRequest topicDetails);
    public void deleteTopic();
    public void clearTopic();
    public void publishMessage();
    public void consumeMessage();
    public void addACL();
    public void removeACL();
    public void getURP();
    public void getOffline();
    public void listTopicsInCluster();

}
