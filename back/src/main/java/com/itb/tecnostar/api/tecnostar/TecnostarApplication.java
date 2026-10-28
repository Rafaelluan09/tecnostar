package com.itb.tecnostar.api.tecnostar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TecnostarApplication {

	public static void main(String[] args) {
		SpringApplication.run(TecnostarApplication.class, args);
		System.out.println("TecnoStar API no ar!");
	}

}
