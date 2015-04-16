package com.tracebucket.x.cqrs.annotation;

/**
 * Created by ffl on 16-04-2015.
 */
public @interface PublishCommand {
	public String command() default "";
}
