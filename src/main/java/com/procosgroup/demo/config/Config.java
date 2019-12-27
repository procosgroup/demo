package com.procosgroup.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration 
public class Config {
		
	 /* @Bean
	  public AuditorAware<AuditableUser> auditorProvider() {
	    return new AuditorAwareImpl();
	  } /*/
}
