#!/bin/bash
docker build . -t java-serverless
mkdir -p build
docker run --rm --entrypoint cat java-serverless  /home/application/function.zip > build/function.zip
