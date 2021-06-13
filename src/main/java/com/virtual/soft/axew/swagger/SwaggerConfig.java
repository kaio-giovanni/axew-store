package com.virtual.soft.axew.swagger;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI () {
        return new OpenAPI()
                .info(new Info()
                        .title("Axew Store API")
                        .version("1.0.0")
                        .description("Axew Store API to make connection with frontend")
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")));
    }

    @Bean
    public OperationCustomizer customGlobalHeaders () {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            operation.addParametersItem(makeAuthorization());

            return operation;
        };
    }

    private Parameter makeAuthorization () {
        return new Parameter().in(ParameterIn.HEADER.toString())
                .name("Jwt Token")
                .description("Authorization token")
                .schema(new StringSchema())
                .required(true);
    }
}