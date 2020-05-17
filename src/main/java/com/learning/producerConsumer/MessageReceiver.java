package com.learning.producerConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;

@Component
public class MessageReceiver implements Runnable {

    private BlockingQueue<String> blockingQueue;

    @Autowired
    public MessageReceiver(@Qualifier("createBlockingQueue") BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            blockingQueue.put("A");
            blockingQueue.put("B");
            blockingQueue.put("C");
            blockingQueue.put("D");
            blockingQueue.put("E");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
