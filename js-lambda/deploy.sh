#!/bin/bash
rm -rf build
mkdir build
npm install
zip -r ./build/js-lambda.zip .
