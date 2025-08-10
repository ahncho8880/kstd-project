package com.kstd.project.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
            .info(new Info()
                .title("Kstd Project WebFlux API")
                .version("1.0.0")
                .description("Spring WebFlux 기반 API 문서")
                .contact(new Contact().name("안철현"))
            );
    }
}
