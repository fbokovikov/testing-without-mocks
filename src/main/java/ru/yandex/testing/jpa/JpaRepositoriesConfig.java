package ru.yandex.testing.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author fbokovikov
 */
@Configuration
@EnableTransactionManagement
@EntityScan("ru.yandex.testing.jpa.entity")
@EnableJpaRepositories("ru.yandex.testing.jpa.repository")
public class JpaRepositoriesConfig {
}
