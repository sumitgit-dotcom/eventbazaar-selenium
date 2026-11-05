pipeline {
    agent any
<<<<<<< HEAD

=======
    tools {
        maven 'M3' // must match a Maven install name in Global Tool Configuration
    }
>>>>>>> cb4eec4 (Fix DriverFactory method signature and update Hooks for headless execution)
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/sumitgit-dotcom/eventbazaar-selenium.git'
            }
        }

        stage('Run Tests') {
            steps {
<<<<<<< HEAD
                bat '''
                    set "JAVA_HOME=C:\\Users\\admin\\AppData\\Local\\Programs\\Eclipse Adoptium\\jdk-21.0.12.101-hotspot"
                    set "PATH=%JAVA_HOME%\\bin;%PATH%"
                    call mvn clean test -Dheadless=true
                '''
            }
        }
    }

=======
                sh 'mvn clean test'
            }
        }
    }
>>>>>>> cb4eec4 (Fix DriverFactory method signature and update Hooks for headless execution)
    post {
        always {
            junit 'target/surefire-reports/*.xml'
            // If using TestNG plugin and testng-results.xml is generated:
            // step([$class: 'Publisher', reportFilenamePattern: '**/testng-results.xml'])
        }
    }
}
