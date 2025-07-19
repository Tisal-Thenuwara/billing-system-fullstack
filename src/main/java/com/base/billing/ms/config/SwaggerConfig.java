package com.base.billing.ms.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Billing Management API")
                .description("This is the API documentation for the Billing Management System.")
                .version("1.0.0")
                .contact(new Contact()
                    .name("Support Team")
                    .email("support@billingms.com")
                    .url("https://www.billingms.com"))
                .license(new License()
                    .name("Apache 2.0")
                    .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
            .externalDocs(new ExternalDocumentation()
                .description("Billing MS Documentation")
                .url("https://www.billingms.com/docs"));
    }
}
