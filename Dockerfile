FROM openjdk:14-alpine
COPY build/libs/java-serverless-*-all.jar java-serverless.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "java-serverless.jar"]