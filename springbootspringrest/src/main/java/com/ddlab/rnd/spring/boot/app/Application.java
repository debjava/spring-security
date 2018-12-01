package com.ddlab.rnd.spring.boot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

//@Configuration
@ComponentScan(basePackages = { "com.ddlab.rnd.spring.*"})
@SpringBootApplication
//@EnableAutoConfiguration
public class Application {
	
//	http://www.programcreek.com/java-api-examples/index.php?source_dir=spring-data-rest-knockout-bookmarks-master/src/main/java/bookmarks/Application.java
	
	
//	@Bean
//    public CommonsRequestLoggingFilter logFilter() {
//        CommonsRequestLoggingFilter filter
//          = new CommonsRequestLoggingFilter();
//        filter.setIncludeQueryString(true);
//        filter.setIncludePayload(true);
//        filter.setMaxPayloadLength(10000);
//        filter.setIncludeHeaders(false);
//        filter.setAfterMessagePrefix("REQUEST DATA : ");
//        return filter;
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}