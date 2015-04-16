package com.tracebucket.x.cqrs.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ffl on 16-04-2015.
 */
@Configuration
@ComponentScan(basePackages = {"com.tracebucket.x.cqrs.service.impl"})
public class ServiceConfig {
}
