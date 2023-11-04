package com.czx.bookproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "com/czx/bookproject/servletComponent")
public class BookProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookProjectApplication.class, args);
    }

}
