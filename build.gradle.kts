plugins {
    java
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.github.revxrsal.zapper") version "1.0.3"
}

group = "com.xism4.ShieldMotd"
version = "1.1.0"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    maven("https://repo.codemc.io/repository/maven-snapshots/")
    maven("https://repo.papermc.io/repository/maven-public/")
    mavenCentral()
}

dependencies {
    implementation("net.elytrium:serializer:1.1.1")

    compileOnly("com.google.code.findbugs:annotations:3.0.1")
    compileOnly("com.google.code.findbugs:jsr305:3.0.1")
    compileOnly("io.github.waterfallmc:waterfall-api:1.20-R0.3-SNAPSHOT")
    compileOnly("net.kyori:adventure-text-serializer-gson:4.16.0")
    compileOnly("net.kyori:adventure-text-minimessage:4.16.0")
    compileOnly("it.unimi.dsi:dsiutils:2.7.3")
    compileOnly("com.mojang:brigadier:1.2.9")
    compileOnly("net.kyori:adventure-text-serializer-legacy:4.16.0")
}

zapper {
    libsFolder = "libraries"

    relocationPrefix = "com.xism4.shieldmotd.libs"

    repositories {
        maven("https://jitpack.io/")
        maven("https://oss.sonatype.org/content/groups/public/")
        includeProjectRepositories()
    }

    relocate("net.elytrium.serializer", "config")

    relocate("org.json", "json")
    relocate("org.jline", "jline")
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }

    shadowJar {
        minimize()
    }

    build {
        dependsOn(shadowJar)
    }
}
