#!/bin/bash

docker run -d --network common \
-p 8080:8080 \
-v /var/log/itmatter:/var/log/itmatter \
csc207-restfulapi:latest

# --user $(id -u):$(id -g) \ was removed from the docker run command, otherwise tomcat could not listen to port 8080
# --env-file .env can be used to override spring boot properties