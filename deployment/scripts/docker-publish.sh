#!/usr/bin/env bash

mvn compile com.google.cloud.tools:jib-maven-plugin:3.0.0:build -Dimage=ghcr.io/luanvuhlu/audit -f audit
mvn compile com.google.cloud.tools:jib-maven-plugin:3.0.0:build -Dimage=ghcr.io/luanvuhlu/book-impport -f book-impport
mvn compile com.google.cloud.tools:jib-maven-plugin:3.0.0:build -Dimage=ghcr.io/luanvuhlu/configuration-server -f configuration-server
mvn compile com.google.cloud.tools:jib-maven-plugin:3.0.0:build -Dimage=ghcr.io/luanvuhlu/discovery-server -f discovery-server
mvn compile com.google.cloud.tools:jib-maven-plugin:3.0.0:build -Dimage=ghcr.io/luanvuhlu/gateway -f gateway
mvn compile com.google.cloud.tools:jib-maven-plugin:3.0.0:build -Dimage=ghcr.io/luanvuhlu/order-service -f order-service
mvn compile com.google.cloud.tools:jib-maven-plugin:3.0.0:build -Dimage=ghcr.io/luanvuhlu/product-service -f product-service

