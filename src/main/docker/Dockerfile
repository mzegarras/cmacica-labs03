FROM openjdk:11.0.1-jdk-oracle


ADD target/labs-03-*.jar app.jar
ADD src/main/docker/entrypoint.sh entrypoint.sh
RUN chmod +x entrypoint.sh

EXPOSE 8085


ENTRYPOINT ["/entrypoint.sh"]