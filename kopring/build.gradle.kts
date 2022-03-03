tasks.getByName<Jar>("jar") {
    enabled = true
}

tasks.getByName<Jar>("bootJar") {
    enabled = false
}

plugins {
    kotlin("jvm") version "1.6.10"
}

dependencies {
    implementation(kotlin("stdlib"))
}