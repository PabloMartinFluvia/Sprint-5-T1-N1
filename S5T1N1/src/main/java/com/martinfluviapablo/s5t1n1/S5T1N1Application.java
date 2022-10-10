package com.martinfluviapablo.s5t1n1;

import com.martinfluviapablo.s5t1n1.model.domain.Sucursal;
import com.martinfluviapablo.s5t1n1.model.dto.SucursalDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Arrays;

@SpringBootApplication
public class S5T1N1Application {

	private Logger log = LoggerFactory.getLogger(S5T1N1Application.class);

	public static void main(String[] args) {
		SpringApplication.run(S5T1N1Application.class, args);
	}

	@Bean
	public CommandLineRunner run(ApplicationContext context){
		return args -> {
			//Arrays.stream(context.getBeanDefinitionNames())
			//		.sorted().forEach(s -> log.info(s));

			//Sucursal sucursal = (Sucursal) context.getBean("sucursal");
			//SucursalDto dto = (SucursalDto) context.getBean("dto");
		};
	}

	@Bean("sucursal")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Sucursal sucursalBean(){
		return new Sucursal();
	}

	@Bean("dto")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public SucursalDto sucursalDtoBean(){
		return new SucursalDto();
	}
}
