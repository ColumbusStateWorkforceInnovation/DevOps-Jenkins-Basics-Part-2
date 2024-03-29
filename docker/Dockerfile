FROM jenkins/jenkins:latest-jdk11

# This is the directory jenkins uses for most of its configuration
ENV JENKINS_HOME=/usr/share/jenkins/ref

# Normally when jenkins starts up you need to put in credentials from the logs
# For this use case we want to bypass that
ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

# Bootstrap jenkins with the plugins we want to possibly use
COPY --chown=jenkins:jenkins plugins.txt ${JENKINS_HOME}/plugins.txt
RUN jenkins-plugin-cli -f ${JENKINS_HOME}/plugins.txt

# Jenkins will run all of the scripts in init.groovy.d at startup, so we can do helpful
# things like install tools and create and run the seed job
COPY --chown=jenkins:jenkins init.groovy.d/ ${JENKINS_HOME}/init.groovy.d/

# Where jenkins jobs lie
COPY --chown=jenkins:jenkins jobs/ ${JENKINS_HOME}/jobs/

# Where we're putting our dsl job definitions.  In particular, your pipeline job will be here.
COPY --chown=jenkins:jenkins dsl/ ${JENKINS_HOME}/dsl

# Download maven to a location inside the docker image, so we can add a maven Tool location within jenkins (in init.groovy.d)
RUN curl -o ${JENKINS_HOME}/apache-maven-3.6.3-bin.tar.gz https://dlcdn.apache.org/maven/maven-3/3.9.4/binaries/apache-maven-3.9.4-bin.tar.gz

# Download Java to a location inside the docker image, so we can add a Java JDK Tool location within jenkins (in init.groovy.d)
RUN curl -o ${JENKINS_HOME}/amazon-corretto-11.0.14.10.1-linux-x64.tar.gz https://corretto.aws/downloads/resources/11.0.14.10.1/amazon-corretto-11.0.14.10.1-linux-x64.tar.gz
