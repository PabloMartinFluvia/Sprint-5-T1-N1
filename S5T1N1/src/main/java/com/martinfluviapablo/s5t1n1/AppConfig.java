package com.martinfluviapablo.s5t1n1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.modelmapper.ModelMapper;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("home");

        /*
        FET EXPRESSAMENT:
        La pàgina de buscar vull que funcioni SENSE passar-li un Model.
        Ja que, proporcionar un model:
              a) No soluciona el problema del path variable segons el valor de l'input (detallat en buscar.html)
              b) La validació de la data proporcionada de la manera típica
                    ex: @ModelAttribute + @Valid , BindingResult + if(has errors) etc..
                 només fa que augmentar el codi innecessàriament, tenint en compte que la restricció
                 es pot posar directament en el html -> <input .... min=1 ... />
         */
        registry.addViewController("/buscar").setViewName("buscar");
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
