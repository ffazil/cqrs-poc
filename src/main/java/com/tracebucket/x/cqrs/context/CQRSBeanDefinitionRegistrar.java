package com.tracebucket.x.cqrs.context;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;
import reactor.core.Environment;
import reactor.core.configuration.PropertiesConfigurationReader;
import reactor.function.Supplier;
import reactor.spring.factory.CreateOrReuseFactoryBean;
import com.tracebucket.x.cqrs.annotation.EnableCQRS;

import java.util.Map;

public class CQRSBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	private static final String DEFAULT_ENV_NAME = "reactorEnv";

	@Override
	public void registerBeanDefinitions(AnnotationMetadata meta, BeanDefinitionRegistry registry) {
		Map<String, Object> attrs = meta.getAnnotationAttributes(EnableCQRS.class.getName());


		// Create a root Enivronment
		if (!registry.containsBeanDefinition(DEFAULT_ENV_NAME)) {
			BeanDefinitionBuilder envBeanDef = BeanDefinitionBuilder.rootBeanDefinition(CreateOrReuseFactoryBean.class);
			envBeanDef.addConstructorArgValue(DEFAULT_ENV_NAME);
			envBeanDef.addConstructorArgValue(Environment.class);

			String envSupplierBean = (String) attrs.get("environmentSupplier");
			if (StringUtils.hasText(envSupplierBean)) {
				envBeanDef.addConstructorArgReference(envSupplierBean);
			} else {
				Supplier<Environment> envSupplier;
				final String profileName = (String) attrs.get("value");
				if (StringUtils.hasText(profileName)) {
					envSupplier = new Supplier<Environment>() {
						@Override
						public Environment get() {
							return new Environment(new PropertiesConfigurationReader(profileName));
						}
					};
				} else {
					envSupplier = new Supplier<Environment>() {
						@Override
						public Environment get() {
							return new Environment();
						}
					};
				}
				envBeanDef.addConstructorArgValue(envSupplier);
			}
			registry.registerBeanDefinition(DEFAULT_ENV_NAME, envBeanDef.getBeanDefinition());
		}

		// Create a ApplicationServiceAutoConfiguration
		if (!registry.containsBeanDefinition(ApplicationServiceAutoConfiguration.class.getName())) {
			BeanDefinitionBuilder autoConfigDef = BeanDefinitionBuilder
					.rootBeanDefinition(ApplicationServiceAutoConfiguration.class);
			registry.registerBeanDefinition(ApplicationServiceAutoConfiguration.class.getName(), autoConfigDef.getBeanDefinition());
		}
	}
}
