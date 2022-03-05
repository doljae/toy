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
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")

    testImplementation("io.kotest:kotest-runner-junit5")
    testImplementation("io.kotest:kotest-assertions-core")
    testImplementation("io.kotest:kotest-property")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}