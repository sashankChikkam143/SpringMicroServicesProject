package com.infy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;

@SpringBootApplication
public class WeCareApplication {
    @Bean
    public ModelMapper mapper()
    {
        return new ModelMapper();
    }

	public static void main(String[] args) {
		SpringApplication.run(WeCareApplication.class, args);
	}

}
