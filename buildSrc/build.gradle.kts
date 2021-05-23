plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    jcenter()   // JCenter is at end of life
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
    implementation("com.android.tools.build:gradle:7.1.0-alpha01")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.4.30")
    implementation("org.jetbrains.dokka:dokka-core:1.4.30")
}