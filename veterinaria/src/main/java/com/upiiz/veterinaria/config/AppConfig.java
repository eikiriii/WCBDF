package com.upiiz.veterinaria.config;

import com.upiiz.veterinaria.mascotas.application.MascotaService;
import com.upiiz.veterinaria.mascotas.domain.ports.in.MascotaUseCase;
import com.upiiz.veterinaria.mascotas.domain.ports.out.MascotaRepository;
import com.upiiz.veterinaria.medicamento.application.MedicamentoService;
import com.upiiz.veterinaria.medicamento.domain.ports.in.MedicamentoUseCase;
import com.upiiz.veterinaria.medicamento.domain.ports.out.MedicamentoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MascotaUseCase mascotaUseCase(MascotaRepository mascotaRepository){
        return new MascotaService(mascotaRepository);
    }

    @Bean
    public MedicamentoUseCase medicamentoUseCase(MedicamentoRepository medicamentoRepository){
        return new MedicamentoService(medicamentoRepository);
    }
}
