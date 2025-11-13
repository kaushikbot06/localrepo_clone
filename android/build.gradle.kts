allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

val newBuildDir: Directory = rootProject.layout.buildDirectory.dir("../../build").get()
rootProject.layout.buildDirectory.value(newBuildDir)

subprojects {
    val newSubprojectBuildDir: Directory = newBuildDir.dir(project.name)
    project.layout.buildDirectory.value(newSubprojectBuildDir)
}
subprojects {
    project.evaluationDependsOn(":app")
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}

buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.1")
    }
}
plugins {
  // ...

  // Add the dependency for the Google services Gradle plugin
  id("com.android.application")
  id("com.google.gms.google-services")
  id("com.google.gms.google-services") version "4.4.4" apply false
}
implementation(platform("com.google.firebase:firebase-bom:34.5.0"))

apply(plugin = "com.google.gms.google-services")


