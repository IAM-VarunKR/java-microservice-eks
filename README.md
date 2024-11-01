# Java Microservice on AWS EKS

This project involves deploying a Java-based microservice application on an AWS EKS (Kubernetes) cluster with a CI/CD pipeline, monitoring, and logging.


# Creating a Java Microservice with Maven

In this step, we create a simple Java-based microservice using Maven and Spring Boot, exposing a REST API that returns "Hello, World!".

## Prerequisites

- Java JDK 11 or higher
- Maven installed

## Steps to Create the Microservice

1. **Initialize the Maven Project**:
   ```bash
   mvn archetype:generate -DgroupId=com.example.demo -DartifactId=demo -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
   cd demo

2. **Update pom.xml: Add the following dependencies to the <dependencies> section**:

  ```bash
  xml
  Copy code
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
  </dependency>
  <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>5.7.0</version>
      <scope>test</scope>
  </dependency>

```
3. **Create Main Application Class: Create src/main/java/com/example/demo/DemoApplication.java and add the following code**:

  ```bash
  package com.example.demo;

  import org.springframework.boot.SpringApplication;
  import org.springframework.boot.autoconfigure.SpringBootApplication;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.RestController;

  @SpringBootApplication
  @RestController
  public class DemoApplication {
      public static void main(String[] args) {
          SpringApplication.run(DemoApplication.class, args);
      }
      @GetMapping("/hello")
      public String hello() {
          return "Hello, World!";
      }
  }
```

4. **Build the Project: Navigate to the project root and run**:
```bash
  mvn clean package
```

