group = "io.paradaux.busmanagement"
description = "Bus Management for Toronto City Data"
val projectVersion: String by project
version = projectVersion

plugins {
    application
    id("org.openjfx.javafxplugin") version "0.0.10"
    id("org.checkerframework") version "0.6.9"
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")

    implementation("org.controlsfx:controlsfx:11.1.1")

    implementation("com.dlsc.formsfx:formsfx-core:11.5.0")
    implementation("org.kordamp.bootstrapfx:bootstrapfx-core:0.4.0")
}

application {
    mainClass.set("io.paradaux.busmanagement.Main")
}

javafx {
    version = "11.0.2"
    modules("javafx.controls", "javafx.fxml")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}