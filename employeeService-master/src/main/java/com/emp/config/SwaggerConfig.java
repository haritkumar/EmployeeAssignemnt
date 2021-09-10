package com.emp.config;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  private static final Set<String> DEFAULT_PRODUCES_CONSUMES = new HashSet<String>(Arrays.asList("application/json"));

  
  @Bean
  public Docket newsApi() {
      return new Docket(DocumentationType.SWAGGER_2)
              .select()
              .apis(RequestHandlerSelectors.basePackage("com.emp.controller"))
              .paths(PathSelectors.any())
              .build()
              .securitySchemes(Lists.newArrayList(apiKey()))
              .securityContexts(Lists.newArrayList(securityContext()))
              .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
      return new ApiInfoBuilder()
              .title("Sig-Predict REST API Document")
              .description("work in progress")
              .termsOfServiceUrl("localhost")
              .version("1.0")
              .build();
  }
  
  @Bean
  SecurityContext securityContext() {
      return SecurityContext.builder()
              .securityReferences(defaultAuth())
              .forPaths(PathSelectors.any())
              .build();
  }

  List<SecurityReference> defaultAuth() {
      AuthorizationScope authorizationScope
              = new AuthorizationScope("global", "accessEverything");
      AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
      authorizationScopes[0] = authorizationScope;
      return Lists.newArrayList(
              new SecurityReference("JWT", authorizationScopes));
  }

  private ApiKey apiKey() {
      return new ApiKey("JWT", "Authorization", "header");
  }
}