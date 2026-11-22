
pipeline {
    agent any

    environment {
        IMAGE_NAME = 'aditya-java-app'
        CONTAINER_NAME = 'aditya-java-container'
    }

    stages {

        stage('Git Checkout') {
            steps {
                echo 'Downloading source code from GitHub'
                checkout scm
            }
        }

        stage('Maven Build') {
            steps {
                echo 'Building Java application'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Check JAR') {
            steps {
                sh 'ls -lh target/*.jar'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Building Docker image'
                sh 'docker build -t ${IMAGE_NAME}:${BUILD_NUMBER} .'
            }
        }

        stage('Docker Image List') {
            steps {
                sh 'docker images'
            }
        }

        stage('Deploy Container') {
            steps {
                sh '''
                    docker rm -f ${CONTAINER_NAME} || true

                    docker run -d \
                      --name ${CONTAINER_NAME} \
                      -p 8081:8080 \
                      ${IMAGE_NAME}:${BUILD_NUMBER}
                '''
            }
        }

        stage('Verify Container') {
            steps {
                sh 'docker ps'
            }
        }
    }

    post {
        success {
            echo 'Docker integration completed successfully!'
        }

        failure {
            echo 'Pipeline failed. Check Jenkins console output.'
        }
    }
}

