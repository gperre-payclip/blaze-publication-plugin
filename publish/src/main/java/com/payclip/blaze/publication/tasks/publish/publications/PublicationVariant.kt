package com.payclip.blaze.publication.tasks.publish.publications

import com.android.build.gradle.internal.crash.afterEvaluate
import com.payclip.blaze.publication.tasks.publish.credentials.getGithubRefName
import com.payclip.blaze.publication.tasks.publish.properties.getArtifactIdFromProperties
import com.payclip.blaze.publication.tasks.publish.properties.getGroupIdFromProperties
import com.payclip.blaze.publication.tasks.publish.properties.getVersionFromProperties
import org.gradle.api.PolymorphicDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.component.SoftwareComponent
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication

internal fun PublishingExtension.setPublication(
    project: Project,
    component: SoftwareComponent
) {
    publications {
        create<MavenPublication>(component.name) {
            groupId = project.getGroupIdFromProperties()
            version = getGithubRefName() ?: project.getVersionFromProperties()

            artifactId =  if (component.name != "release") {
                "${component.name}-${project.getArtifactIdFromProperties()}"
            } else {
                project.getArtifactIdFromProperties()
            }

            afterEvaluate {
                from(component)
                artifact(project.tasks.getByName("sourcesJar"))
            }
        }
    }
}

private inline fun <reified U : Any> PolymorphicDomainObjectContainer<in U>.create(
    name: String,
    noinline configuration: U.() -> Unit
) = this.create(name, U::class.java, configuration)
