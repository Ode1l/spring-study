package com.iris.config;

import com.iris.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.iris")
public class SpringConfig {
    // @Bean
    // public User getUser(){
    //     return new User();
    // }
}
