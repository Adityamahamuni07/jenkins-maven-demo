
pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                echo 'Building Java application...'
                sh 'echo Maven Build Successful'
            }
        }

        stage('Test') {
            steps {
                echo 'Running unit tests...'
                sh 'echo All Tests Passed'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Building Docker image...'
                sh 'echo Docker Image Created'
            }
        }

        stage('Production Approval') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    input(
                        id: 'productionApproval',
                        message: 'Do you approve Production deployment?',
                        ok: 'Deploy to Production',
                        submitter: 'ec2-user'
                    )
                }
            }
        }

        stage('Production Deployment') {
            steps {
                echo 'Deploying application to Production...'
                sh 'echo Production Deployment Successful'
            }
        }
    }
}
