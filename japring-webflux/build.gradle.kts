plugins {
    id("org.springframework.boot")
}

dependencies {
    implementation(project(":toy-core"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    // https://stackoverflow.com/questions/71966221/spring-api-gateway-m1-java-lang-unsatisfiedlinkerror-no-netty-resolver-dns-n
    // https://github.com/netty/netty/issues/11020
    implementation("io.netty:netty-all")

    runtimeOnly("mysql:mysql-connector-java")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("io.reactivex.rxjava3:rxjava")

    implementation("com.h2database:h2")
    testImplementation("com.h2database:h2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
