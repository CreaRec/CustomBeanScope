package com.crearec.beanperthread.configuration;

import com.crearec.beanperthread.scope.CustomThreadScope;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.SimpleThreadScope;

@Configuration
public class AppConfiguration {

    @Bean
    public static CustomScopeConfigurer webSocketScopeConfigurer() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        configurer.addScope("thread", new CustomThreadScope());
        return configurer;
    }
}
