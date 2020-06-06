package me.study.shop.web;

import me.study.shop.domain.Product;
import me.study.shop.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
	@DisplayName("상품 등록")
	public void save() throws Exception {
		Product mockProduct = Product.builder()
			.title("test")
			.price(10000)
			.stockQuantity(100)
			.build();

		given(productService.save(any(Product.class))).willReturn(mockProduct);

		mockMvc.perform(post("/api/v1/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"title\":\"test\",\"price\":10000, \"stockQuantity\":100}"))
			.andDo(print())
			.andExpect(status().isCreated());

		verify(productService).save(any(Product.class));
	}

	@Test
	@DisplayName("상품 등록 - 파라미터 검증")
	public void save_invalid_input() throws Exception {
		mockMvc.perform(post("/api/v1/product")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"title\":\"test\",\"price\":10000, \"stockQuantity\":0}"))
			.andDo(print())
			.andExpect(status().is4xxClientError());

		mockMvc.perform(post("/api/v1/product")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"title\":\"\",\"price\":10000, \"stockQuantity\":100}"))
			.andDo(print())
			.andExpect(status().is4xxClientError());
	}

	@Test
	@DisplayName("상품 목록")
	public void list() throws Exception {
		final String title = "test";
		List<Product> mockProducts = Collections.singletonList(Product.builder()
			.title(title)
			.price(10000)
			.stockQuantity(100)
			.build());

		Page<Product> mockPage = new PageImpl<>(mockProducts);
		PageRequest pageRequest = PageRequest.of(0, 3);
		given(productService.findProducts(pageRequest)).willReturn(mockPage);

		mockMvc.perform(get("/api/v1/product")
				.param("page", "0")
				.param("size", "3"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().string(containsString(title)));
	}

	@Test
	@DisplayName("상품 상세정보")
	public void detail() throws Exception {
		final Long id = 1L;
		final String title = "test";
		Product product = Product.builder()
			.id(id)
			.title(title)
			.price(100)
			.stockQuantity(100)
			.build();

		given(productService.findProduct(id)).willReturn(product);

		mockMvc.perform(get("/api/v1/product/1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().string(containsString(title)));
	}
}
