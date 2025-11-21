# start with slim debian image
FROM debian:bullseye-slim

RUN apt-get update && apt-get install -y openjdk-17-jdk maven && rm -rf /var/lib/apt/lists/*

ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV MAVEN_HOME=/usr/share/maven
ENV PATH=$%JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH

WORKDIR /app

VOLUME ["/data"]

#copy project files into the working directory
COPY . .

CMD ["mvn", "clean", "test"]