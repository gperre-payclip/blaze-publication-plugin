package com.payclip.blaze.publication.tasks.source

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.PublishArtifact
import org.gradle.api.artifacts.dsl.ArtifactHandler
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.bundling.Jar

internal fun Project.addSourcesJarTask() {
    val android = project.extensions.getByName("android")

    if (android is BaseExtension) {
        val sourcesJar = tasks.create("sourcesJar", Jar::class.java) {
            dependsOn(JavaPlugin.CLASSES_TASK_NAME)
            archiveClassifier.set("sources")
            from(android.sourceSets.getByName("main").java.srcDirs)
        }

        project.artifacts {
            archives(sourcesJar)
        }
    }
}

fun ArtifactHandler.archives(
    artifactNotation: Any
): PublishArtifact = add("archives", artifactNotation)
