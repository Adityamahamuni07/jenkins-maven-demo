
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    credentialsId: 'github-token',
                    url: 'https://github.com/Adityamahamuni07/jenkins-maven-demo.git'
            }
        }

        stage('Check Project') {
            steps {
                sh '''
                    echo "Checking Maven project..."
                    ls -la
                    test -f pom.xml
                '''
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -B clean package'
            }
        }

        stage('Verify') {
            steps {
                sh '''
                    echo "Generated artifacts:"
                    find target -maxdepth 1 -name "*.jar" -type f
                '''
            }
        }
    }

    post {
        success {
            echo 'Webhook-triggered build completed successfully!'
        }

        failure {
            echo 'Build failed. Check Console Output.'
        }
    }
}
