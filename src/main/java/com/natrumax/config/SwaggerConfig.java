package com.natrumax.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
<<<<<<< HEAD
                        .title("WMS-NATRUMAX API")
                        .version("1.0")
                        .description("API documentation for WMS-NATRUMAX System"));
=======
                        .title("User Management API")
                        .version("1.0")
                        .description("API documentation for User Management System"));
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
    }
}
