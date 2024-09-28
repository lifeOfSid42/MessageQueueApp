package org.example.producerconsumer.message;

public class MessageTracker {
    private int successCount = 0;
    private int errorCount = 0;

    public synchronized void incrementSuccessCount() {
        successCount++;
    }

    public synchronized void incrementErrorCount() {
        errorCount++;
    }

    public synchronized int getSuccessCount() {
        return successCount;
    }

    public synchronized int getErrorCount() {
        return errorCount;
    }
}