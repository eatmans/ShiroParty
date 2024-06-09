package com.eatmans.shiro721;


import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Shiro721Application {
    //    Shiro721（CVE-2019-12422）
    public static void main(String[] args) {
        new SpringApplicationBuilder(Shiro721Application.class)
                .web(WebApplicationType.SERVLET)
                .run(args);

    }
}
