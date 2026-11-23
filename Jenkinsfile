
pipeline {
    agent any

    environment {
        IMAGE = 'aditya9023/aditya20'
        DEPLOY_HOST = '43.205.209.230'
        DEPLOY_USER = 'aditya9023'
    }

    stages {
        stage('Pull Docker Image') {
            steps {
                sshagent(credentials: ['deploy-ec2-key']) {
                    sh """
                        ssh ${DEPLOY_USER}@${DEPLOY_HOST} \
                        'sudo docker pull ${IMAGE}:latest'
                    """
                }
            }
        }

        stage('Stop Old Container') {
            steps {
                sshagent(credentials: ['deploy-ec2-key']) {
                    sh """
                        ssh ${DEPLOY_USER}@${DEPLOY_HOST} \
                        'sudo docker rm -f jenkins-demo || true'
                    """
                }
            }
        }

        stage('Run New Container') {
            steps {
                sshagent(credentials: ['deploy-ec2-key']) {
                    sh """
                        ssh ${DEPLOY_USER}@${DEPLOY_HOST} \
                        'sudo docker run -d \
                        --name jenkins-demo \
                        -p 8080:8080 \
                        ${IMAGE}:latest'
                    """
                }
            }
        }

        stage('Verify Deployment') {
            steps {
                sshagent(credentials: ['deploy-ec2-key']) {
                    sh """
                        ssh ${DEPLOY_USER}@${DEPLOY_HOST} \
                        'sudo docker ps'
                    """
                }
            }
        }
    }
}

