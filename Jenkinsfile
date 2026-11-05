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
                // Notice the extra quotes around the JAVA_HOME path
                bat '''
                    set "JAVA_HOME=C:\\Program Files\\Eclipse Adoptium\\jdk-21.0.12.101-hotspot"
                    set "PATH=%JAVA_HOME%\\bin;%PATH%"
                    call mvn clean test
                '''
            }
        }
    
    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
