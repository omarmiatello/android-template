version = "1.0.0"
description = "This is module_name"

plugins {
    id("java-library")
    kotlin("jvm")
    id("maven-publish")
    publish
}

dependencies {
    implementation(libs.bundles.coroutines)

    // Your dependencies
    // ...

    // Test
    testImplementation(libs.test.junit)
}

java {
    val javaVersion = JavaVersion.toVersion(libs.versions.java.get())
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}
