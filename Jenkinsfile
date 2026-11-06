
pipeline {
    agent any

    environment {
        APP_NAME = 'jenkins-maven-demo'
        IMAGE_TAG = "${BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm

                echo 'Source code checked out.'
            }
        }

        stage('Maven Build') {
            steps {
                sh 'mvn -B clean package'
            }
        }

        stage('Verify JAR') {
            steps {
                sh '''
                    echo "Checking generated JAR..."
                    ls -lh target/
                    test -n "$(find target -maxdepth 1 -name "*.jar" -type f)"
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                    docker build \
                        -t ${APP_NAME}:${IMAGE_TAG} \
                        .
                '''
            }
        }

        stage('Verify Docker Image') {
            steps {
                sh '''
                    docker images
                    docker image inspect ${APP_NAME}:${IMAGE_TAG}
                '''
            }
        }

        stage('Run Docker Container') {
            steps {
                sh '''
                    docker run --rm ${APP_NAME}:${IMAGE_TAG}
                '''
            }
        }
    }

    post {
        always {
            junit testResults: 'target/surefire-reports/*.xml',
                  allowEmptyResults: true
        }

        success {
            echo 'Maven build and Docker image creation completed!'
        }

        failure {
            echo 'Pipeline failed. Check Console Output.'
        }
    }
}
