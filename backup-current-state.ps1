# Backup Script for FoodDelivery Project
# This script creates a backup of the current code state

$projectPath = "C:\Users\75509\Downloads\FoodDelivery-main (1)\FoodDelivery-main"
$backupPath = "C:\Users\75509\Downloads\FoodDelivery-Backup-" + (Get-Date -Format "yyyyMMdd-HHmmss")

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "FoodDelivery Project Backup Tool" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Check if project path exists
if (-Not (Test-Path $projectPath)) {
    Write-Host "Error: Project path not found!" -ForegroundColor Red
    Write-Host "Path: $projectPath" -ForegroundColor Red
    exit 1
}

Write-Host "Project Path: $projectPath" -ForegroundColor Green
Write-Host "Creating backup..." -ForegroundColor Yellow

# Create backup directory
try {
    New-Item -ItemType Directory -Path $backupPath -Force | Out-Null
    Write-Host "Backup directory created: $backupPath" -ForegroundColor Green
} catch {
    Write-Host "Error creating backup directory!" -ForegroundColor Red
    exit 1
}

# Copy project files (exclude build folders and .gradle)
Write-Host "Copying project files..." -ForegroundColor Yellow

$excludeFolders = @("build", ".gradle", ".idea", "*.apk", "*.aab")

Get-ChildItem -Path $projectPath -Recurse | Where-Object {
    $item = $_
    $shouldExclude = $false
    
    foreach ($exclude in $excludeFolders) {
        if ($item.FullName -like "*\$exclude\*" -or $item.FullName -like "*\$exclude") {
            $shouldExclude = $true
            break
        }
    }
    
    -not $shouldExclude
} | ForEach-Object {
    $targetPath = $_.FullName.Replace($projectPath, $backupPath)
    $targetDir = Split-Path $targetPath -Parent
    
    if (-Not (Test-Path $targetDir)) {
        New-Item -ItemType Directory -Path $targetDir -Force | Out-Null
    }
    
    if (-Not $_.PSIsContainer) {
        Copy-Item -Path $_.FullName -Destination $targetPath -Force
    }
}

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Backup Completed Successfully!" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Backup Location: $backupPath" -ForegroundColor Yellow
Write-Host ""
Write-Host "To restore this backup:" -ForegroundColor Cyan
Write-Host "1. Close Android Studio" -ForegroundColor White
Write-Host "2. Delete current project folder" -ForegroundColor White
Write-Host "3. Copy backup folder to original location" -ForegroundColor White
Write-Host "4. Rename folder to 'FoodDelivery-main'" -ForegroundColor White
Write-Host "5. Reopen in Android Studio" -ForegroundColor White
Write-Host ""

# Also initialize git if not already done
Set-Location $projectPath
if (-Not (Test-Path "$projectPath\.git")) {
    Write-Host "Initializing Git repository..." -ForegroundColor Yellow
    git init
    git add .
    git commit -m "Save current state - $(Get-Date -Format 'yyyy-MM-dd HH:mm:ss')"
    Write-Host "Git repository initialized and committed!" -ForegroundColor Green
    Write-Host ""
    Write-Host "To create additional checkpoints:" -ForegroundColor Cyan
    Write-Host "  git add ." -ForegroundColor White
    Write-Host "  git commit -m 'Your message'" -ForegroundColor White
    Write-Host ""
    Write-Host "To restore to this point:" -ForegroundColor Cyan
    Write-Host "  git log  (view commit history)" -ForegroundColor White
    Write-Host "  git reset --hard <commit-hash>" -ForegroundColor White
} else {
    Write-Host "Git repository already exists, creating new commit..." -ForegroundColor Yellow
    git add .
    git commit -m "Save current state - $(Get-Date -Format 'yyyy-MM-dd HH:mm:ss')"
    Write-Host "Changes committed successfully!" -ForegroundColor Green
}

Write-Host ""
Write-Host "Press any key to exit..." -ForegroundColor Gray
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
