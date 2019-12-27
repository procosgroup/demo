package com.procosgroup.demo.config;
 
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories; 

@Configuration
@EnableJdbcRepositories
class JdbcConfiguration extends AbstractJdbcConfiguration {

 
}