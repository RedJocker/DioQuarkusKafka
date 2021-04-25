#!/bin/bash

Extensions="-Dextensions="

add() {
	Extensions+=$1
}



add quarkus-mongodb-panache,
add quarkus-mongodb-client,

add quarkus-resteasy,
add quarkus-resteasy-mutiny,
add quarkus-resteasy-jsonb,
add quarkus-jsonb,

add quarkus-kafka-streams,
add quarkus-smallrye-reactive-messaging-kafka,
add quarkus-smallrye-context-propagation



mvn quarkus:add-extensions ${Extensions}