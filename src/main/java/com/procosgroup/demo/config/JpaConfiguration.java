package com.procosgroup.demo.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {"com.procosgroup.demo.repository"})
@EntityScan(basePackages = "com.procosgroup.demo.domain")
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaAuditing
public class JpaConfiguration {

}
