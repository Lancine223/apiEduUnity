package com.odk3.projet_tp_api.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiQuizOpenApi() {
        return new OpenAPI()
                .info(new Info().title("Quiz Api")
                        .description("Une api quiz pour les plateformes quiz")
                        .version("1.0.0"));
    }
}
