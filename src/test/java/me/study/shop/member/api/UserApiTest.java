package me.study.shop.member.api;

import me.study.shop.member.domain.Address;
import me.study.shop.member.domain.Email;
import me.study.shop.member.domain.User;
import me.study.shop.member.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserApiTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Test
	@DisplayName("유저 등록")
	public void save() throws Exception {
		final User user = User.createUser(
			"member1", "test", new Email("test@test.com"),
			new Address("Seoul", "road", "12345"));


		given(userService.register(anyString(), anyString(), any(), any())).willReturn(user);

		mockMvc.perform(post("/api/v1/users")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"username\":\"member1\", \"password\":\"1234\",\"email\": {\"emailAddress\":\"test@test.com\"},"
				+ "\"address\" : {\"city\":\"Seoul\", \"street\":\"road\", \"zipcode\":\"12345\"}}"))
			.andDo(print())
			.andExpect(status().isCreated());

		verify(userService).register(anyString(), anyString(), any(), any());
	}

	@Test
	@DisplayName("유저 등록 - 파라미터 검증")
	public void save_invalid_input() throws Exception {
		mockMvc.perform(post("/api/v1/users")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"address\" : {\"city\":\"Seoul\", \"street\":\"road\", \"zipcode\":\"12345\"}}"))
			.andDo(print())
			.andExpect(status().is4xxClientError());
	}
}