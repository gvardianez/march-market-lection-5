package ru.geekbrains.march.market.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MarchMarketCoreApplication {
	// Домашнее задание:
	// 1. Разобраться с кодом
	// 2. Реализовать создание заказов в кор-сервисе

	public static void main(String[] args) {
		SpringApplication.run(MarchMarketCoreApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
