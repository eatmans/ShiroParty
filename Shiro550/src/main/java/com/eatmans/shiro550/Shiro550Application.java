package com.eatmans.shiro550;


import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Shiro550Application {
    // Shiro1.2.4 (CVE-2016-4437) shiro550
    public static void main(String[] args) {
        new SpringApplicationBuilder(Shiro550Application.class)
                .web(WebApplicationType.SERVLET)
                .run(args);

    }
}
