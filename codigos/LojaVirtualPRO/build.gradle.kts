plugins {
    kotlin("jvm") version "2.0.10"
}

group = "com.aasjunior"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.h2database:h2:1.4.200")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(18)
}