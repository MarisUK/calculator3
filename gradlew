#!/bin/sh

# Gradle wrapper script to run gradlew with correct configuration
# Ensure that the gradle-wrapper.jar is in the appropriate location

GRADLE_WRAPPER_PATH=./gradle/wrapper/gradle-wrapper.jar

exec java -classpath "$GRADLE_WRAPPER_PATH" org.gradle.wrapper.GradleWrapperMain "$@"
