package org.example.producerconsumer.message;

import org.example.producerconsumer.exception.MessageProcessingException;

public interface MessageConsumer {
    void consumeMessages() throws MessageProcessingException, InterruptedException;
}