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

RUN groupadd -r webcrawler && useradd -r -g webcrawler webcrawler
RUN usermod -aG users webcrawler
COPY . /var/opt/webcrawler-restfulapi

RUN chown -R webcrawler:users /var/opt/webcrawler-restfulapi

RUN mkdir -p /var/log/itmatter && chown -R root:users /var/log/itmatter
RUN mkdir /var/log/itmatter/restful-api && chown -R webcrawler:users /var/log/itmatter/restful-api

RUN chmod -R 777 /var/log/itmatter
RUN chmod +x /var/opt/webcrawler-restfulapi/runme.sh

# Create the mount point directory
#RUN mkdir -p /home/webcrawler-data && chown webcrawler:users /home/webcrawler-data
#RUN chmod -R 775 /home/webcrawler-data

USER webcrawler
WORKDIR /var/opt/webcrawler-restfulapi

# Expose port 8082
EXPOSE 8082

ENTRYPOINT [ "./runme.sh" ]

# check firewall status on target machine
#sudo ufw status verbose
# enable MySQL port on target machine
#sudo ufw allow 3306/tcp

#docker network create --driver bridge common
#docker build -t webcrawler-local:latest .
#docker run -d --network common webcrawler-local:latest
#docker exec -it d1511ec83072 /bin/bash
#docker stats
# stdout and stderr
#docker logs -f 2c8c96a758f9