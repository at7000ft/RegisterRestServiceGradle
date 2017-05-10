package com.webpilot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description: Start RegisterRestService 
 * Date: 4/11/17
 *
 * @author RGH
 *
 * The @SpringBootApplication annotation is equivalent to using @Configuration,
 * @EnableAutoConfiguration and @ComponentScan with their default attributes.
 */
@SpringBootApplication
public class SpringBootWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }
}
