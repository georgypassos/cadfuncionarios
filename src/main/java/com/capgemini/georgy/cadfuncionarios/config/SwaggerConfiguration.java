package com.capgemini.georgy.cadfuncionarios.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.capgemini.georgy.cadfuncionarios.config.jwt.JwtSecurityConstants;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	private ApiInfo apiInfo() {
		return new ApiInfo("CAD Funcionários API", "API responsável pelo Gerenciamento do cadastro de Funcionários.", "1.0", "Terms of service",
				new Contact("Georgy Passos", "https://github.com/georgypassos/", "georgy1101@gmail.com"),
				"Link do projeto", "https://github.com/georgypassos/cadfuncionarios", Collections.emptyList());
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.capgemini.georgy.cadfuncionarios"))
				.paths(PathSelectors.any()).build();
	}
	
	private ApiKey apiKey() {
		return new ApiKey("JWT", JwtSecurityConstants.HEADER_STRING, "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}

}
