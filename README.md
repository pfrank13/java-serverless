Build and test
`./gradlew clean build`

Native image (Required more than the default 2GB of memory for me to build)
`./docker-build.sh`

Lambda ready zip with proper bootstrap
`./deploy.sh`

TODO:
- Figure out why the Micronaut app can't talk to the RDS instance but node can. Seems it connection timesout in ~1 second (even tho the connection timeout is 30 seconds)
- Try out the native image with https://github.com/localstack/localstack, I think there is going to be a bunch of environment variables that the micronaut entrance class for AWS Lambda