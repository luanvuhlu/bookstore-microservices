#!/usr/bin/env bash

set -e

REGISTRY="${DOCKER_REGISTRY:-ghcr.io/luanvuhlu}"
PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/../.." && pwd)"

echo "Publishing Docker images to registry: $REGISTRY"
echo ""

docker build -f "$PROJECT_ROOT/audit/Dockerfile" -t "$REGISTRY/audit:latest" "$PROJECT_ROOT" && docker push "$REGISTRY/audit:latest"
docker build -f "$PROJECT_ROOT/configuration-server/Dockerfile" -t "$REGISTRY/configuration-server:latest" "$PROJECT_ROOT" && docker push "$REGISTRY/configuration-server:latest"
docker build -f "$PROJECT_ROOT/discovery-server/Dockerfile" -t "$REGISTRY/discovery-server:latest" "$PROJECT_ROOT" && docker push "$REGISTRY/discovery-server:latest"
docker build -f "$PROJECT_ROOT/gateway/Dockerfile" -t "$REGISTRY/gateway:latest" "$PROJECT_ROOT" && docker push "$REGISTRY/gateway:latest"
docker build -f "$PROJECT_ROOT/louis-store/Dockerfile" -t "$REGISTRY/louis-store:latest" "$PROJECT_ROOT" && docker push "$REGISTRY/louis-store:latest"
docker build -f "$PROJECT_ROOT/order-service/Dockerfile" -t "$REGISTRY/order-service:latest" "$PROJECT_ROOT" && docker push "$REGISTRY/order-service:latest"
docker build -f "$PROJECT_ROOT/product-service/Dockerfile" -t "$REGISTRY/product-service:latest" "$PROJECT_ROOT" && docker push "$REGISTRY/product-service:latest"

echo ""
echo "All images published successfully to $REGISTRY"
