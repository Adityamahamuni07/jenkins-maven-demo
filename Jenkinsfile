
pipeline {
    agent {
        label 'linux-agent'
    }

    stages {

        stage('Check Agent') {
            steps {
                sh 'hostname'
                sh 'whoami'
            }
        }

        stage('Check Java') {
            steps {
                sh 'java -version'
            }
        }

        stage('Check Disk') {
            steps {
                sh 'df -h'
            }
        }

        stage('Check Workspace') {
            steps {
                sh 'pwd'
                sh 'ls -la'
            }
        }
    }
}
