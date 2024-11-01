pipeline {
    agent any
    environment {
        AWS_REGION = 'us-east-1' // Replace with your AWS region, e.g., 'us-east-1'
        ECR_REPO_URI = '345594595830.dkr.ecr.us-east-1.amazonaws.com/demo-app' // Replace with your actual ECR URI
    }
    stages {
        stage('Build') {
            steps {
                script {
                    echo 'Building the application...'
                    sh 'mvn clean package -DskipTests'
                }
            }
        }
        stage('SonarQube Analysis') {
            steps {
                script {
                    echo 'Running SonarQube analysis...'
                    sh 'mvn sonar:sonar'
                }
            }
        }
        stage('Docker Build and Push') {
            steps {
                script {
                    echo 'Building Docker image...'
                    dockerImage = docker.build("demo-app:${env.BUILD_ID}")

                    echo 'Authenticating Docker with ECR...'
                    sh """
                    aws ecr get-login-password --region $AWS_REGION | docker login --username AWS ECR --password-stdin $ECR_REPO_URI
                    """

                    echo 'Pushing Docker image to ECR...'
                    dockerImage.push("${env.BUILD_ID}")
                    dockerImage.push("latest")
                }
            }
        }
    }
    post {
    always {
        sh '''
        #!/bin/bash
        docker rmi demo-app:$BUILD_ID || true
        docker rmi $ECR_REPO_URI:$BUILD_ID || true
        docker rmi $ECR_REPO_URI:latest || true
        '''
            }
        }


}
