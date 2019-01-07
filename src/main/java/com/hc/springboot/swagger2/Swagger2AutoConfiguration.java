package com.hc.springboot.swagger2;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import( { Swagger2ImportBeanDefinitionRegistrar.class } )
public class Swagger2AutoConfiguration {}