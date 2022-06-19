plugins {
    id("org.springframework.boot")
}

val queryDsl = dependencyManagement.importedProperties["querydsl.version"]

dependencies {
    implementation(project(":toy-core"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    runtimeOnly("mysql:mysql-connector-java")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("org.springdoc:springdoc-openapi-ui")
    implementation("io.reactivex.rxjava3:rxjava")

    implementation("com.h2database:h2")
    testImplementation("com.h2database:h2")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    implementation("com.querydsl:querydsl-jpa")
    implementation("com.querydsl:querydsl-apt:$queryDsl:jpa")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
