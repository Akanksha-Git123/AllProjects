package com.payingguest.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class PayingGuestBookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayingGuestBookingSystemApplication.class, args);
	}

}
