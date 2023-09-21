
node {
  stage('checkout sources') {
        // To avoid needing to set up permissions to your private repo, leave this url set to the assignment repo.  
        //For an extra challenge, try setting up the Jenkinsfile to pull from your repo instead
        // NEVER commit secrets (like credentials) to GitHub!!!
        git url: 'https://github.com/ColumbusStateWorkforceInnovation/Infrastructure-Automation-Jenkins-Basics-Part-2'
  }

  stage('Build') {
    // you should build this repo with a maven build step here
    echo "hello"
  }
  // you should add a test report here
}
