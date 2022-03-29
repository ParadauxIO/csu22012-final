group = "io.paradaux.busmanagement"
description = "Bus Management for Toronto City Data"
val projectVersion: String by project
version = projectVersion

plugins {
    application
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    implementation("com.google.guava:guava:31.1-jre")
}

application {
    mainClass.set("io.paradaux.busmanagement.Main")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}