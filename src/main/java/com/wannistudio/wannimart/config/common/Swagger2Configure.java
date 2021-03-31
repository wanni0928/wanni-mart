package com.wannistudio.wannimart.config.common;

import com.wannistudio.wannimart.config.jwt.JwtTokenConfigure;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;
import static springfox.documentation.builders.RequestHandlerSelectors.withMethodAnnotation;

@Configuration
@EnableSwagger2
public class Swagger2Configure implements WebMvcConfigurer {

  private final JwtTokenConfigure jwtTokenConfigure;

  public Swagger2Configure(JwtTokenConfigure jwtTokenConfigure) {
    this.jwtTokenConfigure = jwtTokenConfigure;
  }

  @Bean
  public Docket restApi() {
    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .ignoredParameterTypes(AuthenticationPrincipal.class)
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
  public UiConfiguration uiConfig() {
    return UiConfigurationBuilder.builder()
            .supportedSubmitMethods(UiConfiguration.Constants.NO_SUBMIT_METHODS)
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

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
            .title("Wanni-Mart")
            .description("online shop Order management api")
            .license("Apache 2.0")
            .licenseUrl("http://springdoc.org")
            .contact(new Contact("choi hyung joong", "https://github.com/wanni0928", "chlgudwnd123@gmail.com"))
            .version("1.0.0")
            .build();
  }

  private ApiKey apiKey() {
    return new ApiKey("apiKey(Bearer {$your_apikey})", jwtTokenConfigure.getHeader(), "header");
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
