import hudson.model.JDK
import hudson.tools.InstallSourceProperty
import hudson.tools.ZipExtractionInstaller

def descriptor = new JDK.DescriptorImpl();


def List<JDK> installations = []

javaTools=[['name':'jdk11', 'url':'file:/usr/share/jenkins/ref/amazon-corretto-11.0.14.10.1-linux-x64.tar.gz', 'subdir':'amazon-corretto-11.0.14.10.1-linux-x64']]

javaTools.each { javaTool ->

    println("Setting up tool: ${javaTool.name}")

    def installer = new ZipExtractionInstaller(javaTool.label as String, javaTool.url as String, javaTool.subdir as String);
    def jdk = new JDK(javaTool.name as String, null, [new InstallSourceProperty([installer])])
    installations.add(jdk)

}
descriptor.setInstallations(installations.toArray(new JDK[installations.size()]))
descriptor.save()
