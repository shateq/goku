import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.7.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "shateq.goku"
version = "1.0"
description = "Tournament computer"

repositories {
    mavenCentral()
    google()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8" // Must have!
        options.release.set(17)
    }

    jar {
        manifest.attributes["Main-Class"] = "shateq.java.goku.GokuMain"
    }

    processResources {
        filteringCharset = "UTF-8"
    }

    named("build") {
        doLast {
            File(buildDir, "libs/goku.bat").writeText("java -jar goku-$version.jar\npause")
            File(buildDir, "libs/goku").writeText("java -jar goku-$version.jar\npause")
        }
    }
}