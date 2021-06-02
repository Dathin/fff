package me.pedrocaires.fff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FffApplication {

	public static void main(String[] args) {
		SpringApplication.run(FffApplication.class, args);
	}

}
