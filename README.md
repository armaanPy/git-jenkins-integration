# git-jenkins-integration

Git Jenkins integration from public localhost.

## To install Jenkinsfile validator in VSCode:

- Download extension: https://marketplace.visualstudio.com/items?itemName=janjoerke.jenkins-pipeline-linter-connector
- Edit Extenstion Settings:
  - jenkins.pipeline.linter.connector.url is the endpoint at which your Jenkins Server expects the POST request, containing your Jenkinsfile which you want to validate. Typically this points to "http://<your_jenkins_server:port>/pipeline-model-converter/validate".
  - jenkins.pipeline.linter.connector.user allows you to specify your Jenkins username.
  - jenkins.pipeline.linter.connector.pass allows you to specify your Jenkins password. This must be your Jenkins API token ( Username -> Configure -> API Token)
  - jenkins.pipeline.linter.connector.crumbUrl has to be specified if your Jenkins Server has CRSF protection enabled. Typically this points to "http://<your_jenkins_server:port>/crumbIssuer/api/xml?xpath=concat(//crumbRequestField,%22:%22,//crumb)".

## To set up webhooks from localhost Jenkins instance:

- Sign up for a free account with SocketXP - https://portal.socketxp.com/#/login . Get a unique JWT auth token assigned just for you.
- Download SocketXP agent - https://www.socketxp.com/download/
- Login to connect to the SocketXP agent with the SocketXP Cloud Gateway, using the unique auth-token provided to you in the SocketXP Portal.
- Create Public URL for your localhost Jenkins instance with $ socketxp connect [http://localhost:port]
- Enter generated Public URL to GitHub repo webhook

## Setting up project in a corp env

- Create job (Freestyle, Maven, Pipeline, Multi-branch)
- Optional: Organise jobs into groups by creating sub-folders
- Set up credentials to access your source code
  - Shared credentials can be viable here
    - Can enable shared credentials on your Git project:
      - Project settings -> Repository -> Deploy Keys -> Enable shared credential
- Now, go to your Jenkins project to add Deploy Key:
  - Pipelines -> Pipeline/Definition -> Specify SSH remote url (hussaarm@github.com:<namespace>/project.git) and then select intended Deploy Key

## Infrastructure

- Jenkins has always had a master agent architecture, where master does all of the admin work, such as hosting the web UI, firing the job triggers, and storing the data.
- It is the agents that do the actual builds.
- Best practice to commission new agents for new requirements i.e. if a build requires JDK 8, instead of installing on an existing agent (as that may break multiple builds), just commission a new agent.

## Agents

- Builds should be agent compatable.
- Agents should be rotating and changing frequently.

## Debugging SCM Pipelines

- Can test via VS Code pipeline linter connector as documented above.
- Can also test via Jenkins "Replay"
  - Go to job - > Click on build from the history that you want to debug -> Click Replay
    - This will show the pipeline that was executed as part of this build, edit the pipeline and confirm.
      - Once you are satisfied, copy the code from Replay and commit to SCM.

## GitLab Multibranch Pipeline Project

- https://www.github.com/jenkinsci/gitlab-branch-source-plugin#multibranch-pipeline-jobs

## Jenkins Parallel Stages

- In Jenkins, it is possible to run your stages in parallel to help improve your build speeds and to also allow for cross-platform (i.e. Windows & Linux) builds. - https://www.jenkins.io/blog/2017/09/25/declarative-1/
- In Jenkins new Declarative Pipeline 13x, improvements have been made to paralle builds in declarative pipelines that support better coordination of sequential steps/stages - https://www.jenkins.io/blog/2018/07/02/whats-new-declarative-piepline-13x-sequential-stages/
- Example parallel build on Windows and Linux agents: ./Jenkinsfile-parallel
