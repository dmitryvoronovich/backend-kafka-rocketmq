## Backend-Kafka-RocketMQ
The `backend-kafka-rocketmq` repository contains the following modules:

- **db-mgmt** – Manages database schema creation and updates using Liquibase.
- **domain-common** – Contains shared entities, models, and data structures.
- **event-producer-service** – Exposes REST endpoint and produces messages to Kafka.
- **event-matcher-service** – Consumes messages from Kafka, matches them with the bets in database, and produces messages for bets that need to be settled.
- **settlement-consumer** – Consumes bet-settlement messages and logs the results.

## Quick Start

### Run Docker Containers
```
docker compose -f docker/docker-compose.yml up -d
```

### Create DB
```
./gradlew :db-mgmt:update
```
### Create RocketMQ topic
Sadly this is not automated

#### Exec Into Container
```
docker exec -it rmqbroker bash
```
#### Create Topic
```
sh mqadmin updatetopic -t bet-settlements -c DefaultCluster
```
#### Verify Topic Is Created
```
sh mqadmin topicList -n namesrv:9876 | grep bet-settlements
```

### Run Services
Start the services using your IDE's **run configurations**. Make sure the following run configurations are available:
- **Event Producer Service**  
  `EventProducerServiceApplication` (or the main class in `event-producer-service`)
- or via console:
```
java -jar event-producer-service/build/libs/event-producer-service-1.0-SNAPSHOT.jar
```
- **Event Matcher Service**  
  `EventMatcherServiceApplication` (or the main class in `event-matcher-service`)
- or via console:
```
java -jar event-matcher-service/build/libs/event-matcher-service-1.0-SNAPSHOT.jar
```
- **Settlement Consumer**  
  `SettlementConsumerApplication` (or the main class in `settlement-consumer`)
- or via console:
```
java -jar settlement-consumer/build/libs/settlement-consumer-1.0-SNAPSHOT.jar
```

### Send EventOutcome via API
```
HOST_IP=$(cat /etc/resolv.conf | grep nameserver | awk '{print $2}')
```
```
curl -X POST http://$HOST_IP:8080/api/events/post \
-H "Content-Type: application/json" \
-d '{"eventId":"E1","eventName":"Championship Final","winnerId":"W1"}'
```
### Verify
Check application logs

### Stop Docker Containers
```
docker compose -f docker/docker-compose.yml down
```
