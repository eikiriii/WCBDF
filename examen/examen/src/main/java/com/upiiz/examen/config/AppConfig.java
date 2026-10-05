package com.upiiz.examen.config;

import com.upiiz.examen.application.UsuarioService;
import com.upiiz.examen.domain.ports.in.UsuarioUseCase;
import com.upiiz.examen.domain.ports.out.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public UsuarioUseCase usuarioUseCase(UsuarioRepository usuarioRepository){
        return new UsuarioService(usuarioRepository);
    }
}
