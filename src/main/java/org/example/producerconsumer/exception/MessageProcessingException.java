package org.example.producerconsumer.exception;

public class MessageProcessingException extends Exception {
    public MessageProcessingException(String message) {
        super(message);
    }
}