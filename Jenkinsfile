pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/erpritpalsingh17-ui/rest-assured-interview-framework.git'
            }
        }

        stage('Run API Tests') {
            steps {
                sh 'mvn clean test'
            }
        }
    }

    post {
        always {
            echo 'API test execution completed'
        }

        success {
            echo 'API tests PASSED'
        }

        failure {
            echo 'API tests FAILED'
        }
    }
}