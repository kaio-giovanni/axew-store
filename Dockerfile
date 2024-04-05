FROM ubuntu:focal-20240216

VOLUME /tmp
WORKDIR /project

RUN rm /bin/sh && ln -s /bin/bash /bin/sh

# Install required packages
RUN echo "Installing required packages ..."
RUN apt-get update && apt-get install -y curl zip unzip && apt-get clean

# Install Java
ARG JAVA_VERSION=17.0.10-zulu
RUN echo "Installing Java ${JAVA_VERSION} ..."
RUN curl -s "https://get.sdkman.io" | bash
RUN source "$HOME/.sdkman/bin/sdkman-init.sh" && sdk install java ${JAVA_VERSION}
ENV PATH=/root/.sdkman/candidates/java/current/bin:$PATH

# Copying files
RUN echo "Copying files ..."
COPY . .

RUN chown -R root /project

RUN echo "Building the project ..."
RUN sed -i -e 's/\r$//' ./gradlew
RUN ./gradlew clean build

# Getting .jar file
RUN echo "Getting .jar file"
ARG JAR_FILE=/build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]