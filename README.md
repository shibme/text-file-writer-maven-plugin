# Text File Writer - Maven Plugin
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/me.shib.plugin/text-file-writer-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/me.shib.plugin/text-file-writer-maven-plugin)
[![Build Status](https://gitlab.com/shibme/text-file-writer-maven-plugin/badges/master/pipeline.svg)](https://gitlab.com/shibme/text-file-writer-maven-plugin/pipelines)

Maven plugin to write text files with specified content

* This [`README.md`](README.md) file itself was generated with text-file-writer-maven-plugin plugin. Refer to this project's [`pom.xml`](pom.xml) to know how it is done.
* Alternatively, you can also refer to the below sample:
```
<plugin>
<groupId>me.shib.plugin</groupId>
<artifactId>text-file-writer-maven-plugin</artifactId>
<version>1.0.0</version>
<executions>
<execution>
<phase>install</phase>
<goals><goal>text-file-writer</goal></goals>
<configuration>
<charset>UTF-8</charset>
<files>
<file>
<destination>README.md</destination>
<overwrite>true</overwrite>
<lines>
<line># Text File Writer - Maven Plugin</line>
<line>Maven plugin to write text files with specified content</line>
</lines>
</file>
</files>
</configuration>
</execution>
<executions>
</configuration>
</plugin>
```