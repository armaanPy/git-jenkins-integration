pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
		buildDescription 'AH-Test-Pipeline'
                echo 'Building...'
            }
        }

	stage('Test') {
            steps {
                echo 'Testing...'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying...'
            }
        }

    }
}
