# git-jenkins-integration

Simple Git Jenkins integration file v2.0.

To install Jenkinsfile validator in VSCode:
- Download extension: https://marketplace.visualstudio.com/items?itemName=janjoerke.jenkins-pipeline-linter-connector
- Edit Extenstion Settings:
    - jenkins.pipeline.linter.connector.url is the endpoint at which your Jenkins Server expects the POST request, containing your Jenkinsfile which you want to validate. Typically this points to "http://<your_jenkins_server:port>/pipeline-model-converter/validate".
    - jenkins.pipeline.linter.connector.user allows you to specify your Jenkins username.
    - jenkins.pipeline.linter.connector.pass allows you to specify your Jenkins password. This must be your Jenkins API token ( Username -> Configure -> API Token) 
    - jenkins.pipeline.linter.connector.crumbUrl has to be specified if your Jenkins Server has CRSF protection enabled. Typically this points to "http://<your_jenkins_server:port>/crumbIssuer/api/xml?xpath=concat(//crumbRequestField,%22:%22,//crumb)".​