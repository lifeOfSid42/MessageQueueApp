package org.example.producerconsumer.message;

public interface MessageProducer {
    void produceMessages() throws InterruptedException;
}