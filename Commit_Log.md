- experimenting with quarkus
    - branch testandoQuarkus
    - branch testandoQuarkusKafka
<br>

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
<br>

- transaction-service initial config
    - witten dev.sh
    - ran dev.sh
        - server started normally
    - written application.properties
    - fixed docker-compose.yml
    - docker compose up
    - server still works normally
<br>

- transaction-service initial build
    - written entity Transaction
    - written resource ResourceTransaction
        - 3 endpoints
            - GET listAll
            - GET findById
            - POST create
        - exception handler for id not found
<br>

- producing events from transactions
    - written ProducerTransactionEvent
    - updated resource ResourceTransaction
    - writen console-kafka-consumer.sh
    - fixed docker-compose.yml
<br>

- create quarkus project balance-service
    - ran createQuarkus.sh
        - default restEasy
        - empty project
        - artifact: balance-service
    - written initialDependencies.sh
    - ran initialDependencies.sh
<br>

- balance-service initial config
    - copied dev.sh from transaction-service
    - written application.properties
    - ran dev.sh
        - server started normally
<br> 

- consume transaction event
    - written Transaction entity on balance-service
    - written IncomeTransactionEvent
        - @Produces Topology
    - written TransactionConsumer
        - @ConsumeEvent
    - receives events produced by transaction-service
        - corrently only prints the transaction
<br>

- create balanceServices
    - big problems with mongodb
        - enven not enabling @Transactional it was requiring replicaSet configurations
        - not sure if this is because i'm using quarkus2.0.0.Alpha1, i've read they were implement transactions for mongoDb on quarkus2.0, so this may be a new bug, or i'm missing something
        - found a way to initialize replicaSet using rs.initiate({ _id: "rs0", members: [{ _id: 0, host: "localhost:27017"}] }) on the mongo client, but wish to find an automated solution, for now will move on to keep building the service
    - updated application.properties with new mongo config
    - updated docker-compose with new mongo config
        - not sure it is working since I have to do manual config on mongo client
    - created entity Balance
    - created interface BalanceCalculator
    - created BalanceServices
        - implements BalanceCalculator
        - implements PanacheMongoRepository
    - updated IncomingTransactionEvent
        - injected BalanceServices
    - deleted TransactionConsumer
        - realized it was not consuming anything and was not needed anyway
    - created ResouceBalance
        - 1 endpoint
            - GET findByAccountIn
        - exception handler for id not found
    - minor update on Transaction to inclue isExpense method
        - i could rely on false values for isIncome, or elsing an if clause for isIncome, but i think it makes the code more readable and adaptative to changes, maybe there could be a new INVESTIMENT type for example and break things 
<br>




    


    