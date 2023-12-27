val projectGroup: String by project
val libraryVersion: String by project

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
    group = projectGroup
    version = libraryVersion
}
