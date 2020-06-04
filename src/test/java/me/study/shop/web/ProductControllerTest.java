package me.study.shop.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.study.shop.dto.ProductRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void save() throws Exception {
		ProductRequestDto dto = ProductRequestDto.builder()
			.name("test")
			.price(10000)
			.build();

		mockMvc.perform(post("/api/v1/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsBytes(dto)))
			.andDo(print())
			.andExpect(status().isCreated());
	}
}
