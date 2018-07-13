package com.naturalprogrammer.spring.lemon.exceptions;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.naturalprogrammer.spring.lemon.exceptions.handlers.AbstractExceptionHandler;
import com.naturalprogrammer.spring.lemon.exceptions.util.LexUtils;

@Configuration
@ComponentScan(basePackageClasses=AbstractExceptionHandler.class)
public class LemonExceptionsAutoConfiguration {

	private static final Log log = LogFactory.getLog(LemonExceptionsAutoConfiguration.class);

	public LemonExceptionsAutoConfiguration() {
		log.info("Created");
	}
	
	
	/**
	 * Configures ErrorResponseComposer if missing
	 */	
	@Bean
	@ConditionalOnMissingBean(ErrorResponseComposer.class)
	public <T extends Throwable>
	ErrorResponseComposer<T> errorResponseComposer(List<AbstractExceptionHandler<T>> handlers) {
		
        log.info("Configuring ErrorResponseComposer");       
		return new ErrorResponseComposer<T>(handlers);
	}
	

	/**
	 * Configures LemonUtils
	 */
	@Bean
	public LexUtils lexUtils(MessageSource messageSource) {

        log.info("Configuring LexUtils");       		
		return new LexUtils(messageSource);
	}
}
