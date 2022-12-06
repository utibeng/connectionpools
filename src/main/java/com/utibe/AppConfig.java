package com.utibe;

import com.utibe.aspects.AspectsUty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy //Enables @AspectJ
public class AppConfig {

    @Bean({"businessClassBean"})
    public BusinessClass generateBusinessClass(){
        return new BusinessClass("Effiong", 21);
    }

    @Bean
    public AspectsUty generateAspectBusinessClassNew(){
        return new AspectsUty();
    }

}