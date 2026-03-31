# Multi-stage build for configuration-server
# Stage 1: Build
FROM maven:3.8.6-openjdk-11 AS builder

WORKDIR /workspace

# Copy Maven settings template and inject credentials using BuildKit secrets
COPY scripts/settings.xml /root/.m2/settings.xml

# Use BuildKit secrets to read credentials securely (not stored in image layers)
RUN --mount=type=secret,id=github_username \
    --mount=type=secret,id=github_token \
    if [ -f /run/secrets/github_username ] && [ -f /run/secrets/github_token ]; then \
      GITHUB_USERNAME=$(cat /run/secrets/github_username) && \
      GITHUB_TOKEN=$(cat /run/secrets/github_token) && \
      sed -i "s|\${env.GITHUB_USERNAME}|$GITHUB_USERNAME|g" /root/.m2/settings.xml && \
      sed -i "s|\${env.GITHUB_TOKEN}|$GITHUB_TOKEN|g" /root/.m2/settings.xml; \
    fi

COPY pom.xml .
COPY configuration-server/pom.xml ./configuration-server/
RUN cd configuration-server && mvn dependency:go-offline -DskipTests -pl configuration-server -am || true
COPY . .
RUN cd configuration-server && mvn clean package -DskipTests -DargLine=""
# Stage 2: Runtime
FROM openjdk:11.0.11-jre-slim
WORKDIR /app
COPY --from=builder /workspace/configuration-server/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
