package com.iquantex.common.apimonitorspringbootstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class ApiMonitorSpringBootStarterApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ApiMonitorSpringBootStarterApplication.class, args);
        Arrays.stream(run.getBeanDefinitionNames()).forEach(System.out::println);
    }
}
