pipeline {

    agent any

    environment {
        JAVA_HOME = '/Library/Java/JavaVirtualMachines/temurin-17.jdk/Contents/Home'
        PATH = "/opt/homebrew/bin:${JAVA_HOME}/bin:${env.PATH}"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/erpritpalsingh17-ui/rest-assured-interview-framework.git'
            }
        }

        stage('Check Java and Maven') {
            steps {
                sh 'java -version'
                sh 'mvn -version'
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