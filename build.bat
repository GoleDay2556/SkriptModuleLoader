@echo off
echo Building SkriptModuleLoader with Java 21...

:: Set JAVA_HOME to your portable Java
set JAVA_HOME=%~dp0java21

:: Set MAVEN_HOME to your Maven bin folder
set MAVEN_HOME=C:\Users\golov_sj1znxl\Documents\Maven\apache-maven-3.9.12\bin

:: Run Maven clean packages
"%MAVEN_HOME%\mvn.cmd" clean package

echo.
echo Build finished!
pause
@echo off
echo Building SkriptModuleLoader with Java 21...

:: Set JAVA_HOME to your portable Java
set JAVA_HOME=%~dp0java21

:: Set MAVEN_HOME to your Maven bin folder
set MAVEN_HOME=C:\Users\golov_sj1znxl\Documents\Maven\apache-maven-3.9.12\bin

:: Run Maven clean package
"%MAVEN_HOME%\mvn.cmd" clean package

echo.
echo Build finished!
pause
