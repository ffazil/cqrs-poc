package com.tracebucket.x.cqrs.config;

import com.tracebucket.x.cqrs.annotation.EnableCQRS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;

/**
 * Created by ffl on 16-04-2015.
 */
@Configuration
@EnableCQRS
@ComponentScan(basePackages = {"com.tracebucket.x.cqrs.support"})
public class InfrastructureConfig {

	@Bean
	public Reactor commandBus(Environment env) {
		// implicit Environment is injected into bean def method
		return Reactors.reactor().env(env).get();
	}
}
