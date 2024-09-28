package org.example.producerconsumer.producer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.BlockingQueue;

import static org.mockito.Mockito.*;

public class QueueProducerTest {

    @Test
    public void testProduceMessages() throws InterruptedException {
        BlockingQueue<String> mockQueue = Mockito.mock(BlockingQueue.class);
        int totalMessages = 5;
        QueueProducer producer = new QueueProducer(mockQueue, totalMessages);

        producer.produceMessages();

        // Verify that the expected number of messages were produced
        for (int i = 1; i <= totalMessages; i++) {
            verify(mockQueue, times(1)).put("Message-" + i);
        }

        // Verify that the END message was sent
        verify(mockQueue, times(1)).put("END");
    }
}