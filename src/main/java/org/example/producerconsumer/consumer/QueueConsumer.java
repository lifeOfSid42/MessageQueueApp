package org.example.producerconsumer.consumer;

import org.example.producerconsumer.exception.MessageProcessingException;
import org.example.producerconsumer.message.MessageConsumer;
import org.example.producerconsumer.message.MessageTracker;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QueueConsumer implements Runnable, MessageConsumer {
    private final BlockingQueue<String> queue;
    private final MessageTracker tracker;
    private static final Logger LOGGER = Logger.getLogger(QueueConsumer.class.getName());

    public QueueConsumer(BlockingQueue<String> queue, MessageTracker tracker) {
        this.queue = queue;
        this.tracker = tracker;
    }

    @Override
    public void run() {
        try {
            consumeMessages();
        } catch (InterruptedException | MessageProcessingException e) {
            LOGGER.log(Level.SEVERE, "Consumer interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void consumeMessages() throws MessageProcessingException, InterruptedException {
        while (true) {
            String message = queue.take();
            if ("END".equals(message)) {
                break;
            }

            try {
                processMessage(message);
                tracker.incrementSuccessCount();
                LOGGER.info("Consumed successfully: " + message);
            } catch (Exception e) {
                tracker.incrementErrorCount();
                LOGGER.log(Level.SEVERE, "Error processing message: " + message, e);
            }
        }
    }

    protected void processMessage(String message) throws MessageProcessingException{
        // added random error during processing
        if (Math.random() > 0.8) {
            throw new MessageProcessingException("Simulated processing error for " + message);
        }
        try {
            Thread.sleep(1000); // added delay in message processing
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }
}