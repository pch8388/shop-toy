package me.study.shop.web;

import me.study.shop.domain.Address;
import me.study.shop.domain.Cart;
import me.study.shop.domain.Member;
import me.study.shop.domain.Product;
import me.study.shop.service.CartService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CartControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CartService cartService;

	@Test
	@DisplayName("장바구니 추가")
	public void add_cart() throws Exception {
		final Member mockMember = Member.createMember(
			"member1", new Address("Seoul", "road", "12345"));

		final Product mockProduct = Product.createProduct(
			"test", 10000, 10);


		final Cart mockCart = Cart.addToCart(mockMember, mockProduct);

		given(cartService.saveCart(anyLong(), anyLong())).willReturn(mockCart);

		mockMvc.perform(post("/api/v1/cart")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"memberId\":1,\"productId\":1}"))
			.andDo(print())
			.andExpect(status().isCreated());

		verify(cartService).saveCart(anyLong(), anyLong());
	}

	@Test
	@DisplayName("장바구니 추가 - 파라미터 검증")
	public void add_cart_invalid_input() throws Exception {
		mockMvc.perform(post("/api/v1/cart")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"productId\":1}"))
			.andDo(print())
			.andExpect(status().is4xxClientError());

		mockMvc.perform(post("/api/v1/cart")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"memberId\":1}"))
			.andDo(print())
			.andExpect(status().is4xxClientError());
	}

	@Test
	@DisplayName("장바구니를 삭제한다")
	public void delete_cart() throws Exception {
		mockMvc.perform(delete("/api/v1/cart/1"))
			.andDo(print())
			.andExpect(status().isNoContent());
	}
}