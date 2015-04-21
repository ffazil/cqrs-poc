package com.tracebucket.x.cqrs.support;

import com.tracebucket.x.cqrs.annotation.PublishCommand;
import com.tracebucket.x.cqrs.command.Command;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;
import reactor.core.Reactor;

import java.beans.Introspector;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by ffl on 16-04-2015.
 */
@Aspect
@Component
public class PublishCommandAdvisor {
	private static Logger log = LoggerFactory.getLogger(PublishCommandAdvisor.class);

	@Autowired
	private Reactor commandBus;

	//Fetch command and formulate command name, use reactor and publish
	@Around(value = "@annotation(com.tracebucket.x.cqrs.annotation.PublishCommand) && !within(com.tracebucket.x.cqrs.annotation.PublishCommand)")
	public Object publish(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature ms = (MethodSignature) joinPoint.getSignature();
		Method m = ms.getMethod();
		Object[] arguments = joinPoint.getArgs();


		PublishCommand publishCommandAnnotation = AnnotationUtils.getAnnotation(m, PublishCommand.class);
		RequestMapping requestMappingAnnotation = AnnotationUtils.getAnnotation(m, RequestMapping.class);

		Object result = joinPoint.proceed();

		String commandName = null;
		commandName = publishCommandAnnotation.command();





		return result;


	}
}
