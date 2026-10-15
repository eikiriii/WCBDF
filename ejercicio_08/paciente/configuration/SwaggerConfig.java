package com.upiiz.ejercicio_08.paciente.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Ejercicio 08",
                version = "1.0.0",
                description = "Documentación donde hacemos como que sabemos que hacemos",
                termsOfService = "Usalo bajo tu propio riesgo",
                contact = @Contact(
                        name = "Ferchiwinguis",
                        email = "fer@correo.com"
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
