package com.prakash.kafka.admin.api.service;

public interface MessagingService {

    public void createTopic();
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
