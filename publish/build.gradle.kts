plugins {
    `java-gradle-plugin`
    `maven-publish`
    `kotlin-dsl`
}

dependencies {
    implementation("com.android.tools.build:gradle:8.1.0")
    implementation(kotlin("gradle-plugin", "1.8.10"))
    implementation(kotlin("android-extensions"))
    implementation(kotlin("script-runtime"))
}

gradlePlugin {
    plugins {
        create("publish") {
            id = "payclip.blaze.publish"
            implementationClass = "com.payclip.blaze.publication.BasePlugin"
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/ClipMX/mobile.android.blaze.publication-plugin")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

tasks.withType(AbstractPublishToMaven::class.java) {
    val projectGroup: String by project

    doLast {
        val dependencyText = "$projectGroup:${project.name}:${publication.version}"
        val versionText = """
        |${"/".repeat(dependencyText.length + 40)}
        |${"/".repeat(10)}${" ".repeat(10)}$dependencyText${" ".repeat(10)}${"/".repeat(10)}
        |${"/".repeat(dependencyText.length + 40)}
        """.trimMargin()

        println("\u001B[92m$versionText\u001B[0m")
    }
}
