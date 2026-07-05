@REM ----------------------------------------------------------------------------
@REM Apache Maven Wrapper startup batch script (classic, self-contained)
@REM ----------------------------------------------------------------------------
@echo off

set BASE_DIR=%~dp0
set WRAPPER_JAR="%BASE_DIR%.mvn\wrapper\maven-wrapper.jar"
set WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

if not exist %WRAPPER_JAR% (
  for /f "usebackq tokens=1,2 delims==" %%A in ("%BASE_DIR%.mvn\wrapper\maven-wrapper.properties") do (
    if "%%A"=="wrapperUrl" set WRAPPER_URL=%%B
  )
  echo Downloading Maven Wrapper jar from: %WRAPPER_URL%
  powershell -Command "Invoke-WebRequest -Uri '%WRAPPER_URL%' -OutFile %WRAPPER_JAR%"
)

if "%JAVA_HOME%"=="" (
  set JAVACMD=java
) else (
  set JAVACMD="%JAVA_HOME%\bin\java"
)

%JAVACMD% %MAVEN_OPTS% -classpath %WRAPPER_JAR% "-Dmaven.multiModuleProjectDirectory=%BASE_DIR%" %WRAPPER_LAUNCHER% %*
