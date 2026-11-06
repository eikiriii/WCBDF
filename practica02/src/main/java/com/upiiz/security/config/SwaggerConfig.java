package com.upiiz.security.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Práctica 02",
                version = "1.0.0",
                description = "Documentación donde hacemos como que sabemos que hacemos",
                termsOfService = "Usalo bajo tu propio riesgo",
                contact = @Contact(
                        name = "Fernanda Salazar",
                        email = "fervanelau@gmail.com"
                ),
                license = @License(
                        name = "Con la licencia de dios y del profe"
                )
        ),
        servers = {
                @Server(description = "Local", url = "http://localhost:8080")
        }
)
@Configuration
public class SwaggerConfig {
}
