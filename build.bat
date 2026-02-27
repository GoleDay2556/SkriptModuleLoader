@echo off
setlocal enabledelayedexpansion

echo =====================================
echo   SkriptModuleLoader Auto Builder
echo =====================================

:: -------------------------------------
:: Check JAVA_HOME
:: -------------------------------------
if defined JAVA_HOME (
    echo Using JAVA_HOME: %JAVA_HOME%
    set "PATH=%JAVA_HOME%\bin;%PATH%"
) else (
    echo JAVA_HOME is not set. Using system Java...
)

java -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java not found!
    pause
    exit /b 1
)

:: -------------------------------------
:: Check MAVEN_HOME
:: -------------------------------------
if defined MAVEN_HOME (
    echo Using MAVEN_HOME: %MAVEN_HOME%
    set "MVN_CMD=%MAVEN_HOME%\mvn.cmd"
) else (
    where mvn >nul 2>&1
    if errorlevel 1 (
        echo ERROR: Maven not found!
        echo Set MAVEN_HOME or install Maven.
        pause
        exit /b 1
    )
    echo Using system Maven...
    set "MVN_CMD=mvn"
)

:: -------------------------------------
:: Build Project
:: -------------------------------------
echo.
echo Building project...
echo.

call "%MVN_CMD%" clean package

if errorlevel 1 (
    echo.
    echo BUILD FAILED!
    pause
    exit /b 1
)

echo.
echo BUILD SUCCESS!
echo Output located in: target\
echo.

pause
exit /b 0