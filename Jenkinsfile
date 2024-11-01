pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                    echo 'Building the application...'
                    sh 'mvn clean package'
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
                    sh 'docker build -t your-docker-image-name .'
                    sh 'docker tag your-docker-image-name:latest your-ecr-repo-uri:latest'
                    sh 'docker push your-ecr-repo-uri:latest'
                }
            }
        }
        stage('Deploy to EKS') {
            steps {
                script {
                    echo 'Deploying to EKS...'
                    sh 'kubectl apply -f deployment.yaml'
                }
            }
        }
    }
}
