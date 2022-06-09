package com.victorfranca.camundaformio.config;

import org.camunda.bpm.engine.rest.security.auth.ProcessEngineAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class CamundaSecurityConfig {

	/**
	 * Camunda REST API Basic Authentication configuration
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<Filter> processEngineAuthenticationFilter() {
		FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
		registration.setName("camunda-auth");
		registration.setFilter(getProcessEngineAuthenticationFilter());
		registration.addInitParameter("authentication-provider",
				"org.camunda.bpm.engine.rest.security.auth.impl.HttpBasicAuthenticationProvider");
		registration.addUrlPatterns("/engine-rest/*");
		return registration;
	}

	@Bean
	public Filter getProcessEngineAuthenticationFilter() {
		return new ProcessEngineAuthenticationFilter();
	}
}
