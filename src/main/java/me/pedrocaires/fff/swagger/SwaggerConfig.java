package me.pedrocaires.fff.swagger;

import me.pedrocaires.fff.exception.ExceptionResponse;
import me.pedrocaires.fff.exception.FormValidationResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.*;
import springfox.documentation.schema.Example;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket fffApi() {

		return new Docket(DocumentationType.OAS_30).useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, defaultResponseMessages())
				.globalResponses(HttpMethod.POST, defaultResponseMessages())
				.globalResponses(HttpMethod.PUT, defaultResponseMessages())
				.globalResponses(HttpMethod.DELETE, defaultResponseMessages())
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
				.build().apiInfo(metaData()).securitySchemes(Collections.singletonList(apiKey()))
				.securityContexts(Collections.singletonList(securityContext()));

	}

	private ApiKey apiKey() {
		return new ApiKey("Authorization", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).operationSelector(operationContext -> true)
				.build();
	}

	private List<SecurityReference> defaultAuth() {
		var authorizationScope = new AuthorizationScope("global",
				"Identify you and your account to access restrict content");
		var authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Collections.singletonList(new SecurityReference("Authorization", authorizationScopes));
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("Free Feature Flag API").description("This is the first version of FFF api")
				.version("1.0.0").license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0.html").build();
	}

	private List<Response> defaultResponseMessages(){
		var responseList = new ArrayList<Response>();
		var mediaType = "application/json";

		responseList.add(new ResponseBuilder()
				.code("400")
				.examples(Collections.singletonList(
						new Example("1",
								"Bad Request",
								"Bad Request",
								Collections.singletonList(new FormValidationResponse("myField", "error description")),
								"1", mediaType))).build());

		responseList.add(new ResponseBuilder()
						.code("401")
						.examples(Collections.singletonList(
								new Example("2",
										"Unauthorized",
										"Unauthorized",
										new ExceptionResponse("Full authentication is required to access this resource"),
										"2", mediaType))).build());

		responseList.add(new ResponseBuilder()
				.code("4XX")
				.examples(Collections.singletonList(
						new Example("3",
								"Server Custom Exception",
								"Server Custom Exception",
								new ExceptionResponse("Custom exception description"),
								"4", mediaType))).build());

		responseList.add(new ResponseBuilder()
				.code("500")
				.examples(Collections.singletonList(
						new Example("5",
								"Internal Server Error",
								"Internal Server Error",
								new ExceptionResponse("Something unusual happened, please try again"),
								"5", mediaType))).build());
		return responseList;
	}

}
