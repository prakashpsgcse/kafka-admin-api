package com.prakash.kafka.admin.api.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class URPDetails {
    private String topic;
    private String partition;
    private String leader;
    private String replicas;
    private String isr;

    @Override
    public String toString() {
        return "URPDetails{" +
                "topic='" + topic + '\'' +
                ", partition='" + partition + '\'' +
                ", leader='" + leader + '\'' +
                ", replicas='" + replicas + '\'' +
                ", isr='" + isr + '\'' +
                '}';
    }
}
