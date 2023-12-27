package com.payclip.blaze.publication.tasks.publish.console

import com.payclip.blaze.publication.tasks.publish.properties.getArtifactIdFromProperties
import com.payclip.blaze.publication.tasks.publish.properties.getGroupIdFromProperties
import com.payclip.blaze.publication.tasks.publish.properties.getVersionFromProperties
import org.gradle.api.Project
import org.gradle.api.publish.maven.tasks.AbstractPublishToMaven

private const val LARGE_DECORATION_BAR = 40
private const val SMALL_DECORATION_BAR = LARGE_DECORATION_BAR / 4

private const val SEPARATOR_SYMBOL = "/"
private const val SPACE_SYMBOL = " "

internal fun Project.setPublicationPrint() {
    tasks.withType(AbstractPublishToMaven::class.java) {
        val groupId = getGroupIdFromProperties()
        val artifactId = getArtifactIdFromProperties()
        val version = System.getenv("GITHUB_REF_NAME") ?: getVersionFromProperties()

        doLast {
            val dependencyText = "$groupId:$artifactId:$version"
            val versionText = """
            |${getLargeSeparator(dependencyText.length)}
            |${getSmallSeparator()}${getSpace()}$dependencyText${getSpace()}${getSmallSeparator()}
            |${getLargeSeparator(dependencyText.length)}
            """.trimMargin()

            println("\u001B[92m$versionText\u001B[0m")
        }
    }
}

private fun getLargeSeparator(length: Int): String {
    return SEPARATOR_SYMBOL.repeat(length + LARGE_DECORATION_BAR)
}

private fun getSmallSeparator(): String {
    return SEPARATOR_SYMBOL.repeat(SMALL_DECORATION_BAR)
}

private fun getSpace(): String {
    return SPACE_SYMBOL.repeat(SMALL_DECORATION_BAR)
}
