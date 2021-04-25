plugins {
    id("com.github.sherter.google-java-format") version "0.9"
    `maven-publish`
    signing
    jacoco
    `java-library`
}
group = project.property("GROUP_ID")!!
version = project.property("VERSION")!!

repositories {
    jcenter()
}

dependencies {
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("ch.qos.logback:logback-core:1.2.3")
    implementation("org.slf4j:slf4j-api:1.7.25")

    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.9.1"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")
    implementation("com.google.code.gson:gson:2.8.6")

    testImplementation("junit:junit:4.13.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
}

tasks.test {
    reports {
        junitXml.isEnabled = true
        html.isEnabled = true
    }
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        xml.destination  = File("$buildDir/reports/jacoco/report.xml")
        csv.isEnabled = false
        html.isEnabled = false
    }
    executionData(File("$buildDir/jacoco/test.exec"))
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks {
    test {
        reports {
            junitXml.isEnabled = true
            html.isEnabled = true
        }
    }

    register("fatJar", Jar::class.java) {
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("all")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        from(
            configurations.runtimeClasspath.get()
                .onEach { println("add from dependencies: ${it.name}") }
                .map { if (it.isDirectory) it else zipTree(it) }
        )
        val sourcesMain = sourceSets.main.get()
        sourcesMain.allSource.forEach { println("add from sources: ${it.name}") }
        from(sourcesMain.output)
    }
}

tasks{

    create<Jar>("sourcesJar") {
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }

    create<Jar>("javadocJar") {
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("javadoc")
        from(sourceSets["main"].allSource)
    }

    withType<JavaCompile>().configureEach {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
        options.encoding = "ISO-8859-1"
    }

    withType<Jar>().configureEach {
        // add META-INF/LICENSE to all created JARs
        from("${rootDir}/LICENSE") {
            into("META-INF")
        }
    }
}

tasks.jar {
    manifest {
        attributes("Automatic-Module-Name" to project.property("MODULE_NAME").toString())
    }
    archiveBaseName.set(rootProject.name)
}

publishing {
    publications {
        create<MavenPublication>(rootProject.name) {
            from(components["java"])

            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])

            repositories {
                maven {
                    credentials {
                        username = System.getenv("MAVEN_USERNAME")
                        password = System.getenv("MAVEN_PASSWORD")
                    }
                    val releasesRepoUrl = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
                    val snapshotsRepoUrl = uri("https://oss.sonatype.org/content/repositories/snapshots/")
                    url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
                }
            }

            pom {
                name.set(rootProject.name)
                description.set("""
                    A (another) Lite RPC wrapper for Bitcoin Core RPC 1.0, that permitted to have
                    flexibility into making the request with different versions of Bitcoin Core without lost
                    compatibility during the update.
                    """.trimIndent())
                url.set("https://github.com/clightning4j/lite-bitcoin-rpc")

                licenses {
                    license {
                        name.set("GNU General Public License v2.0")
                        url.set("https://github.com/clightning4j/lite-bitcoin-rpc/blob/main/LICENSE")
                    }
                }

                developers {
                    developer {
                        id.set("vincenzopalazzo")
                        name.set("Vincenzo Palazzo")
                        email.set("vincenzopalazzodev@gmail.com")
                        url.set("https://github.com/vincenzopalazzo")
                        roles.addAll("developer")
                        timezone.set("Europe/Rome")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/clightning4j/lite-bitcoin-rpc.git")
                    developerConnection.set("scm:git:ssh://github.com:clightning4j/lite-bitcoin-rpc.git")
                    url.set("https://github.com/clightning4j/lite-bitcoin-rpc.git")
                }
            }
        }
    }
}

signing {
    isRequired = true
    sign(tasks["sourcesJar"], tasks["javadocJar"])
    sign(publishing.publications[rootProject.name])
}