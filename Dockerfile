FROM ubuntu:16.04
MAINTAINER Denis Hodzhadzhikov (hodz.denis@yahoo.com)

RUN apt-get update
RUN apt-get install -y openjdk-8-jdk
RUN apt-get install -y maven

COPY pom.xml /usr/local/service/pom.xml
COPY test.csv /usr/local/service/test.csv
COPY random.csv /usr/local/service/random.csv
COPY random2.csv /usr/local/service/random2.csv
COPY src/ /usr/local/service/src

WORKDIR /usr/local/service/

RUN mvn package

CMD ["mvn", "compile"]
CMD ["mvn", "test"]
CMD ["mvn", "exec:java"]
