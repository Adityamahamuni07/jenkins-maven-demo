pipeline {
    agent any

    stages {

        stage('Git Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Adityamahamuni07/jenkins-maven-demo.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test Results') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }
    }
}
