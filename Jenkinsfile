pipeline {
    agent any

    options { 
        skipDefaultCheckout(true) 
        //timestamps () 
        }

    stages {
        stage('Checkout SCM') {
            steps {
                checkout scm
                //git branch: 'main', credentialsId: 'armaanpy-github', poll: false, url: 'https://github.com/armaanPy/jgsu-spring-petclinic' <- Specify branch via Git Plugin
            }
        } 
        stage('Build') {
            steps {
                echo "Building from Staging branch."
            }
        }
    }
}