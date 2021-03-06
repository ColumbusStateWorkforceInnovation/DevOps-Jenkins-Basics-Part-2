pipelineJob('build-pipeline-job') { 

  // This is the repo that contains the Jenkinsfile you want to use to define your pipeline
  // From the Jenkins UI, you can easily add your credentials for this repo to the pipeline to test.
  // For an extra challenge, try configuring this job to include your credential object
  // NEVER commit secrets (like credentials) to GitHub!!!
  def repo = '[insert-your-repo-url]'

  description("Your App Pipeline") 
  keepDependencies(false) 

  properties{ 
    githubProjectUrl (repo) 
    rebuild { 
      autoRebuild(false) 
    } 
  } 

  definition { 
    cpsScm { 
      scm { 
        git { 
          remote { url(repo) } 
          branches('master') 
          scriptPath('Jenkinsfile') // The location of the Jenkinsfile that defines the pipeline job within the repo
          extensions { }  // required as otherwise it may try to tag the repo, which you may not want 
        } 
      } 
    } 
  }
}
