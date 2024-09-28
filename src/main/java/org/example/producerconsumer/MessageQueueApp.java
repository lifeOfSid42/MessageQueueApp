package org.example.producerconsumer;

import org.example.producerconsumer.consumer.QueueConsumer;
import org.example.producerconsumer.message.MessageTracker;
import org.example.producerconsumer.producer.QueueProducer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageQueueApp {
    private static final Logger LOGGER = Logger.getLogger(MessageQueueApp.class.getName());
    private static final int QUEUE_CAPACITY = 10;
    private static final int TOTAL_MESSAGES = 20;

    public static void main(String[] args) {
        BlockingQueue<String> messageQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
        MessageTracker tracker = new MessageTracker();

        // Instantiate Producer and Consumer
        QueueProducer producer = new QueueProducer(messageQueue, TOTAL_MESSAGES);
        QueueConsumer consumer = new QueueConsumer(messageQueue, tracker);

        // Create threads with the producer and consumer
        Thread producerThread = new Thread(producer, "Producer");
        Thread consumerThread = new Thread(consumer, "Consumer");

        // Start the threads
        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Thread interrupted", e);
            Thread.currentThread().interrupt();
        }

        LOGGER.info("Total messages processed successfully: " + tracker.getSuccessCount());
        LOGGER.info("Total error messages encountered: " + tracker.getErrorCount());
    }
}