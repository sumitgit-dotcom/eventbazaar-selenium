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
                    set "JAVA_HOME=C:\\Program Files\\Eclipse Adoptium\\jdk-21.0.12.101-hotspot"
                    set "PATH=%JAVA_HOME%\\bin;%PATH%"
                    where java
                    java -version
                    call mvn clean test -Dmaven.compiler.fork=true -Dmaven.compiler.executable="C:\\Program Files\\Eclipse Adoptium\\jdk-21.0.12.101-hotspot\\bin\\javac.exe"
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
