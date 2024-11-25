# Use Ubuntu Jammy as the base image
FROM ubuntu:22.04

# Stop dpkg-reconfigure tzdata from prompting for input
ENV DEBIAN_FRONTEND=noninteractive

# Install Apache and PHP
RUN apt-get update && \
    apt-get install -y --no-install-recommends\
    curl \
    git \
    gnupg \
    lsb-release \
    software-properties-common \
    openjdk-17-jdk \
    mysql-client \
    iputils-ping \
    net-tools && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

RUN groupadd -r webuser && useradd -r -g webuser webuser
RUN usermod -aG users webuser

COPY . /var/opt/restfulapi

RUN chown -R webuser:users /var/opt/restfulapi

RUN mkdir -p /var/log/itmatter && chown -R root:users /var/log/itmatter
RUN mkdir /var/log/itmatter/restful-api && chown -R webuser:users /var/log/itmatter/restful-api

RUN chmod -R 777 /var/log/itmatter
RUN chmod +x /var/opt/restfulapi/runme.sh

USER webuser
WORKDIR /var/opt/restfulapi

# Expose port 8080
EXPOSE 8080

ENTRYPOINT [ "./runme.sh" ]
#ENTRYPOINT ["java", "-jar", "-Dlog4j2.debug", "app.jar"]




