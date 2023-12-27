package com.payclip.blaze.publication.tasks.publish.repositories

import com.payclip.blaze.publication.tasks.publish.credentials.getGithubActor
import com.payclip.blaze.publication.tasks.publish.credentials.getGithubToken
import com.payclip.blaze.publication.tasks.publish.properties.getRepositoryFromProperties
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.publish.PublishingExtension

internal fun PublishingExtension.setRepositories(project: Project) {
    repositories {
        setGithubPackagesRepository(project)
    }
}

private fun RepositoryHandler.setGithubPackagesRepository(project: Project) {
    maven {
        val uri = project.getRepositoryFromProperties()

        name = "GitHubPackages"
        url = project.uri(uri)
        credentials {
            username = getGithubActor()
            password = getGithubToken()
        }
    }
}
