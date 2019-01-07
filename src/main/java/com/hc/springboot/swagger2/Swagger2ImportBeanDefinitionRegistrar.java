package com.hc.springboot.swagger2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Predicates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Map;

public class Swagger2ImportBeanDefinitionRegistrar implements EnvironmentAware, ImportBeanDefinitionRegistrar {
	private static final Logger LOGGER = LoggerFactory.getLogger( Swagger2ImportBeanDefinitionRegistrar.class );
	private Swagger2Properties swagger2Properties = null;
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public void registerBeanDefinitions( AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry ) {
		try {
			if ( swagger2Properties == null || swagger2Properties.getDocketConfig() == null
					|| swagger2Properties.getDocketConfig().isEmpty() ) {
				LOGGER.error( "springfox swagger2 load docket config is empty" );
				return;
			}
			Map<String, Swagger2Properties.DocketConfig> docketConfig = swagger2Properties.getDocketConfig();

			int i = 0;
			for ( Map.Entry<String, Swagger2Properties.DocketConfig> entry : docketConfig.entrySet() ) {
				Swagger2Properties.DocketConfig _docketConfig = entry.getValue();
				BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition( Docket.class );

				Docket docket = new Docket( DocumentationType.SWAGGER_2 );
				ApiSelectorBuilder selectorBuilder = docket.select();
				if ( _docketConfig.getBasePackage() != null && !_docketConfig.getBasePackage().isEmpty() ) {
					for ( String basePackage : _docketConfig.getBasePackage() ) {
						selectorBuilder.apis( RequestHandlerSelectors.basePackage( basePackage ) );
					}
				}
				if ( _docketConfig.getPathsInclude() != null && !_docketConfig.getPathsInclude().isEmpty() ) {
					for ( String pathsInclude : _docketConfig.getPathsInclude() ) {
						selectorBuilder.paths( PathSelectors.regex( pathsInclude ) );
					}
				}
				if ( _docketConfig.getPathsExclude() != null && !_docketConfig.getPathsExclude().isEmpty() ) {
					for ( String pathsExclude : _docketConfig.getPathsExclude() ) {
						selectorBuilder.paths( Predicates.not( PathSelectors.regex( pathsExclude ) ) );
					}
				}
				selectorBuilder.build();

				builder.addConstructorArgValue( DocumentationType.SWAGGER_2 );
				builder.addPropertyValue( "apiInfo", swagger2Properties.getApiInfoByName( _docketConfig.getApiInfoName() ) );
				builder.addPropertyValue( "groupName", entry.getKey() );
				builder.addPropertyValue( "apiSelector", docket.getApiSelector() );
				registry.registerBeanDefinition( "docker" + System.currentTimeMillis() + "" + i, builder.getBeanDefinition() );
				i++;
			}
		} catch ( Exception e ) {
			LOGGER.error( "springfox swagger2 load docket config exception", e );
		}
	}

	@Override
	public void setEnvironment( Environment environment ) {
		String swaggerConfig = environment.getProperty( "springfox-swagger2.config" );
		try {
			if ( !StringUtils.isEmpty( swaggerConfig ) ) {
				this.swagger2Properties = objectMapper.readValue( swaggerConfig, Swagger2Properties.class );
			}
		} catch ( Exception e ) {
			LOGGER.error( "parse swagger2 config exception,config:{}", swaggerConfig, e );
		}

	}
}