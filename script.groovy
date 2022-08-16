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
    git version
    docker version
    '''
    } catch(Exception e) {
        println("Exception: ${e}")
    } finally {
        // The finally block always executes.
    }
}

def getVersionSuffix() {
    if (params.RC) { // <- If RC parameter is set to true...
        return env.VERSION_RC // <- Then the Version Suffix is just the RC number
    } else { // Otherwise...
        return env.VERSION_RC + '+SNAPSHOT.' + env.BUILD_NUMBER // <- Add the RC number + 'SNAPSHOT' + the build number
    }
}

return this