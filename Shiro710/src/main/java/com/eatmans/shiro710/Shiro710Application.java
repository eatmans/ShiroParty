package com.eatmans.shiro710;


import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Shiro710Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Shiro710Application.class)
                .web(WebApplicationType.SERVLET)
                .run(args);

    }
}
