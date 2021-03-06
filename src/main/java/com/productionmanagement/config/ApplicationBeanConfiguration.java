package com.productionmanagement.config;

import com.productionmanagement.mappings.MappingsInitializer;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    private static ModelMapper mapper;

    static {
        mapper = new ModelMapper();
        MappingsInitializer.initMappings((mapper));
    }

    @Bean
    public ModelMapper modelMapper() {
        return mapper;
    }

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
}
