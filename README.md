Build and test
./gradlew clean build

Native image (Required more than the default 2GB of memory for me to build)
./gradlew clean shadowJar && ./docker-build.sh

TODO:
Try out the native image in Lambda, currently it's bundling a bunch of crap that I think I wouldn't do in a non POC world
Try out the native image with https://github.com/localstack/localstack, I think there is going to be a bunch of environment variables that the micronaut entrance class for AWS Lambda