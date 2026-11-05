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
                // Assumes mvn is installed on the server's PATH
                sh 'mvn clean test'
            }
        }
    }
    
    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
