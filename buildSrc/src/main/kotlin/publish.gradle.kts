/**
 * To use it just apply:
 *
 * plugins {
 *     publish
 * }
 */
plugins {
    id("maven-publish")
    id("signing")
    id("org.jetbrains.dokka")
}

group = "com.github.owner.project-name"

val dokkaJar = tasks.create<Jar>("dokkaJar") {
    group = "documentation"
    archiveClassifier.set("javadoc")
    from(tasks.findByName("dokkaHtml"))
}

val sourcesJar = tasks.register<Jar>("sourcesJar") {
    group = "build"
    description = "Assembles Source jar for publishing"
    archiveClassifier.set("sources")
    if (plugins.hasPlugin("com.android.library")) {
        from(
            (project.extensions.getByName("android") as com.android.build.gradle.LibraryExtension).sourceSets.named(
                "main"
            ).get().java.srcDirs
        )
    } else {
        from(
            (project.extensions.getByName("sourceSets") as SourceSetContainer).named("main")
                .get().allSource
        )
    }
}

afterEvaluate {
    publishing {
        repositories {
            maven {
                name = "nexus"
                url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = getPropertyOrNull("NEXUS_USERNAME")
                    password = getPropertyOrNull("NEXUS_PASSWORD")
                }
            }
            maven {
                name = "snapshot"
                url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots")
                credentials {
                    username = getPropertyOrNull("NEXUS_USERNAME")
                    password = getPropertyOrNull("NEXUS_PASSWORD")
                }
            }
        }

        publications {
            create<MavenPublication>("release") {
                if (plugins.hasPlugin("com.android.library")) {
                    from(components["release"])
                } else {
                    from(components["java"])
                }
                artifact(dokkaJar)
                artifact(sourcesJar)

                pom {
                    if (getPropertyOrNull("USE_SNAPSHOT") != null) {
                        version = "$version-SNAPSHOT"
                    }
                    name.set("project-name:$artifactId")
                    description.set(project.description)
                    url.set("https://github.com/owner/project-name/")

                    licenses {
                        license {
                            name.set("The MIT License")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }
                    developers {
                        developer {
                            id.set("owner")
                            name.set("fullname")
                            email.set("emailaddress")
                        }
                    }
                    scm {
                        connection.set("scm:git:git://github.com/owner/project-name.git")
                        developerConnection.set("scm:git:ssh://github.com/owner/project-name.git")
                        url.set("https://github.com/owner/project-name/")
                    }
                    issueManagement {
                        system.set("GitHub Issues")
                        url.set("https://github.com/owner/project-name/issues")
                    }
                }
            }
        }

        val signingKey = getPropertyOrNull("SIGNING_KEY")
        val signingPwd = getPropertyOrNull("SIGNING_PWD")
        if (signingKey == null || signingPwd == null) {
            logger.info("Signing Disable as the PGP key was not found")
        } else {
            logger.warn("Using SIGNING_KEY and SIGNING_PWD")
            signing {
                useInMemoryPgpKeys(signingKey, signingPwd)
                sign(publishing.publications["release"])
            }
        }
    }
}

fun getPropertyOrNull(property: String) = (findProperty(property) as? String)
        ?.takeIf { it.isNotBlank() }

