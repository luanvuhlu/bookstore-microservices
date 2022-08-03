[![Test](https://github.com/luanvuhlu/bookstore-microservices/actions/workflows/test.yml/badge.svg?branch=master)](https://github.com/luanvuhlu/bookstore-microservices/actions/workflows/test.yml)  
# bookstore-microservices
A simple microservices project

## Build docker

```bash
mvn compile jib:dockerBuild
```

## Kubernetes with Kind

```bash
cd deployment
```

Create a cluster
```bash
kind create cluster --name <cluster name>
```
Create resources
```bash
kubectl apply -f k8s
```

Run proxy
```bash
kubectl proxy
```

Access
* Gateway: http://localhost:8001/api/v1/namespaces/default/services/http:gateway:/proxy/
* Zipkin: http://localhost:8001/api/v1/namespaces/default/services/http:zipkin:/proxy/

## URLs

Eureka: http://localhost:8761
Zipkin: http://localhost:9411/

### TODO

[x] Zipkin  
[x] Spring Cloud Feign  
[] OAuth  
[] Full docker support  
[x] Kubernetes  
[x] Spring Cloud Configuration  
[x] Spring Cloud Stream  
[] Ribbon  
[] Prometheus  
[] HashiCorp Vault  
[x] Jenkins  
[x] Sonarqube  
[] Fault Tolerance  
[x] Liquibase  
[x] Kotlin projects
[] Python projects
