package com.upiiz.practicaII.config;

import com.upiiz.practicaII.application.HeroeService;
import com.upiiz.practicaII.domain.ports.in.HeroeUseCase;
import com.upiiz.practicaII.domain.ports.out.HeroeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public HeroeUseCase heroeUseCase(HeroeRepository heroeRepository){
        return new HeroeService(heroeRepository);
    }
}
