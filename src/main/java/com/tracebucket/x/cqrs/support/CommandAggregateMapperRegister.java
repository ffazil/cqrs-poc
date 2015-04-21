package com.tracebucket.x.cqrs.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by ffl on 02-03-2015.
 */
@Component
public class CommandAggregateMapperRegister {
    private static Logger log = LoggerFactory.getLogger(CommandAggregateMapperRegister.class);

    protected ConcurrentMap<String, String> mappingRegister = new ConcurrentHashMap<>(0);


    public void map(String instanceId, String commandId){
        mappingRegister.put(instanceId, commandId);
    }

    public String getAggregateId(String instanceId){
        return mappingRegister.get(instanceId);
    }


}
