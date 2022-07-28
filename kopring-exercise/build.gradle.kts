import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot")
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.spring") version "1.7.10"
    kotlin("plugin.jpa") version "1.7.10"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    runtimeOnly("mysql:mysql-connector-java")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("com.h2database:h2")
    testImplementation("com.h2database:h2")

    implementation("com.jaredrummler:ktsh:1.0.0")

    implementation("org.springframework.statemachine:spring-statemachine-starter:3.2.0")

    implementation("io.github.nsk90:kstatemachine:0.9.5")
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

springBoot {
    mainClass.set("com.example.KopringExerciseApplication.kt")
}