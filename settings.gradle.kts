pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "NoteApp"
include(":app:")
include(":core-ui")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":feature-login")
include(":firebase")
include(":firebase:authentication")
include(":navigation")
include(":utils")
include(":utils:injection")
include(":feature-home")
