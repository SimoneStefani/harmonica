import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    base
    kotlin("jvm") version "1.7.10"
    `maven-publish`
}

group = "dev.simonestefani"
version = "1.0.1"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    api("com.github.cesarferreira:kotlin-pluralizer:1.0.0")
    api("io.github.classgraph:classgraph:4.8.146")
    api("org.jetbrains.kotlin:kotlin-reflect:1.7.10")

    testImplementation("io.kotest:kotest-runner-junit5:5.4.2")
    testImplementation("io.kotest:kotest-assertions-core:5.4.2")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
}

publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
            artifact(sourcesJar.get())
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
        }
    }
}
