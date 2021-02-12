package com.prakash.kafka.admin.api.service;

import com.prakash.kafka.admin.api.domain.TopicRequest;
import com.prakash.kafka.admin.api.domain.URP;
import com.prakash.kafka.admin.api.domain.URPDetails;
import org.apache.kafka.clients.admin.*;

import java.util.*;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.common.config.ConfigResource;
import org.apache.kafka.common.config.TopicConfig;
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
    public void deleteTopic(TopicRequest topicDetails) {
        Properties brokerConfig = new Properties();
        brokerConfig.put("bootstrap.servers", topicDetails.getBrokerUrl());

        AdminClient adminClient = AdminClient.create(brokerConfig);
        DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Collections.singleton(topicDetails.getTopicName()));
    }

    @Override
    public void clearTopic(TopicRequest topicDetails) {
        Properties brokerConfig = new Properties();
        brokerConfig.put("bootstrap.servers", topicDetails.getBrokerUrl());

        AdminClient admin = AdminClient.create(brokerConfig);

        ConfigResource configResource = new ConfigResource(ConfigResource.Type.TOPIC, topicDetails.getTopicName());
        //convert retention time to 0
        List<ConfigEntry> entries = new ArrayList<>();
        entries.add(new ConfigEntry(TopicConfig.RETENTION_MS_CONFIG, String.valueOf(0)));
        Config topicConfig = new Config(entries);
        Map<ConfigResource, Config> alterConfigs = new HashMap<ConfigResource, Config>();
        alterConfigs.put(configResource, topicConfig);
        AlterConfigsResult result=admin.alterConfigs(alterConfigs);
        try {
            Thread.sleep(10000);
        }catch(Exception e) {

        }

        //reset retention back
        entries = new ArrayList<>();
        entries.add(new ConfigEntry(TopicConfig.RETENTION_MS_CONFIG, String.valueOf(topicDetails.getTopicConfigs().get("retention.ms"))));
        topicConfig = new Config(entries);
        alterConfigs = new HashMap<ConfigResource, Config>();

        alterConfigs.put(configResource, topicConfig);
        result=admin.alterConfigs(alterConfigs);
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
    public void getURP(String brokerURL) {
        Properties brokerConfig = new Properties();
        brokerConfig.put("bootstrap.servers", brokerURL);
        AdminClient admin = AdminClient.create(brokerConfig);
        URP urp=new URP();

        ListTopicsResult topicsInCluster = admin.listTopics();

        while (!topicsInCluster.names().isDone()){
            //waiting for result [Future Object is returned immediately]
        }
        try {
            DescribeTopicsResult describeTopicsResult = admin.describeTopics(topicsInCluster.names().get());
            while(!describeTopicsResult.all().isDone()){
                //waiting for result [Future Object is returned immediately]
            }
            describeTopicsResult.all().get().forEach((key,topic)->{
                topic.partitions().forEach(partition->{
                    if(partition.replicas().size()!=partition.isr().size()){
                        urp.increaseURP();
                        List<Integer> replicas=new ArrayList<Integer>();
                        List<Integer> isrs=new ArrayList<Integer>();

                        partition.isr().forEach(isr->{
                            isrs.add(isr.id());
                        });
                        partition.replicas().forEach(replica->{
                            if(!isrs.contains(replica.id())){

                                if(urp.getUrpPerBrokers().containsKey(replica.id())){
                                   urp.getUrpPerBrokers().put(replica.id(),urp.getUrpPerBrokers().get(replica.id())+1);
                                }else{
                                    urp.getUrpPerBrokers().put(replica.id(),1);
                                }
                            }
                            replicas.add(replica.id());
                        });

                        int leaderBrokerID=-1;
                        if(null!=partition.leader()){
                            leaderBrokerID=partition.leader().id();
                        }else{
                            urp.increaseOffline();
                        }

                        URPDetails urpDetails=new URPDetails();
                        urpDetails.setIsr(isrs.toString());
                        urpDetails.setReplicas(replicas.toString());
                        urpDetails.setLeader(""+urpDetails.getLeader());
                        urpDetails.setPartition(""+partition.partition());
                        urpDetails.setTopic(topic.name());
                        urp.getUrpDetailsList().add(urpDetails);
                    }
                });
            });
        } catch (ExecutionException|InterruptedException e) {
            e.printStackTrace();
        }finally {
            admin.close();
        }

    }

    @Override
    public void getOffline() {

    }

    @Override
    public void listTopicsInCluster() {

    }
}
