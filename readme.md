# AXEW STORE

An API REST created using spring-boot with Java.

## Dependencies

- git
- Java 17
- gradle ^7.2

## Project setup

1. Execute the commands below:

```bash
git clone https://github.com/kaio-giovanni/axew-store 
cd axew-store
gradle clean build
```

2. Please create a `.env` file in the project root and enter your credentials based on the `.env.example` file.

## Run project

- Execute the command below:

```bash
gradle bootRun
```

Alternatively, you can run the project as a Docker container:

- Create the docker image for the project:

```bash

docker build -t axew-image .
```

```bash

docker run --name axew -p 8080:8080 --env-file ./.env axew-image
```

## Documentation

- To see documentation of the project, run the project and access the path below:

Example:

```
http://localhost:8080/swagger-ui-custom.html
```

## Run tests

- To run all tests, execute the command below:

```bash
gradle test
```

- To run a specific test, execute the command below:

```bash
gradle test --tests SomeSpecificTest
```

- To run tests with debug mode, execute the command below:

```bash
DEBUG=true gradle test
```

## Deploy

To make deploy, run the command `gradle deploy` passing environment variables below:

- `STAGE` Stage name (dev, validation or prod) where it's necessary to make deploy (Required)
- `RUN_TESTS` If true, run tests before deploy. If false, don't run tests before deploy. By default, it's true.
- `SSH_IDENTITY_KEY` Content of ssh key file (.pem)

Example:

```bash
STAGE=dev SSH_IDENTITY_PEM="Max..." gradle deploy
```

## Author

| ![user](https://avatars1.githubusercontent.com/u/64810260?v=4&s=150) |
| ----------------------------- |
| <p align="center"> <a href="https://github.com/kaio-giovanni"> @kaio-giovanni </a> </p>|


