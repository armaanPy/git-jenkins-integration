# git-jenkins-integration

Git Jenkins integration from public localhost.

To install Jenkinsfile validator in VSCode:
- Download extension: https://marketplace.visualstudio.com/items?itemName=janjoerke.jenkins-pipeline-linter-connector
- Edit Extenstion Settings:
    - jenkins.pipeline.linter.connector.url is the endpoint at which your Jenkins Server expects the POST request, containing your Jenkinsfile which you want to validate. Typically this points to "http://<your_jenkins_server:port>/pipeline-model-converter/validate".
    - jenkins.pipeline.linter.connector.user allows you to specify your Jenkins username.
    - jenkins.pipeline.linter.connector.pass allows you to specify your Jenkins password. This must be your Jenkins API token ( Username -> Configure -> API Token) 
    - jenkins.pipeline.linter.connector.crumbUrl has to be specified if your Jenkins Server has CRSF protection enabled. Typically this points to "http://<your_jenkins_server:port>/crumbIssuer/api/xml?xpath=concat(//crumbRequestField,%22:%22,//crumb)".
    
To set up webhooks from localhost Jenkins instance:
- Sign up for a free account with SocketXP - https://portal.socketxp.com/#/login . Get a unique JWT auth token assigned just for you.
- Download SocketXP agent - https://www.socketxp.com/download/
- Login to connect to the SocketXP agent with the SocketXP Cloud Gateway, using the unique auth-token provided to you in the SocketXP Portal.
- Create Public URL for your localhost Jenkins instance with $ socketxp connect [http://localhost:port]
- Enter generated Public URL to GitHub repo webhook
