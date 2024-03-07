plugins {
    kotlin("jvm") version "1.9.22"
    id("fabric-loom") version "1.4-SNAPSHOT"
}

group = "com.neptuneclient"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:${project.properties["minecraft_version"]}")
    mappings("net.fabricmc:yarn:${project.properties["yarn_mappings"]}:v2")

    modImplementation("net.fabricmc:fabric-loader:${project.properties["loader_version"]}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${project.properties["fabric_version"]}")

    implementation(project(":"))

    implementation("org.lwjgl:lwjgl-nanovg:3.3.1")
    implementation("org.lwjgl:lwjgl-nanovg:3.3.1:natives-windows")
}

kotlin {
    jvmToolchain(17)
}

tasks.processResources {
    inputs.property("version", project.version)

    filesMatching("fabric.mod.json") {
        expand(mutableMapOf("version" to project.version))
    }
}
