
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm

                echo 'Source code checked out successfully.'
            }
        }

        stage('Check Project') {
            steps {
                sh '''
                    echo "Checking Maven project..."
                    pwd
                    ls -la
                    test -f pom.xml
                '''
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn -B clean package'
            }
        }

        stage('Verify Artifact') {
            steps {
                sh '''
                    echo "Checking generated JAR..."
                    ls -lh target/
                    find target -maxdepth 1 -name "*.jar" -type f
                '''
            }
        }
    }

    post {
        always {
            junit testResults: 'target/surefire-reports/*.xml',
                  allowEmptyResults: false
        }

        success {
            archiveArtifacts artifacts: 'target/*.jar',
                             fingerprint: true

            echo 'Build, tests, and artifact archiving completed!'
        }

        failure {
            echo 'Pipeline failed. Check Console Output and test reports.'
        }
    }
}
