version = "1.0.0"
description = "This is module_name"

plugins {
    id("com.android.library")
    kotlin("android")
    id("maven-publish")
    publish
}

android {
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        val javaVersion = JavaVersion.toVersion(libs.versions.java.get())
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
    kotlinOptions {
        jvmTarget = libs.versions.java.get()
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    lint {
        isWarningsAsErrors = true
        isAbortOnError = true
    }
}

dependencies {
    // Standard libs
    implementation(libs.androidx.activity)
    implementation(libs.androidx.core)
    implementation(libs.google.materialCore)
    implementation(libs.bundles.coroutines)

    // UI: Jetpack Compose
    implementation(libs.bundles.compose.basic)
    implementation(libs.bundles.compose.iconsViewModelCoil)
    debugImplementation(libs.bundles.compose.debug)

    // Your dependencies
    // ...

    // Test
    androidTestImplementation(libs.bundles.test.androidx)
    testImplementation(libs.test.junit)
}
