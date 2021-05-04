plugins {
    id("com.android.application") apply false
    id("com.android.library") apply false
    kotlin("android") apply false
    id("io.gitlab.arturbosch.detekt") version "1.16.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
    id("com.github.ben-manes.versions") version "0.38.0"
}

allprojects {
    group = "com.github.owner.project-name"
    repositories {
        mavenCentral()
        google()
        jcenter()   // JCenter is at end of life
    }
}

subprojects {
    apply {
        plugin("io.gitlab.arturbosch.detekt")
        plugin("org.jlleitschuh.gradle.ktlint")
        plugin("com.github.ben-manes.versions")
    }

    ktlint {
        debug.set(false)
        // version.set("0.41.0")
        // version.set(libs.ktlint.core.get().versionConstraint.displayName)
        verbose.set(true)
        android.set(false)
        outputToConsole.set(true)
        ignoreFailures.set(false)
        enableExperimentalRules.set(true)
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }

    detekt {
        config = rootProject.files("config/detekt/detekt.yml")
        reports {
            html {
                enabled = true
                destination = file("build/reports/detekt.html")
            }
        }
    }
}

tasks {
    register("clean", Delete::class.java) {
        delete(rootProject.buildDir)
    }
    
    fun isStableVersion(version: String): Boolean {
        val upperCase = version.toUpperCase(java.util.Locale.ROOT)
        val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { it in upperCase }
        return stableKeyword || Regex("^[0-9,.v-]+(-r)?$").matches(version)
    }
    
    withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
        rejectVersionIf { !isStableVersion(candidate.version) }
    }
}
