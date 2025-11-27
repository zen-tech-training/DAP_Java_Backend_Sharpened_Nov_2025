package com.zensar.stockapp;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@SpringBootApplication
public class StockAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockAppApplication.class, args);
	}
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	//@Bean
	  public OpenAPI customOpenAPI() {
	    return new OpenAPI()
	            .components(new Components().addSecuritySchemes("bearer-jwt",
	                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("jwt")
	                    .in(SecurityScheme.In.HEADER).name("Authorization")))
	            .info(new Info().title("App API").version("snapshot"))
	            .addSecurityItem(
	                    new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write")));
	  }
	
}
