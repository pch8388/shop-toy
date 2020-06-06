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
		final Member mockMember = Member.builder()
			.username("member1")
			.address(new Address("Seoul", "road", "12345"))
			.build();

		final Product mockProduct = Product.builder()
			.title("test")
			.price(10000)
			.build();

		final Cart mockCart = Cart.builder()
			.id(1L)
			.product(mockProduct)
			.member(mockMember)
			.build();

		given(cartService.saveCart(anyLong(), anyLong())).willReturn(mockCart);

		mockMvc.perform(post("/api/v1/cart")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"memberId\":1,\"productId\":1}"))
			.andDo(print())
			.andExpect(status().isCreated());

		verify(cartService).saveCart(anyLong(), anyLong());
	}
}