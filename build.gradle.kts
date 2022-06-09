import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

plugins {
    java
    id("org.springframework.boot") version "2.7.0" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
    kotlin("jvm") version "1.6.21" apply false
    kotlin("plugin.spring") version "1.6.21" apply false
    kotlin("plugin.jpa") version "1.6.21" apply false
}

allprojects {
    group = "com.example"
    version = "0.0.1-SNAPSHOT"
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    java.sourceCompatibility = JavaVersion.VERSION_17
    val springCloudVersion = "2021.0.2"

    repositories {
        mavenCentral()
    }

    configure<DependencyManagementExtension> {
        imports {

        }
        dependencies {

        }
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    dependencies {

        implementation(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
        implementation(platform("org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"))

        // lombok
        compileOnly("org.projectlombok:lombok")
        testCompileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
        testAnnotationProcessor("org.projectlombok:lombok")

        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
        implementation("io.kotest:kotest-runner-junit5:5.0.3")
        implementation("io.kotest:kotest-assertions-core:5.0.3")
        implementation("io.kotest:kotest-property:5.0.3")
        implementation("org.springdoc:springdoc-openapi-ui:1.6.8")
        implementation("io.reactivex.rxjava3:rxjava:3.1.4")
        implementation("com.h2database:h2:2.1.212")
    }
}