package com.payclip.blaze.publication.tasks.publish.properties

import org.gradle.api.Project

internal fun Project.getGroupIdFromProperties(): String {
    val groupId = project.findProperty("groupId")?.toString()
    return groupId ?: throw Exception("Missing property \"groupId\" in gradle.properties")
}

internal fun Project.getArtifactIdFromProperties(): String {
    val artifactId = project.findProperty("artifactId")?.toString()
    return artifactId ?: throw Exception("Missing property \"artifactId\" in gradle.properties")
}

internal fun Project.getVersionFromProperties(): String {
    val version = project.findProperty("version")?.toString()
    return version ?: throw Exception("Missing property \"version\" in gradle.properties")
}

internal fun Project.getRepositoryFromProperties(): String {
    val repository = project.findProperty("repository")?.toString()
    return repository ?: throw Exception("Missing property \"repository\" in gradle.properties")
}
