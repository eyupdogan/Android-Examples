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
            url = uri("https://raw.github.com/oguzkaran/android-mar-2023-maven-repo/main")
        }
    }
}

rootProject.name = "006-MultipleActivity"
include(":app")
include(":DataBindingConverterModule")
