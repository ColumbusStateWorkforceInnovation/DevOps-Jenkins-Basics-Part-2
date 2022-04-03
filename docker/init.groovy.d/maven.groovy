import hudson.tasks.Maven
import hudson.tasks.Maven.MavenInstallation;
import hudson.tools.InstallSourceProperty;
import hudson.tools.ToolProperty;
import hudson.tools.ToolPropertyDescriptor
import hudson.tools.ZipExtractionInstaller;
import hudson.util.DescribableList
import jenkins.model.Jenkins;

/* This runs at startup (since it's in init.groovy.d).  It installs maven */

def extensions = Jenkins.instance.getExtensionList(Maven.DescriptorImpl.class)[0]
List<MavenInstallation> installations = []
mavenTool = ['name': 'maven3', 'url': 'file:/usr/share/jenkins/ref/apache-maven-3.6.3-bin.tar.gz', 'subdir': 'apache-maven-3.6.3']
println("Setting up tool: ${mavenTool.name} ")
def describableList = new DescribableList<ToolProperty<?>, ToolPropertyDescriptor>()
def installer = new ZipExtractionInstaller(mavenTool.label as String, mavenTool.url as String, mavenTool.subdir as String);
describableList.add(new InstallSourceProperty([installer]))
installations.add(new MavenInstallation(mavenTool.name as String, "", describableList))
extensions.setInstallations(installations.toArray(new MavenInstallation[installations.size()]))
extensions.save()
