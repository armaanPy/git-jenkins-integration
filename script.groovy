def buildApp() {
    echo 'Building the application...'
} 

def testApp() {
    echo 'Testing the application...'
} 

def deployApp() {
    echo 'Deploying the application...'
}

def auditTools() {
    try {
    sh '''
    lsb_release -a
    '''
    } catch(Exception e) {
        println("Exception: ${e}")
    }

    try {
    sh '''
    git version
    '''
    } catch(Exception e) {
        println("Exception: ${e}")
    }
    
    try {
    sh '''
    docker version
    '''
    } catch(Exception e) {
        println("Exception: ${e}")
    } finally {
        println("Continuing step...")
        // The finally block always executes
    }
}

def getVersionSuffix() {
    if (params.PERFORM_RELEASE) { // <- If RC parameter is set to true...
        return env.VERSION_RC // <- Then the Version Suffix is just the RC number
    } else { // Otherwise...
        return env.VERSION_RC + '+SNAPSHOT.' + env.BUILD_NUMBER // <- Add the RC number + 'SNAPSHOT' + the build number
    }
}

return this