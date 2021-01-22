package me.study.shop.config;

import static java.util.Collections.*;
import static springfox.documentation.builders.RequestHandlerSelectors.*;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class SwaggerConfiguration {

	private final JwtTokenConfig jwtTokenConfig;

	@Bean
	public Docket restApi() {
		return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(apiInfo())
			.ignoredParameterTypes(AuthenticationPrincipal.class, Pageable.class)
			.securitySchemes(singletonList(apiKey()))
			.securityContexts(singletonList(securityContext()))
			.produces(singleton("application/json"))
			.consumes(singleton("application/json"))
			.useDefaultResponseMessages(false)
			.select()
			.apis(withMethodAnnotation(ApiOperation.class))
			.build();
	}

	@Bean
	public SecurityConfiguration security() {
		return SecurityConfigurationBuilder.builder()
			.scopeSeparator(",")
			.additionalQueryStringParams(null)
			.useBasicAuthenticationWithAccessCodeGrant(false)
			.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("Social-Server")
			.contact(new Contact("username", null, "your-email@address.com"))
			.version("1.0.0")
			.build();
	}

	private ApiKey apiKey() {
		return new ApiKey("apiKey", jwtTokenConfig.getHeader(), "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
			.securityReferences(securityReference())
			.forPaths(PathSelectors.any())
			.build();
	}

	private List<SecurityReference> securityReference() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return singletonList(new SecurityReference("apiKey", authorizationScopes));
	}
}
