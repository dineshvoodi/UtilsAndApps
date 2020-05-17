package com.learning.producerConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@SpringBootApplication
public class ApplicationMain implements CommandLineRunner {

    @Autowired
    MessageReceiver receiver;

    @Autowired
    MessageProcessor processor;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }

    @Bean
    public BlockingQueue<String> createBlockingQueue() {

        return new LinkedBlockingQueue<>(10);

    }

    @Override
    public void run(String... args) throws Exception {
        Thread prodThread = new Thread(receiver);
        Thread consThread = new Thread(processor);

        // Starting threads

        prodThread.start();
        consThread.start();

    }
}
