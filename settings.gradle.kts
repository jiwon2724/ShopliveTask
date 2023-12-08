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
    }
}

rootProject.name = "ShopliveTask"
include(":app")
include(":feature:marvel")
include(":domain")
include(":data")
include(":feature:search")
include(":feature:favorite")
include(":core:base")
