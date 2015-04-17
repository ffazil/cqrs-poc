package com.tracebucket.x.cqrs.support;

import com.tracebucket.x.cqrs.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;
import reactor.core.Reactor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by ffl on 16-09-2014.
 */
@Component
public class CommandControllerHelper {
    private static Logger log = LoggerFactory.getLogger(CommandControllerHelper.class);

    @Autowired
    private Reactor commandBus;
    //Every ones Victim(Reads and Writes concurrently by CommandBus EventBus)
    protected ConcurrentMap<String, DeferredResult> queue = new ConcurrentHashMap<String, DeferredResult>(0);

    protected void addToQueue(Command command, DeferredResult result){
        String key = command.getCid();
        queue.put(key, result);
        result.onCompletion(new RemoveOnComplete(key));
        result.onTimeout(new RemoveOnTimeout(key));

    }

    public class RemoveOnTimeout implements Runnable{
        private String key;

        public RemoveOnTimeout(String key){
            this.key = key;
        }
        @Override
        public void run() {
            queue.remove(key);
            log.info("Async result with key {} timed out",key);
        }
    }

    public class RemoveOnComplete implements Runnable{
        private String key;

        public RemoveOnComplete(String key){
            this.key = key;
        }

        @Override
        public void run() {
            queue.remove(key);
            log.info("Async result with key {} flushed out",key);
        }
    }

    public DeferredResult fetch(String key){
        log.info("Fetching deferred result for key = " + key);
        if(queue.containsKey(key))
            return queue.get(key);
        else
            return null;
    }

    /*public String intent(String commandName, Object o, DeferredResult deferredResult){
        addToQueue(command, deferredResult);
        log.info("Command: " + commandName + " | " + o.toString());
        commandBus.notify(commandName, command);
        return command.getCid();
    }*/

    public String intent(String commandName, Command command){
        addToQueue(command, new DeferredResult());
        log.info("Command: " + commandName + " | " + command.getData().toString());
        commandBus.notify(commandName, command);
        return command.getCid();
    }

    public String intent(String commandName, Command command, DeferredResult result){
        addToQueue(command, result);
        log.info("Command: " + commandName + " | " + command.getData().toString());
        commandBus.notify(commandName, command);
        return command.getCid();
    }






}
