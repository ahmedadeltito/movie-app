plugins {
    id(BuildPlugins.benManesVersionPlugin) version BuildPlugins.Versions.benManes
}

buildscript {
    repositories {
        google()
        mavenCentral()

    }
    dependencies {
        classpath(BuildPlugins.androidGradlePlugin)
        classpath(BuildPlugins.kotlinGradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean").configure {
    delete("build")
}