- experimenting with quarkus
    - branch testandoQuarkus
    - branch testandoQuarkusKafka


- create quarkus project transaction-service
    - written createQuarkus.sh
    - ran createQuarkus.sh
        - default restEasy
        - empty project
        - artifact: transaction-service
    - written initialDependencies.sh
    - ran initialDependiencies.sh
    - written docker-compose.yml
    - written root .gitignore

- transaction-service initial config
    - witten dev.sh
    - ran dev.sh
        - server started normally
    - written application.properties
    - fixed docker-compose.yml
    - docker compose up
    - server still works normally

- transaction-service initial build
    - written entity Transaction
    - written resource ResourceTransaction
        - 3 endpoints
            - GET listAll
            - GET findById
            - POST create
        - exception handler for id not found

- producing events from transactions
    - written ProducerTransactionEvent
    - updated resource ResourceTransaction
    - writen console-kafka-consumer.sh
    - fixed docker-compose.yml

- create quarkus project balance-service
    - ran createQuarkus.sh
        - default restEasy
        - empty project
        - artifact: balance-service
    - written initialDependencies.sh
    - ran initialDependencies.sh
    


    