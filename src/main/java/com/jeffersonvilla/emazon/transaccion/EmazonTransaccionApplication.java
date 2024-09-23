package com.jeffersonvilla.emazon.transaccion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EmazonTransaccionApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmazonTransaccionApplication.class, args);
	}

}
