FROM oracle/graalvm-ce:20.0.0-java11 as graalvm
#FROM oracle/graalvm-ce:20.0.0-java11 as graalvm # For JDK 11
RUN gu install native-image

COPY . /home/app/java-serverless-graal-app
WORKDIR /home/app/java-serverless-graal-app

RUN ls build/libs/
RUN native-image --no-server -cp build/libs/java-serverless-*-all.jar

FROM frolvlad/alpine-glibc
RUN apk update && apk add libstdc++
EXPOSE 8080
COPY --from=graalvm /home/app/java-serverless-graal-app/java-serverless-graal-app /java-serverless-graal-app/java-serverless-graal-app
ENTRYPOINT ["/java-serverless-graal-app/java-serverless-graal-app"]