group = "io.paradaux.busmanagement"
description = "Bus Management for Toronto City Data"
val projectVersion: String by project
version = projectVersion

plugins {
    application
    id("org.checkerframework") version "0.6.9"
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

application {
    mainClass.set("io.paradaux.busmanagement.Main")

}

tasks.named<Test>("test") {
    useJUnitPlatform()
}