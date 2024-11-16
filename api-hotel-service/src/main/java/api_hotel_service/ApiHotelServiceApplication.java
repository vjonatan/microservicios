package api_hotel_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class ApiHotelServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiHotelServiceApplication.class, args);
	}

}
