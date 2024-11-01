pipeline {
    agent any
    environment {
        AWS_REGION = 'us-east-1' // Replace with your AWS region, e.g., 'us-east-1'
        ECR_REPO_URI = '345594595830.dkr.ecr.us-east-1.amazonaws.com/demo-app' // Replace with your actual ECR URI
    }
    
    stages {
        stage('Debug') {
            steps {
                // List the contents of the workspace to verify Dockerfile location
                sh 'ls -la /var/lib/jenkins/workspace/JavaMicroservicePipeline/'
            }
        }
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
                withEnv([
                    "SONAR_HOST_URL=http://34.201.43.25:9000/",
                    "SONAR_LOGIN=8a93d16d56a2b8a19eccecce179264e5b4e3a205" // Replace with your actual SonarQube token
                ]) {
                    sh 'mvn sonar:sonar -Dsonar.host.url=$SONAR_HOST_URL -Dsonar.login=$SONAR_LOGIN'
                }
            }
        }
        stage('Docker Build and Push') {
            steps {
                script {
                    sh 'docker build -t demo-app:${env.BUILD_ID} -f demo/Dockerfile demo/'
                }
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
    
    post {
    always {
        script {
            // Check if the image exists before attempting to remove it
            sh 'if [ "$(docker images -q demo-app:8 2> /dev/null)" != "" ]; then docker rmi demo-app:8; fi'
            sh 'if [ "$(docker images -q 345594595830.dkr.ecr.us-east-1.amazonaws.com/demo-app:8 2> /dev/null)" != "" ]; then docker rmi 345594595830.dkr.ecr.us-east-1.amazonaws.com/demo-app:8; fi'
            sh 'if [ "$(docker images -q 345594595830.dkr.ecr.us-east-1.amazonaws.com/demo-app:latest 2> /dev/null)" != "" ]; then docker rmi 345594595830.dkr.ecr.us-east-1.amazonaws.com/demo-app:latest; fi'
            }
        }
    }
}

