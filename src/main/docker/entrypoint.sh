#!/bin/sh

java $JAVA_OPTS -Dspring.main.allow-bean-definition-overriding=true -Djava.security.egd=file:/dev/./urandom -jar /app.jar