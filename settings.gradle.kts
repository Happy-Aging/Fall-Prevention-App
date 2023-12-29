pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven ("https://devrepo.kakao.com/nexus/content/groups/public/")
    }
}

rootProject.name = "happy_aging"
include(":app")
include(":data")
include(":presentation")
include(":di")
include(":domain")
include(":build-src")
include(":core")
