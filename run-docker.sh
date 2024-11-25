#!/bin/bash
if [ "$1" != "" ]; then
    echo "Building with profile: $1"
else
    echo "Build profile is not specified"
    exit -1
fi

docker run -d --network common \
-p 8082:8082 \
-v /var/log/itmatter:/var/log/itmatter \
webcrawler-restfulapi-$1:latest

# --user $(id -u):$(id -g) \ was removed from the docker run command, otherwise tomcat could not listen to port 8080
# --env-file .env can be used to override spring boot properties