package com.tracebucket.x.cqrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by ffl on 16-04-2015.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.tracebucket.x.cqrs.config")
public class CQRSStarter {
	public static void main(String[] args) {
		SpringApplication.run(CQRSStarter.class, args);
	}
}
