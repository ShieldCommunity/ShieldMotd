plugins {
    id("project.common-conventions")
    alias(libs.plugins.shadow)
}

repositories {
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    compileOnly(libs.spigot)
    compileOnly(libs.placeholder)
}

tasks {
    shadowJar {
        archiveBaseName.set("ShieldMotd")
        destinationDirectory.set(file("$rootDir/bin/"))
        minimize()
    }

    clean {
        delete("${rootDir}/bin/")
    }
}