package com.infy;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
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
