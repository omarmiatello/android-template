version = "1.0.0"
description = "This is module_name"

plugins {
    id("java-library")
    kotlin("jvm")
    id("maven-publish")
    publish
}

dependencies {
    testImplementation(Lib.testJunit)
}

java {
    sourceCompatibility = JavaVersion.toVersion(Version.java)
    targetCompatibility = JavaVersion.toVersion(Version.java)
}
