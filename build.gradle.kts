import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

plugins {
    java
    id("org.springframework.boot") version "2.6.7" apply false
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
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")

    java.sourceCompatibility = JavaVersion.VERSION_17
    val springCloudVersion = "2021.0.2"

    repositories {
        mavenCentral()
    }

    configure<DependencyManagementExtension> {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}")
        }
        dependencies {
            dependency("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
            dependency("io.kotest:kotest-runner-junit5:5.0.3")
            dependency("io.kotest:kotest-assertions-core:5.0.3")
            dependency("io.kotest:kotest-property:5.0.3")
            dependency("org.springdoc:springdoc-openapi-ui:1.6.8")
            dependency("io.reactivex.rxjava3:rxjava:3.1.4")
            dependency("com.h2database:h2:2.1.212")
//            dependency("org.mockito:mockito-core:4.5.1")
//            dependency("org.mockito:mockito-junit-jupiter:4.5.1")
//            dependency("net.bytebuddy:byte-buddy-agent:1.12.10")
        }
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    dependencies {
        // lombok
        compileOnly("org.projectlombok:lombok")
        testCompileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
        testAnnotationProcessor("org.projectlombok:lombok")
    }
}