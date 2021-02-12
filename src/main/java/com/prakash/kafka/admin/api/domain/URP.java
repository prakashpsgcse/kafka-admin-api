package com.prakash.kafka.admin.api.domain;

import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class URP {
    private Integer urpCount;
    private Integer offlineCount;
    private Map<Integer, Integer> urpPerBrokers=new HashMap<Integer, Integer> ();
    private Map<Integer, Integer> offlinePerBroker=new HashMap<Integer, Integer> ();
    private List<URPDetails> urpDetailsList=new ArrayList<URPDetails>();

    public void addURP(Integer brokerID,Integer count){
        urpPerBrokers.put(brokerID,count);
    }
    public void addOffline(Integer brokerID,Integer count){
        offlinePerBroker.put(brokerID,count);
    }

    public void increaseURP(){
        urpCount++;
    }
    public void increaseOffline(){
        offlineCount++;
    }


}
