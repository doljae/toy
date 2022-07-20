package com.example.containers;

import java.util.stream.Stream;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

public abstract class AbstractTestContainers {

    private static final MySQLContainer<?> MYSQL_CONTAINER =
            new MySQLContainer<>(DockerImageName.parse("mysql:8.0.29"))
                    .withDatabaseName("toy")
                    .withCommand("--character-set-server=utf8mb4",
                                 "--collation-server=utf8mb4_unicode_ci")
                    .withUsername("user")
                    .withPassword("user")
                    .withExposedPorts(3306);

    private static final GenericContainer<?> REDIS_CONTAINER =
            new GenericContainer<>(DockerImageName.parse("redis:7.0.3"))
                    .withExposedPorts(6379);

    static {
        Stream.of(MYSQL_CONTAINER, REDIS_CONTAINER)
              .parallel()
              .forEach(GenericContainer::start);
    }

    @DynamicPropertySource
    static void dbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MYSQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MYSQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MYSQL_CONTAINER::getPassword);
//        registry.add("spring.datasource.driver-class-name", MYSQL_CONTAINER::getDriverClassName);
//        registry.add("spring.jpa.database-platform", () -> "org.hibernate.dialect.MySQL5InnoDBDialect");

        registry.add("spring.redis.host",REDIS_CONTAINER::getHost);
        registry.add("spring.redis.port",REDIS_CONTAINER::getFirstMappedPort);
    }
}
