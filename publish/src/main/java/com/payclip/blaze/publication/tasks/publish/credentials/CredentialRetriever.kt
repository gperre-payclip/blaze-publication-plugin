package com.payclip.blaze.publication.tasks.publish.credentials

internal fun getGithubRefName(): String? {
    return System.getenv("GITHUB_REF_NAME")
}

internal fun getGithubActor(): String? {
    return System.getenv("GITHUB_ACTOR")
}

internal fun getGithubToken(): String? {
    return System.getenv("GITHUB_TOKEN")
}
