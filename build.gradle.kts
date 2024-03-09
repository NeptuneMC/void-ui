plugins {
    kotlin("jvm") version "1.9.22"
}

group = "com.neptuneclient"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}

tasks.compileKotlin {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
}

kotlin {
    jvmToolchain(17)
}