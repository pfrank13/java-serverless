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

TODO:
- Try out the native image with https://github.com/localstack/localstack, I think there is going to be a bunch of environment variables that the micronaut entrance class for AWS Lambda
- Follow up with Micronaut filed issues