package com.kamoun.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamoun.productservice.dto.ProductRequest;
import com.kamoun.productservice.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest
class ProductServiceApplicationTests {
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	ProductRepository productRepository;
	@DynamicPropertySource
 static void setProperties(DynamicPropertyRegistry registry){
	 registry.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);
 }

	@Test
	void should_create_product() throws Exception {
    var product=getProductRequest();
	String productRequest=objectMapper.writeValueAsString(product);
	mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
			.contentType(MediaType.APPLICATION_JSON)
			.content(productRequest))
			.andExpect(status().isCreated());
		Assert.assertEquals(1,productRepository.findAll().size());

	}
private ProductRequest getProductRequest(){
		return ProductRequest.builder().
				name("iphone 16")
						.description("iphone 16")
								.price(BigDecimal.valueOf(1300)).
				build();
}



}
