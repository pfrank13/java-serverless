#!/bin/sh
docker build . -t java-serverless
echo
echo
echo "To run the docker container execute:"
echo "    $ docker run -p 8080:8080 java-serverless"
