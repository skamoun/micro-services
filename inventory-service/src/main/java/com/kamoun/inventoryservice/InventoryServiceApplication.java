package com.kamoun.inventoryservice;

import com.kamoun.inventoryservice.model.Inventory;
import com.kamoun.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
@Bean
	public CommandLineRunner loadData(InventoryRepository repository){
		return args -> {
			Inventory inventory =Inventory.builder()
					.skuCode("iphone_14")
					.quantity(100)
					.build();
			Inventory inventory1 =Inventory.builder()
					.skuCode("iphone_14_red")
					.quantity(0)
					.build();
			repository.save(inventory);
			repository.save(inventory1);
		};
	}

}
