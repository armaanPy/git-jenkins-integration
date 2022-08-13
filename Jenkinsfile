def code

pipeline {
    agent any
    options { skipDefaultCheckout(true) }
    environment {
        DEVELOPMENT = "algo-trade-dev.capital.com"
        UAT = "algo-trade-uat.capital.com"
        DEMO = "algo-trade-demo.capital.com"
        PRODUCTION = "algo-trade-prod.capital.com"
    }
    parameters {
        string(name: 'USER_ID', defaultValue: '', description: '')
        booleanParam(name: 'Execute tests?', defaultValue: true, description: '')
        choice(name: 'TESTS', choices: ['Regression', 'Performance', 'Integration'], description: '')
        choice(name: 'RELEASE', choices: ['1.1', '1.2', '1.3'], description: '')
        string(name: 'TARGET_ENVIRONMENT', defaultValue: "${PRODUCTION}", description:'')

    }


    stages {
        stage('Checkout SCM') {
            steps {
                checkout scm
                //git branch: 'main', credentialsId: 'armaanpy-github', poll: false, url: 'https://github.com/armaanPy/jgsu-spring-petclinic' <- Specify branch via Git Plugin
            }
        } 

        stage('Initialise') {
            steps {
                script {
                    code = load "script.groovy"
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    code.buildApp()
                }
            }
        }

	    stage('Test') {
            when {
                expression {
                    params.executeTests
                }
            }
            steps {
                sh """
                echo "Hello ${params.USER_ID}"
                echo 'This stage was only executed as the "executeTests" parameter was ticked'
                echo "Testing ${params.VERSION}..."
                """
            }
        }

        stage('Deploy') {
            input {
                message 'Deploy?'
                ok 'Do it!'
                parameters {
                    string(name: 'TARGET_ENVIRONMENT', defaultValue: "${PRODUCTION}", description:'')
                }
            }

            steps {
                echo "Deploying "
            }
        }
    }
        post {
            success {
                echo "Build: ${BUILD_NUMBER} successful."
            }
            failure {
                echo "Build: ${BUILD_NUMBER} failed."
            }
        }
}
