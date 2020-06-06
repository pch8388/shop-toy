package me.study.shop.web;

import me.study.shop.domain.Product;
import me.study.shop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@Test
	public void save() throws Exception {
		mockMvc.perform(post("/api/v1/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"test\",\"price\":10000}"))
			.andDo(print())
			.andExpect(status().isCreated());
	}

	@Test
	public void list() throws Exception {
		mockMvc.perform(get("/api/v1/products"))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	public void detail() throws Exception {
		final Long id = 1L;
		final String name = "test";
		Product product = Product.builder()
			.id(id)
			.name(name)
			.price(100)
			.build();

		given(productService.findProduct(id)).willReturn(product);

		mockMvc.perform(get("/api/v1/product/1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().string(containsString(name)));
	}
}
