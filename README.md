# MessageQueueApp

This application simulates a producer-consumer scenario using a message queue in core Java. It tracks and logs the total number of messages processed successfully and the number of errors encountered.

## Features

- Simulates a message queue using a blocking queue.
- Producer generates messages and places them in the queue.
- Consumer processes messages from the queue.
- Tracks the number of successfully processed messages and errors encountered.

## Prerequisites

- Java Development Kit (JDK) 11 or higher
- An IDE such as IntelliJ IDEA, Eclipse, or any text editor
- Apache Maven (for managing dependencies)

## Getting Started

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/lifeOfSid42/MessageQueueApp.git
   cd MessageQueueApp
   
2. **Build the Project**: 
   ```bash
   mvn clean install
- This command will compile the code, run all tests, and package the application into a JAR file.
  - If you want to run the tests separately, use: 
     ```bash
    mvn test
  
3. **Run the Application**:
- Using the command line: 
   ```bash
   java -jar target/producer-consumer-app-1.0-SNAPSHOT.jar
- Alternatively, you can run the MessageQueueApp class from your IDE:
  - Open the `MessageQueueApp` main class.
  - Run the main method to start the application.
  - You should see log outputs indicating the production and consumption of messages.