package com.lugiatracker.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI configurarSwagger() {
        return new OpenAPI().info(new Info()
            .title("LugiaTracker")
            .description("Este projeto visa gerenciar as relações e funcionalidades entre os elementos "
                       + "motos, pátios, gerentes e mecânicos em uma aplicação de alto nível de maturidade, "
                       + "utilizando recursos do framework Spring Boot.")
            .summary("Gerenciamento de pátios e seus elementos.")
            .version("v1.0.0")
            .termsOfService("https://www.fiap.com.br/termos")
            .license(new License()
                .name("LugiaTracker")
                .url("https://www.fiap.com.br"))
        );
    }
}
