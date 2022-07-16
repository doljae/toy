import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot")
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.spring") version "1.7.10"
    kotlin("plugin.jpa") version "1.7.10"
}

extra["kotestExtensionSpringVersion"] = "1.1.1"
extra["kotlinJdslVersion"] = "2.0.4.RELEASE"

dependencies {

    // web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // persistent
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.linecorp.kotlin-jdsl:spring-data-kotlin-jdsl-starter:${property("kotlinJdslVersion")}")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java")
    // implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    // implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    // implementation("org.springframework.boot:spring-boot-starter-data-redis")
    // implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:${property("kotestExtensionSpringVersion")}")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:mysql")
    testImplementation("io.projectreactor:reactor-test")
    // testImplementation("org.testcontainers:mongodb")


    // maintenance
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // serialization
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // reactive
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    // etc
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
