#!/bin/bash

mvn clean install package
docker build -t csc207-restfulapi:latest .
