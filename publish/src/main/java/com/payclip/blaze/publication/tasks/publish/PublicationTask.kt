package com.payclip.blaze.publication.tasks.publish

import com.payclip.blaze.publication.tasks.publish.publications.setPublication
import com.payclip.blaze.publication.tasks.publish.repositories.setRepositories
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension

internal fun Project.addPublicationTask() {
    afterEvaluate {
        publishing {
            setRepositories(project)
        }
        components.forEach { component ->
            publishing {
                setPublication(project, component)
            }
        }
    }
}

private fun Project.publishing(
    configure: Action<PublishingExtension>
): Unit = (this as org.gradle.api.plugins.ExtensionAware).extensions.configure(
    "publishing",
    configure
)
