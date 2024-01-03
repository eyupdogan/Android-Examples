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

        maven {
            url = uri("https://raw.github.com/eyodohan/android-2023-maven-repo/main")
        }
    }
}

rootProject.name = "WikiSearchRepeat"
include(":app")
include(":RepositoryLib")
