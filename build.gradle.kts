import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.nio.charset.StandardCharsets

plugins {
    java
    kotlin("jvm") version "1.6.20"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "shateq.java"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    // withSourcesJar()
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    withType<JavaCompile> {
        options.encoding = StandardCharsets.UTF_8.name() // Must have!
        options.release.set(17)
    }

    withType<Jar> {
        manifest.attributes["Main-Class"] = "shateq.java.goku.GokuMain"
    }

    withType<ProcessResources> {
        filteringCharset = StandardCharsets.UTF_8.name()
    }
}


tasks.named("build") {
    doLast {
        File(buildDir, "libs/goku.bat").writeText("java -jar goku-$version.jar\npause")
        File(buildDir, "libs/goku").writeText("java -jar goku-$version.jar\npause")
    }
}