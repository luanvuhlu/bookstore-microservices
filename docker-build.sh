#!/bin/bash
# Docker build script for all microservices

set -e

PROJECT_ROOT=$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)
REGISTRY="${DOCKER_REGISTRY:-localhost:5000}"

# Get GitHub credentials from environment variables
GITHUB_USERNAME="${GITHUB_USERNAME}"
GITHUB_TOKEN="${GITHUB_TOKEN}"

if [ -z "$GITHUB_USERNAME" ] || [ -z "$GITHUB_TOKEN" ]; then
    echo "WARNING: GITHUB_USERNAME or GITHUB_TOKEN not set. Maven builds may fail if GitHub packages are required."
    echo "Set credentials using: export GITHUB_USERNAME='your-username'; export GITHUB_TOKEN='your-token'"
    echo ""
fi

# Enable Docker BuildKit
export DOCKER_BUILDKIT=1

echo "Building Docker images with BuildKit secrets..."
echo "Registry: $REGISTRY"
echo ""

# Array of modules to build
modules=("audit" "configuration-server" "discovery-server" "gateway" "order-service" "product-service" "product-import" "louis-store")

for module in "${modules[@]}"
do
    echo "=================================================="
    echo "Building Docker image for: $module"
    echo "=================================================="

    # Use BuildKit secrets for secure credential passing
    if [ -n "$GITHUB_USERNAME" ] && [ -n "$GITHUB_TOKEN" ]; then
        docker build \
            -f "$PROJECT_ROOT/$module/Dockerfile" \
            -t "$REGISTRY/luanvv/$module:latest" \
            --secret id=github_username,env=GITHUB_USERNAME \
            --secret id=github_token,env=GITHUB_TOKEN \
            "$PROJECT_ROOT"
    else
        docker build \
            -f "$PROJECT_ROOT/$module/Dockerfile" \
            -t "$REGISTRY/luanvv/$module:latest" \
            "$PROJECT_ROOT"
    fi

    echo "✓ Successfully built: $REGISTRY/luanvv/$module:latest"
    echo ""
done

echo "=================================================="
echo "All Docker images built successfully!"
echo "=================================================="
echo ""
echo "Built images:"
for module in "${modules[@]}"
do
    echo "  - $REGISTRY/luanvv/$module:latest"
done
