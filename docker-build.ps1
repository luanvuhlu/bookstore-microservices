# Docker build script for all microservices (Windows PowerShell)

$ErrorActionPreference = "Stop"

$ProjectRoot = Split-Path -Parent $MyInvocation.MyCommand.Path

# Get GitHub credentials from environment variables
$GithubUsername = $env:GITHUB_USERNAME
$GithubToken = $env:GITHUB_TOKEN

if (-not $GithubUsername -or -not $GithubToken) {
    Write-Host "WARNING: GITHUB_USERNAME or GITHUB_TOKEN not set. Maven builds may fail if GitHub packages are required." -ForegroundColor Yellow
    Write-Host "Set credentials using: `$env:GITHUB_USERNAME='your-username'; `$env:GITHUB_TOKEN='your-token'" -ForegroundColor Yellow
    Write-Host ""
}

# Enable Docker BuildKit
$env:DOCKER_BUILDKIT = "1"

Write-Host "Building Docker images with BuildKit secrets..." -ForegroundColor Green
Write-Host ""

# Array of modules to build
$modules = @("audit", "configuration-server", "discovery-server", "gateway", "order-service", "product-service", "product-import", "louis-store")

foreach ($module in $modules) {
    Write-Host "==================================================" -ForegroundColor Yellow
    Write-Host "Building Docker image for: $module" -ForegroundColor Yellow
    Write-Host "==================================================" -ForegroundColor Yellow

    $imageName = "luanvv/$module`:latest"
    $dockerfile = Join-Path -Path $ProjectRoot -ChildPath "$module\Dockerfile"

    # Use BuildKit secrets for secure credential passing
    if ($GithubUsername -and $GithubToken) {
        docker build `
            -f $dockerfile `
            -t $imageName `
            --secret id=github_username,env=GITHUB_USERNAME `
            --secret id=github_token,env=GITHUB_TOKEN `
            $ProjectRoot
    } else {
        docker build `
            -f $dockerfile `
            -t $imageName `
            $ProjectRoot
    }

    if ($LASTEXITCODE -eq 0) {
        Write-Host "Successfully built: $imageName" -ForegroundColor Green
    } else {
        Write-Host "Failed to build: $imageName" -ForegroundColor Red
        exit 1
    }
    Write-Host ""
}

Write-Host "==================================================" -ForegroundColor Green
Write-Host "All Docker images built successfully!" -ForegroundColor Green
Write-Host "==================================================" -ForegroundColor Green
Write-Host ""
Write-Host "Built images:" -ForegroundColor Green
foreach ($module in $modules) {
    Write-Host "  - luanvv/$module`:latest" -ForegroundColor Green
}
