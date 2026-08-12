plugins {
    id("java-library")
    id("xyz.jpenilla.run-velocity") version "3.0.2"
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.codemc.io/repository/maven-releases/")
}

dependencies {
    compileOnly("com.velocitypowered:velocity-api:3.5.0-SNAPSHOT")
    compileOnly("com.github.retrooper:packetevents-velocity:2.13.0")

    implementation("net.kyori:adventure-nbt:4.26.1")

    compileOnly("org.projectpersistence:queue:1.1-SNAPSHOT")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

tasks {
    runVelocity {
        velocityVersion("3.5.0-SNAPSHOT")
    }

    processResources {
        val props = mapOf(
            "name" to rootProject.name,
            "version" to version
        )

        filesMatching("velocity-plugin.json") {
            expand(props)
        }
    }
}
