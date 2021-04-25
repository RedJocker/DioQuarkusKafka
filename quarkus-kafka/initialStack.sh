#!/bin/bash

Extensions="-Dextensions="



add() {
	Extensions+=$1
}

add quarkus-hibernate-orm-panache,
add quarkus-hibernate-orm,
add quarkus-hibernate-validator,

add quarkus-jdbc-postgresql,
add quarkus-reactive-pg-client,

add quarkus-resteasy,
add quarkus-resteasy-mutiny,
add quarkus-resteasy-jsonb,
add quarkus-jsonb,
add quarkus-smallrye-reactive-messaging-kafka,

add quarkus-flyway,
add quarkus-vertx


#echo $Extensions
mvn quarkus:add-extensions ${Extensions}