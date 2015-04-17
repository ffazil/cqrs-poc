package com.tracebucket.x.cqrs.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ReplyTo {

	/**
	 * An expression which evaluates to a key to which is sent the method return value.
	 * If empty, consumer will try to use {@link reactor.event.Event#getReplyTo()} header.
	 *
	 * @return The expression.
	 */
	String value() default "";

}
