@echo off
setlocal enabledelayedexpansion

set REGISTRY=%DOCKER_REGISTRY%
if "%REGISTRY%"=="" set REGISTRY=ghcr.io/luanvuhlu

REM Get the project root (two levels up from the script)
for %%A in ("%~dp0..") do for %%B in ("%%~dpA.") do set PROJECT_ROOT=%%~dpB

echo Publishing Docker images to registry: %REGISTRY%
echo.

cd /d "%PROJECT_ROOT%"

docker build -f audit\Dockerfile -t %REGISTRY%/audit:latest . && docker push %REGISTRY%/audit:latest
docker build -f configuration-server\Dockerfile -t %REGISTRY%/configuration-server:latest . && docker push %REGISTRY%/configuration-server:latest
docker build -f discovery-server\Dockerfile -t %REGISTRY%/discovery-server:latest . && docker push %REGISTRY%/discovery-server:latest
docker build -f gateway\Dockerfile -t %REGISTRY%/gateway:latest . && docker push %REGISTRY%/gateway:latest
docker build -f louis-store\Dockerfile -t %REGISTRY%/louis-store:latest . && docker push %REGISTRY%/louis-store:latest
docker build -f order-service\Dockerfile -t %REGISTRY%/order-service:latest . && docker push %REGISTRY%/order-service:latest
docker build -f product-service\Dockerfile -t %REGISTRY%/product-service:latest . && docker push %REGISTRY%/product-service:latest

echo.
echo All images published successfully to %REGISTRY%
