import java.nio.charset.StandardCharsets

plugins {
    id("java")
    kotlin("jvm") version "1.6.20"

    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "shateq.java"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:23.0.0")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    // withSourcesJar()
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }

    compileJava {
        options.encoding = StandardCharsets.UTF_8.name() // Must have!
        options.release.set(17)
    }

    jar {
        manifest.attributes["Main-Class"] = "shateq.java.goku.GokuMain"
    }

    processResources {
        filteringCharset = StandardCharsets.UTF_8.name()
    }
}