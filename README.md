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

# Integrating SonarQube for Code Quality Analysis

In this step, we will set up SonarQube for analyzing the code quality of our Java microservice application. SonarQube provides a platform for continuous inspection of code quality and helps detect bugs, vulnerabilities, and code smells.

# Installing and Starting SonarQube on AWS EC2

This guide provides instructions for installing and starting SonarQube on an Amazon EC2 instance. SonarQube is an open-source platform for continuous inspection of code quality, which helps in identifying bugs, vulnerabilities, and code smells.

## Prerequisites

1. **AWS Account**: You need an AWS account to create an EC2 instance.
2. **Java**: SonarQube requires Java 11 or later. This guide assumes that Java is installed on the EC2 instance.
3. **EC2 Instance**: A running EC2 instance

## Step 1: Launch an EC2 Instance

1. **Sign in to the AWS Management Console**.
2. **Navigate to EC2**: Go to the EC2 dashboard.
3. **Launch Instance**:
   - Click on "Launch Instance".
   - Choose an Amazon Machine Image (AMI). Select "Amazon Linux 2" or "Ubuntu Server".
   - Choose an instance type (t2.micro is sufficient for testing).
   - Configure security group settings to allow traffic on the following ports:
     - **9000**: SonarQube default port
     - **22**: SSH (for remote access)
   - Review and launch the instance.
4. **Connect to your instance**:
   - Use SSH to connect to your instance. For example:
     ```bash
     ssh -i your-key.pem ec2-user@your-instance-public-dns
     ```

## Step 2: Install Dependencies

### For Ubuntu:
1.Update the package index:

```bash
sudo apt update -y
```
2.Install Java:

```bash
sudo apt install openjdk-11-jdk -y
```
## Step 3: Install SonarQube
1. Download SonarQube
```bash
wget https://binaries.sonarsource.com/Distribution/sonarqube-x.x.x.zip
```
2. Unzip SonarQube
```bash
sudo apt install unzip -y
unzip sonarqube-9.9.0.zip
```
3. Move to a suitable directory
```bash
sudo mv sonarqube-x.x.x /opt/sonarqube
```
4. Create a SonarQube user
```bash
sudo useradd sonaruser
sudo chown -R sonaruser:sonaruser /opt/sonarqube
```
5. Switch to the SonarQube user
```bash
sudo su sonaruser
```
6. Edit the sonar.properties
```bash
vim /opt/sonarqube/conf/sonar.properties
```
7. Change the Login Header Setting
```bash
sonar.web.sso.loginHeader=X-Forwarded-Login
```
8. Start SonarQube
```bash
cd /opt/sonarqube/bin/linux-x86-64
./sonar.sh start
```



## 1. Configure `pom.xml`

*Prerequisites*

- SonarQube server running (either locally or on an AWS EC2 instance)
- Java and Maven installed on your machine

Add the SonarQube Maven plugin to your `pom.xml` file. This plugin will enable Maven to communicate with the SonarQube server and perform the analysis.

### Update your `pom.xml`:

Locate the `<build>` section in your `pom.xml` and add the following plugin configuration:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
        <plugin>
            <groupId>org.sonarsource.scanner.maven</groupId>
            <artifactId>sonar-maven-plugin</artifactId>
            <version>3.9.0.2155</version>
        </plugin>
    </plugins>
</build>
```

**Execute the following command in your project directory to perform the analysis**:

```bash
   mvn clean verify sonar:sonar


