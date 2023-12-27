package com.payclip.blaze.publication

import com.payclip.blaze.publication.tasks.publish.addPublicationTask
import com.payclip.blaze.publication.tasks.publish.console.setPublicationPrint
import com.payclip.blaze.publication.tasks.source.addSourcesJarTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class BasePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.setPublicationPrint()
        project.addSourcesJarTask()
        project.addPublicationTask()
    }
}
