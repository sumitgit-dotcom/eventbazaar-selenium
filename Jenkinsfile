pipeline {
    agent any
    
    // If Maven is not configured in Jenkins Tools, you might need to remove the 'tools' block 
    // and ensure 'mvn' is in your system PATH.
    tools {
        maven 'M3' 
    }
    
    stages {
        stage('Checkout') {
            steps {
                // Pointing to the correct repo
                git branch: 'main', url: 'https://github.com/sumitgit-dotcom/eventbazaar-selenium.git'
            }
        }
        
        stage('Run Tests') {
            steps {
                // This runs your Maven tests
                sh 'mvn clean test'
            }
        }
    }
    
    post {
        always {
            // This publishes the JUnit reports
            junit 'target/surefire-reports/*.xml'
        }
    }
}
