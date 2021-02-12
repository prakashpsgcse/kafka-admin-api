package com.prakash.kafka.admin.api.service;

import com.prakash.kafka.admin.api.domain.TopicRequest;

public interface MessagingService {

    public void createTopic(TopicRequest topicDetails);
    public void deleteTopic(TopicRequest topicDetails);
    public void clearTopic(TopicRequest topicDetails);
    public void publishMessage();
    public void consumeMessage();
    public void addACL();
    public void removeACL();
    public void getURP();
    public void getOffline();
    public void listTopicsInCluster();

}
