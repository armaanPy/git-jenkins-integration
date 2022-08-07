// code == load "script.groovy"
def code

pipeline {
    agent any
    // Disable DefaultCheckout so I can include it manually in 'Checkout SCM' stage
    options { skipDefaultCheckout(true) }
    parameters {
        string(name: 'USER_ID', defaultValue: 'Armaan', description: '')
        // Set Yes/No param for the ability to execute tests
        booleanParam(name: 'executeTests', defaultValue: true, description: '')
        // Specify different build versions
        choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: '')
    }

    stages {
        // Manually checkout scm
        stage('Checkout SCM') {
            steps {
                checkout scm
            }
        }
        // Initialise code as "script.groovy"
        stage('Load Script') {
            steps {
                script {
                    code = load "script.groovy"
                }
            }
        }

        stage('Build') {
            steps {
                echo "Hello ${params.USER_ID}"
                // Call upon buildApp function within script.groovy
                script {
                    code.buildApp()
                }
            }
        }

	    stage('Test') {
            // booleanParam for if you want to execute this stage
            when {
                expression {
                    params.executeTests
                }
            }
            steps {
                echo "Hello ${params.USER_ID}"
                echo 'This stage was only executed as the "executeTests" parameter was ticked'
                echo "Testing ${params.VERSION}..."
            }
        }

        stage('Deploy') {
            steps {
                echo "Hello ${params.USER_ID}"
                echo "Deploying ${params.VERSION}..."
            }
        }
    }
}
