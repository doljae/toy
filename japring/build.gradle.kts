plugins {
    id("org.springframework.boot")
}

val queryDsl = dependencyManagement.importedProperties["querydsl.version"]

dependencies {
    implementation(project(":toy-core"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("io.github.openfeign:feign-okhttp")

    runtimeOnly("mysql:mysql-connector-java")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("org.springdoc:springdoc-openapi-ui:1.6.9")
    implementation("io.reactivex.rxjava3:rxjava:3.1.5")

    implementation("com.h2database:h2")
    testImplementation("com.h2database:h2")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    implementation("com.querydsl:querydsl-jpa")
    annotationProcessor("com.querydsl:querydsl-apt:$queryDsl:jpa")
    annotationProcessor("jakarta.annotation:jakarta.annotation-api")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.clean {
    delete(rootProject.buildDir)
}