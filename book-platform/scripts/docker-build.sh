#!/usr/bin/env bash

mvn compile jib:dockerBuild -f audit
mvn compile jib:dockerBuild -f book-impport
mvn compile jib:dockerBuild -f configuration-server
mvn compile jib:dockerBuild -f discovery-server
mvn compile jib:dockerBuild -f gateway
mvn compile jib:dockerBuild -f order-service
mvn compile jib:dockerBuild -f product-service
