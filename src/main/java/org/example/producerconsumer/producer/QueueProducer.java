package org.example.producerconsumer.producer;

import org.example.producerconsumer.message.MessageProducer;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QueueProducer implements Runnable, MessageProducer {
    private final BlockingQueue<String> queue;
    private final int totalMessages;
    private static final Logger LOGGER = Logger.getLogger(QueueProducer.class.getName());

    public QueueProducer(BlockingQueue<String> queue, int totalMessages) {
        this.queue = queue;
        this.totalMessages = totalMessages;
    }

    @Override
    public void run() {
        try {
            produceMessages();
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Producer interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void produceMessages() throws InterruptedException {
        for (int i = 1; i <= totalMessages; i++) {
            String message = "Message-" + i;
            queue.put(message);
            LOGGER.info("Produced: " + message);
            Thread.sleep(500); // added delay in message production
        }
        queue.put("END"); // signal consumer to stop
    }
}