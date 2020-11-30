package com.prakash.kafka.admin.api.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class TopicRequest {
    private String brokerUrl;
    private String topicName;
    private Short replicasCount;
    private Integer partitionCount;
    private Boolean compactedTopic;
    private Map<String,String> topicConfigs=new HashMap<String,String>();

}

