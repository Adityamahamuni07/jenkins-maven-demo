
pipeline {
    agent any

    environment {
        APP_NAME = 'jenkins-maven-demo'
        DOCKERHUB_USERNAME = 'YOUR_DOCKERHUB_USERNAME'
        IMAGE_TAG = "${BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm

                echo 'Source code checked out successfully.'
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
                        -t ${DOCKERHUB_USERNAME}/${APP_NAME}:${IMAGE_TAG} \
                        .
                '''
            }
        }

        stage('Docker Hub Login and Push') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-creds',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_TOKEN'
                    )
                ]) {
                    sh '''
                        set +x

                        echo "$DOCKER_TOKEN" | docker login \
                            --username "$aditya9023" \
                            --password-stdin

                        docker push \
                            ${DOCKERHUB_USERNAME}/${APP_NAME}:${IMAGE_TAG}

                        docker logout
                    '''
                }
            }
        }

        stage('Verify Image') {
            steps {
                sh '''
                    docker images
                    echo "Image pushed to Docker Hub:"
                    echo "${DOCKERHUB_USERNAME}/${APP_NAME}:${IMAGE_TAG}"
                '''
            }
        }
    }

    post {
        success {
            echo 'SUCCESS: Docker image pushed to Docker Hub!'
        }

        failure {
            echo 'FAILURE: Check Console Output.'
        }
    }
}
