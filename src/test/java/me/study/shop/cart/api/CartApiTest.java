package me.study.shop.cart.api;

import me.study.shop.member.domain.Address;
import me.study.shop.cart.domain.Cart;
import me.study.shop.member.domain.Email;
import me.study.shop.member.domain.User;
import me.study.shop.product.domain.Product;
import me.study.shop.cart.service.CartService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CartApiTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CartService cartService;

	@Test
	@DisplayName("장바구니 추가")
	public void add_cart() throws Exception {
		final User mockUser = User.createUser(
			"member1", "test", new Email("test@test.com"),
			new Address("Seoul", "road", "12345"));

		final Product mockProduct = Product.createProduct(
			"test", 10000, 10);


		final Cart mockCart = Cart.addToCart(mockUser, mockProduct);

		given(cartService.saveCart(anyLong(), anyLong())).willReturn(mockCart);

		mockMvc.perform(post("/api/v1/carts")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"memberId\":1,\"productId\":1}"))
			.andDo(print())
			.andExpect(status().isCreated());

		verify(cartService).saveCart(anyLong(), anyLong());
	}

	@Test
	@DisplayName("장바구니 추가 - 파라미터 검증")
	public void add_cart_invalid_input() throws Exception {
		mockMvc.perform(post("/api/v1/carts")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"productId\":1}"))
			.andDo(print())
			.andExpect(status().isBadRequest());

		mockMvc.perform(post("/api/v1/carts")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"memberId\":1}"))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("장바구니를 삭제한다")
	public void delete_cart() throws Exception {
		mockMvc.perform(delete("/api/v1/carts/1"))
			.andDo(print())
			.andExpect(status().isNoContent());
	}

	@Test
	@DisplayName("장바구니 목록 조회")
	public void list_cart() throws Exception {
		final User mockUser = User.createUser(
			"member1", "test", new Email("test@test.com"),
			new Address("Seoul", "road", "12345"));

		final Product mockProduct = Product.createProduct(
			"test", 10000, 10);

		List<Cart> mockCart = Collections.singletonList(Cart.addToCart(mockUser, mockProduct));

		Page<Cart> mockPage = new PageImpl<>(mockCart);
		PageRequest pageRequest = PageRequest.of(0, 3);
		given(cartService.findAllByMemberId(1L, pageRequest)).willReturn(mockPage);

		mockMvc.perform(get("/api/v1/carts/1")
			.param("page", "0")
			.param("size", "3"))
			.andDo(print())
			.andExpect(status().isOk());
	}
}