@echo off
REM Start React Counter App HTTP Server
REM This batch file starts a local server on port 8000

cd /d "%~dp0"
echo.
echo ========================================
echo React Counter App - Starting Server
echo ========================================
echo.
echo Starting HTTP server on port 8000...
echo.
echo Open your browser and navigate to:
echo http://localhost:8000
echo.
echo Press Ctrl+C to stop the server
echo ========================================
echo.

python -m http.server 8000

