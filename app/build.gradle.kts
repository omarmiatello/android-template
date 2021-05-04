plugins {
    id("com.android.application")
    kotlin("android")
    // kotlin("kapt")
}

android {
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        applicationId = "com.example"
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        compose = true
    }
    kotlinOptions {
        jvmTarget = libs.versions.java.get()
        freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn" // for Jetpack Compose
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.compose.compiler.get().versionConstraint.displayName
    }
    compileOptions {
        val javaVersion = JavaVersion.toVersion(libs.versions.java.get())
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
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
        disable(
            "ObsoleteLintCustomCheck",
            "UnsafeExperimentalUsageError",
            "UnsafeExperimentalUsageWarning"
        )

        isWarningsAsErrors = true
        isAbortOnError = true
    }

    /**
     * Use this block to configure different flavors
     */
    // flavorDimensions("version")
    // productFlavors {
    //     create("full") {
    //         dimension = "version"
    //         applicationIdSuffix = ".full"
    //     }
    //     create("demo") {
    //         dimension = "version"
    //         applicationIdSuffix = ".demo"
    //     }
    // }
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
