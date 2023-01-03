package com.rviewer.skeletons.infrastructure.config;

import com.rviewer.skeletons.domain.repository.DispenserRepository;
import com.rviewer.skeletons.domain.services.DispenserService;
import com.rviewer.skeletons.domain.services.impl.DispenserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public DispenserService dispenserService(DispenserRepository dispenserRepository) {
        return new DispenserServiceImpl(dispenserRepository);
    }
}
