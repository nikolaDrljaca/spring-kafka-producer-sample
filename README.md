# Spring Kafka Producer 

Sample application demonstrating a REST API built on Spring Boot, integrating with Kafka.

The app accepts `POST` requests and forwards the data to a Kafka `topic`.

## Setup

1. Run `kafka-broker` and `zookeeper` using the included `docker-compose.yml` file:
    ```
    # This will pull the necessary docker images and run the containers.
    # Port 9092 is exposed for kafka topics by default 
    # Make sure to run the command from project root
    
    docker-compose up -d
    ```
2. Run the Spring boot application
   1. Either from IntelliJ - using `SpringProducerApplication.main()`
   2. Build a `.jar` file with Maven and run it