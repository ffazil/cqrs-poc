package com.tracebucket.x.cqrs.support;

import com.tracebucket.x.cqrs.annotation.PublishCommand;
import com.tracebucket.x.cqrs.command.Command;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by ffl on 16-04-2015.
 */
@Aspect
@Component
public class CommandPublisher {
	private static Logger log = LoggerFactory.getLogger(CommandPublisher.class);

	@Around(value = "@annotation(com.tracebucket.x.cqrs.annotation.PublishCommand) && !within(com.tracebucket.x.cqrs.annotation.PublishCommand)")
	public Object publish(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature ms = (MethodSignature) joinPoint.getSignature();
		Method m = ms.getMethod();
		Object[] arguments = joinPoint.getArgs();

		CommandWrapper commandWrapper = (CommandWrapper) Arrays.asList(arguments).stream()
				.filter(a -> a instanceof CommandWrapper)
				.findFirst()
				.get();

		Command command = commandWrapper.getCommand();
		DeferredResult deferredResult = commandWrapper.getDeferredResult();

		PublishCommand annotation = AnnotationUtils.getAnnotation(m, PublishCommand.class);

		Object result = joinPoint.proceed();
		return result;


	}
}
