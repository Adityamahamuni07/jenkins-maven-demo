
pipeline {
    agent any

    parameters {
        choice(
            name: 'ENVIRONMENT',
            choices: ['dev', 'staging', 'production'],
            description: 'Select deployment environment'
        )

        string(
            name: 'APP_NAME',
            defaultValue: 'my-java-app',
            description: 'Enter application name'
        )

        booleanParam(
            name: 'RUN_TESTS',
            defaultValue: true,
            description: 'Run application tests?'
        )

        string(
            name: 'APP_VERSION',
            defaultValue: '1.0',
            description: 'Enter application version'
        )
    }

    stages {

        stage('Display Parameters') {
            steps {
                echo "Environment: ${params.ENVIRONMENT}"
                echo "Application: ${params.APP_NAME}"
                echo "Version: ${params.APP_VERSION}"
                echo "Run Tests: ${params.RUN_TESTS}"
            }
        }

        stage('Build') {
            steps {
                echo "Building ${params.APP_NAME}"
                echo "Version: ${params.APP_VERSION}"
            }
        }

        stage('Test') {
            when {
                expression {
                    params.RUN_TESTS == true
                }
            }

            steps {
                echo 'Running application tests...'
                sh 'echo Tests completed successfully'
            }
        }

        stage('Development Deployment') {
            when {
                expression {
                    params.ENVIRONMENT == 'dev'
                }
            }

            steps {
                echo 'Deploying to Development Environment'
            }
        }

        stage('Staging Deployment') {
            when {
                expression {
                    params.ENVIRONMENT == 'staging'
                }
            }

            steps {
                echo 'Deploying to Staging Environment'
            }
        }

        stage('Production Deployment') {
            when {
                expression {
                    params.ENVIRONMENT == 'production'
                }
            }

            steps {
                echo 'Deploying to Production Environment'
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }

        failure {
            echo 'Pipeline failed!'
        }
    }
}
