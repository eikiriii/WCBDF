package com.upiiz.hexagonal.config;

import com.upiiz.hexagonal.application.CategoriaService;
import com.upiiz.hexagonal.domain.ports.in.CategoriaUseCase;
import com.upiiz.hexagonal.domain.ports.out.CategoriaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    // frijolitos que se van a comer
    // instancias que la app va a usar

    // Adaptador de Salida - BD

    // Puerto de entrada - Rest
    @Bean
    public CategoriaUseCase categoriaUseCase(CategoriaRepository categoriaRepository){
        return new CategoriaService(categoriaRepository);
    }
}
