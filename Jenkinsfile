// Let Jenkins know how to fetch a shared library:
library identifier: 'jenkins-pipeline-demo-library@master',
        retriever: modernSCM([$class: 'GitSCMSource',
        remote: 'https://github.com/sixeyed/jenkins-pipeline-demo-library.git'])
// @Library('devops') _ <- Nom method

def code
// getVersionSuffix() < - SEMANTIC VERSIONING | Identify if the build is a Release Candidate. And if so, provide it with a Release Candidate Suffix.

pipeline {
    agent any
    // agent  {label "linux && region-eu" }
    // Org likely enabled global timestamper in Jenkins configuration, but if not...
    options { 
        skipDefaultCheckout(true) 
        //timestamps () 
        }
    environment {
        DEVELOPMENT = "algo-trade-dev.capital.com"
        UAT = "algo-trade-uat.capital.com"
        DEMO = "algo-trade-demo.capital.com"
        PRODUCTION = "algo-trade-prod.capital.com"
        VERSION = "0.1.0" // getVersionSuffix()
        VERSION_RC = "RC.1" // getVersionSuffix()
    }
    parameters {
        string(name: 'USER_ID', defaultValue: '', description: '')
        booleanParam(name: 'executeTests', defaultValue: true, description: '')
        choice(name: 'TESTS', choices: ['Regression', 'Performance', 'Integration'], description: '')
        choice(name: 'RELEASE', choices: ['1.1', '1.2', '1.3'], description: '')
        booleanParam(name:'PERFORM_RELEASE', defaultValue: false, description:'Tick this box if this is a release build.') // getVersionSuffix()
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
        stage('Audit Tools') {
            steps {
                script {
                    code.auditTools()
                }
            }
        } 
        stage('Build') {
            environment {
                VERSION_SUFFIX = code.getVersionSuffix() // getVersionSuffix()
            }
            when {
                // This stage will only run WHEN params.RELEASE value is set to False.
                expression {
                    return !params.PERFORM_RELEASE
                }
            }
            steps {
                echo "Building ${env.VERSION} with suffix: ${VERSION_SUFFIX}" // getVersionSuffix()
                script {
                    code.buildApp()
                    // Normally would put build commands here:
                        // sh 'mvn clean compile'
                }
            }
        }

        stage('Build and check its functioning')
            // This step will run the source code, if it is then fine, if it is not then it will archive the source code for us to view
            try{
                sh 'python3 betaregression.py'
            } finally{
                archiveArtifacts artifacts: '/SourceCodeLocation/*.*'
            }

	    stage('Test') {
            when {
                expression {
                    params.executeTests
                }
            }
            steps {
                sh """
                echo 'This stage was only executed as the "executeTests" parameter was ticked.'
                echo "Test: ${params.TESTS} running on Release: ${params.RELEASE}"
                """
                writeFile file: 'test-results.txt', text: 'passed'
            }
        }
        stage('Deploy') {
            input {
                message 'Deploy?'
                ok 'Do it!'
                parameters {
                    string(name: 'TARGET_ENVIRONMENT', defaultValue: "PROD", description:'')
                }
            }
            steps {
                echo "${params.USER_ID} successfully deployed Build ${params.RELEASE} to ${TARGET_ENVIRONMENT}."
            }
        }
        stage('Publish') {
            environment {
                VERSION_SUFFIX = code.getVersionSuffix() // getVersionSuffix()
            }
            when {
                // This stage will only run WHEN params.RELEASE value is set to True.
                expression {
                    return params.PERFORM_RELEASE
                }
            }
            steps {
                echo "${params.USER_ID} successfully published ${env.VERSION} with suffix: ${VERSION_SUFFIX}."
                // Normally would put build commands here:
                    // sh 'mvn clean package'
            }
        }
    }

        post {
            success {
                echo "Build: ${BUILD_NUMBER} successful."
                archiveArtifacts 'test-results.txt'
            }
            failure {
                echo "Build: ${BUILD_NUMBER} failed."
            }
        }
}