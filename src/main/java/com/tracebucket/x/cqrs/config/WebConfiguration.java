package com.tracebucket.x.cqrs.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author FFL
 * @since 11-03-2015
 */
@Configuration
@ComponentScan(basePackages = {"com.tracebucket.x.cqrs.web"})
public class WebConfiguration extends WebMvcConfigurerAdapter {


}
