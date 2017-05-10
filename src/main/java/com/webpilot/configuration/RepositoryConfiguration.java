package com.webpilot.configuration;

/**
 * Description: Specify entity and repository packages to be scanned for SpringData use.
 * Date: 4/11/17
 *
 * @author RGH
 */
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.webpilot.domain"})
@EnableJpaRepositories(basePackages = {"com.webpilot.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
