package com.crio.xharktank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
@CrossOrigin(origins="*")
public class XharktankApplication {

	public static void main(String[] args) {
		SpringApplication.run(XharktankApplication.class, args);
	}

//	@Bean
//	public WebMvcConfigurer configure() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/*").allowedOrigins("https://xharktank.crio.do/");
//			}
//
//		};
//	}

}
