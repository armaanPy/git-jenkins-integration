def gv

pipeline {
    agent any
    
    parameters {
        string(name: 'USER_ID', defaultValue: 'Armaan', description: '')
        choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: '')
        booleanParam(name: 'executeTests', defaultValue: true, description: '')
    }

    stages {
        stage('Initialise') {
            steps {
                echo "Hello ${params.USERID}"
            script {
                gv = load "script.groovy"
            }
            }
        }
        stage('Build') {
            steps {
                echo "Hello ${params.USERID}"
                echo 'Building...'
            }
        }
	    stage('Test') {
            when {
                expression {
                    params.executeTests
                }
            }
            steps {
                echo "Hello ${params.USERID}"
                echo 'Testing...'
            }
        }
        stage('Deploy') {
            steps {
                echo "Hello ${params.USERID}"
                echo 'Deploying...'
            }
        }
    }
}
