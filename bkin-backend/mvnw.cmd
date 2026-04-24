@REM ----------------------------------------------------------------------------
@REM Maven Wrapper startup batch script
@REM ----------------------------------------------------------------------------
@echo off
set MAVEN_WRAPPER_JAR="%USERPROFILE%\.m2\wrapper\dists\apache-maven-3.9.6-bin\maven-wrapper.jar"
set DOWNLOAD_URL=https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar
set JAVA_HOME_OPT=

IF NOT EXIST "%USERPROFILE%\.m2\wrapper" mkdir "%USERPROFILE%\.m2\wrapper"

IF EXIST %MAVEN_WRAPPER_JAR% GOTO startWithJavaw

powershell -Command "Invoke-WebRequest -Uri '%DOWNLOAD_URL%' -OutFile %MAVEN_WRAPPER_JAR%"

:startWithJavaw
java -jar %MAVEN_WRAPPER_JAR% %*
