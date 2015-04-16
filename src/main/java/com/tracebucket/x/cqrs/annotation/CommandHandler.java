/*
 * Copyright (c) 2011-2013 GoPivotal, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tracebucket.x.cqrs.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CommandHandler {

	/**
	 * An expression that evaluates to a {@link reactor.event.selector.Selector} to register
     * this handler with the {@link
	 * reactor.core.Reactor}.
	 * If empty, consumer will be subscribed on the global reactor selector
	 * {@link reactor.core.Reactor#on(reactor.event.selector.Selector selector, reactor.function.Consumer)}
	 *
	 * @return An expression to be evaluated.
	 */
	String value() default "";

	/**
	 * An expression that evaluates to the {@link reactor.core.Reactor} on which to place this handler.
	 *
	 * @return An expression to be evaluated.
	 */
	String reactor() default "reactor";

	/**
	 * The type of {@link reactor.event.selector.Selector} to register.
	 *
	 * @return The type of the {@link reactor.event.selector.Selector}.
	 */
	SelectorType type() default SelectorType.OBJECT;

}
