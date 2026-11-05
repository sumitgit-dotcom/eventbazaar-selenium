pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/sumitgit-dotcom/eventbazaar-selenium.git'
            }
        }
        
        stage('Run Tests') {
            steps {
                // Changed from 'sh' to 'bat' for Windows
                // 'call mvn' ensures the script waits for Maven to finish
                bat 'call mvn clean test'
            }
        }
    }
    
    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
