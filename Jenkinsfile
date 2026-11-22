
pipeline {
    agent any

    environment {
        IMAGE_NAME = 'aditya-java-app'
    }

    stages {

        stage('Git Checkout') {
            steps {
                echo 'Downloading source code'
                checkout scm
            }
        }

        stage('Maven Build') {
            steps {
                echo 'Building Java application'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Building Docker image'
                sh '''
                    docker build -t ${IMAGE_NAME}:${BUILD_NUMBER} .
                '''
            }
        }

        stage('Docker Hub Login and Push') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-creds',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable:'DOCKER_TOKEN'
                    )
                ]) {
                    sh '''
                        echo "$DOCKER_TOKEN" | docker login \
                        -u "$DOCKER_USER" --password-stdin

                        docker tag ${IMAGE_NAME}:${BUILD_NUMBER} \
                        "$DOCKER_USER/${IMAGE_NAME}:${BUILD_NUMBER}"

                        docker push \
                        "$DOCKER_USER/${IMAGE_NAME}:${BUILD_NUMBER}"

                        docker logout
                    '''
                }
            }
        }

        stage('Verify Docker Image') {
            steps {
                echo 'Checking Docker images'
                sh 'docker images'
            }
        }
    }

    post {
        success {
            echo 'Docker image successfully pushed to Docker Hub!'
        }

        failure {
            echo 'Pipeline failed. Check Console Output.'
        }
    }
}

