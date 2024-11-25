#!/bin/bash
WAR_FILE=$(ls ./target/restful-api-*.jar | head -n 1)
java -jar -Dlog4j2.debug "$WAR_FILE"

