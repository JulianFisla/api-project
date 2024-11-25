#!/bin/bash
if [ "$1" != "" ]; then
    echo "Building with profile: $1"
else
    echo "Build profile is not specified"
    exit -1
fi

./build.sh $1
docker build -t webcrawler-restfulapi-$1:latest .
