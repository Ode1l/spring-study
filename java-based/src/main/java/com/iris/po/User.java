package com.iris.po;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class User {
    @Value("Ode1l")
    public String Name;
}
