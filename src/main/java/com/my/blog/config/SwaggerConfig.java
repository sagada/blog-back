package com.my.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private String filterApiPath = "com.my.blog.web.controller";
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(filterApiPath))
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .apiInfo(apiInfo("블로그", "1.0.0"));
    }

    private ApiInfo apiInfo(String title, String version)
    {
        return new ApiInfo(
                title,
                "블로그 swagger",
                version,
                null,
                null,
                "blog",
                "www.sagada.com",
                new ArrayList<>());
    }
}
