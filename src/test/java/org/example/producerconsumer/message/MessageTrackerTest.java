package org.example.producerconsumer.message;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageTrackerTest {

    @Test
    public void testSuccessCount() {
        MessageTracker tracker = new MessageTracker();
        tracker.incrementSuccessCount();
        tracker.incrementSuccessCount();
        assertEquals(2, tracker.getSuccessCount());
    }

    @Test
    public void testErrorCount() {
        MessageTracker tracker = new MessageTracker();
        tracker.incrementErrorCount();
        assertEquals(1, tracker.getErrorCount());
    }
}