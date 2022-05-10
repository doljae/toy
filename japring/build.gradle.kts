plugins {
    java
    id("org.springframework.boot") version "2.6.7"
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    // implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("mysql:mysql-connector-java")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

//    testImplementation("org.mockito:mockito-core:4.5.1")
//    testImplementation("org.mockito:mockito-junit-jupiter:4.5.1")
//    testImplementation("net.bytebuddy:byte-buddy-agent:1.12.10")


    implementation("org.springdoc:springdoc-openapi-ui")
    implementation("io.reactivex.rxjava3:rxjava")
}

    tasks.withType<Test> {
    useJUnitPlatform()
}
