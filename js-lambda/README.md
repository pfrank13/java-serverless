This is a small node app that I use for a comparison with the GraalNative stuff in the main repo. Mostly to compare performance and initially to make sure I didn't have IAM permissions issues in AWS.

To install dependencies
```shell
npm install
```

To create zip for deployment into lambda
```shell
./deploy.sh
```
Then the zip is in
```shell
./build/js-lambda.zip
```
