Build and test
```shell
./gradlew clean build
```

Native image (Required more than the default 2GB of memory for me to build)
```shell
./docker-build.sh
```

Lambda ready zip with proper bootstrap
```shell
./deploy.sh
```

The zip that is created by this command in `./build/distributions/java-serverless-\*.zip` is what you want to deploy for the JVM related Lambda
```shell
./gradlew clean buildZip
```

For a JVM Lambda (with a provided runtime) the Handler class that needs to be configured is
```java
com.github.pfrank13.handler.StreamHandler
```

TODO:
- Try out the native image with https://github.com/localstack/localstack, I think there is going to be a bunch of environment variables that the micronaut entrance class for AWS Lambda
- Follow up with Micronaut filed issues
