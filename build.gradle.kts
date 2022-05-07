plugins {
    java
    id("org.springframework.boot") version "2.6.7" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
    kotlin("jvm") version "1.6.21" apply false
    kotlin("plugin.spring") version "1.6.21" apply false
    kotlin("plugin.jpa") version "1.6.21" apply false
}
java.sourceCompatibility = JavaVersion.VERSION_17
allprojects {
    group = "com.example"
    version = "0.0.1-SNAPSHOT"

}

subprojects {
    apply(plugin = "io.spring.dependency-management")

    repositories {
        mavenCentral()
    }

    configure<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension> {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }
        dependencies {
            dependency("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
            dependency("io.kotest:kotest-runner-junit5:5.0.3")
            dependency("io.kotest:kotest-assertions-core:5.0.3")
            dependency("io.kotest:kotest-property:5.0.3")
        }
    }

//    configurations {
//        compileOnly {
//            extendsFrom(configurations.annotationProcessor.get())
//        }
//    }

    dependencies {
//        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//        implementation("org.springframework.boot:spring-boot-starter-web")
//        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//        implementation("org.jetbrains.kotlin:kotlin-reflect")
//        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//        compileOnly("org.projectlombok:lombok")
//        runtimeOnly("mysql:mysql-connector-java")
//        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
//        annotationProcessor("org.projectlombok:lombok")
//        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}


//tasks.withType<KotlinCompile> {
//    kotlinOptions {
//        freeCompilerArgs = listOf("-Xjsr305=strict")
//        jvmTarget = "17"
//    }
//}

//tasks.withType<Test> {
//    useJUnitPlatform()
//}
