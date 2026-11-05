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
                bat '''
                    set "JAVA_HOME=C:\\Users\\admin\\AppData\\Local\\Programs\\Eclipse Adoptium\\jdk-21.0.12.101-hotspot"
                    set "PATH=%JAVA_HOME%\\bin;%PATH%"
                    call mvn clean test
                '''
            }
        }
    }
    
    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
