@echo off
echo Building SkriptModuleLoader...

set JAVA_HOME=%~dp0java17
set MAVEN_HOME=%~dp0maven

"%MAVEN_HOME%\bin\mvn.cmd" clean package

echo.
echo Build finished!
pause
