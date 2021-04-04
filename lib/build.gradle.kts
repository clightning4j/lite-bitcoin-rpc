plugins {
    id("org.jmailen.kotlinter") version "3.3.0"
    id("com.github.sherter.google-java-format") version "0.9"

    `java-library`
}

repositories {
    jcenter()
}

dependencies {
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.9.1"))
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    testImplementation("junit:junit:4.13")
}

tasks {
    register("fatJar", Jar::class.java) {
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
