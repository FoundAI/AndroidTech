pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        maven {setUrl("https://developer.huawei.com/repo/")}
        maven {setUrl("https://dl.google.com/dl/android/maven2/")}
        maven {setUrl("https://www.jitpack.io")}
        maven {setUrl("https://maven.aliyun.com/repository/google")}
        maven {setUrl("https://maven.aliyun.com/repository/jcenter")}
    }
}

rootProject.name = "AndroidTech"
include(":app")
 