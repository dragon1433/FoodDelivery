@echo off
echo ====================================
echo FoodDelivery App - Reset Tool
echo ====================================
echo.
echo This will clear all app data and cache
echo.
pause

echo Clearing app data...
adb shell pm clear com.fooddelivery.app

echo.
echo App data cleared successfully!
echo Please restart the app.
echo.
pause
