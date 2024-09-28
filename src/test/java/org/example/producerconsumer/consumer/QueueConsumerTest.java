package org.example.producerconsumer.consumer;

import org.example.producerconsumer.exception.MessageProcessingException;
import org.example.producerconsumer.message.MessageTracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.BlockingQueue;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

public class QueueConsumerTest {

    private BlockingQueue<String> mockQueue;
    private MessageTracker mockTracker;
    private QueueConsumer consumer;

    @BeforeEach
    public void setUp() {
        mockQueue = Mockito.mock(BlockingQueue.class);
        mockTracker = Mockito.mock(MessageTracker.class);
        consumer = Mockito.spy(new QueueConsumer(mockQueue, mockTracker));
    }

    @Test
    public void testConsumeMessages() throws MessageProcessingException, InterruptedException {
        when(mockQueue.take()).thenReturn("Message-1", "Message-2", "END");
        doNothing().when(consumer).processMessage(Mockito.anyString());
        consumer.consumeMessages();

        verify(mockTracker, times(2)).incrementSuccessCount();
        verify(mockTracker, never()).incrementErrorCount();
    }

    @Test
    public void testConsumeMessagesWithError() throws MessageProcessingException, InterruptedException {
        when(mockQueue.take()).thenReturn("Message-1", "Message-2", "END");
        doThrow(new MessageProcessingException("Simulated processing error for Message-1"))
                .when(consumer).processMessage("Message-1");
        doNothing().when(consumer).processMessage("Message-2");
        consumer.consumeMessages();

        verify(mockTracker, times(1)).incrementSuccessCount();
        verify(mockTracker, times(1)).incrementErrorCount();
    }
}