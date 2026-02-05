package co.edu.unicauca.asae.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST de Gestión de Clientes")
                        .version("1.2")
                        .description("API REST para la gestión de clientes, con inicio de sesión y registro."));
    }
}