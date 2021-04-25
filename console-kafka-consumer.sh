#!/bin/bash

docker exec -it bankaccount-kafka ./bin/kafka-console-consumer.sh --from-beginning --topic transactions --bootstrap-server localhost:9092