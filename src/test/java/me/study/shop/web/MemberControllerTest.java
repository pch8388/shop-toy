package me.study.shop.web;

import me.study.shop.domain.Address;
import me.study.shop.domain.Member;
import me.study.shop.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MemberService memberService;

	@Test
	@DisplayName("유저 등록")
	public void save() throws Exception {
		final Member member = Member.builder()
			.username("member1")
			.address(new Address("Seoul", "road", "12345"))
			.build();

		given(memberService.register(any(Member.class))).willReturn(member);

		mockMvc.perform(post("/api/v1/member")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"username\":\"member1\", \"address\" : {\"city\":\"Seoul\", \"street\":\"road\", \"zipcode\":\"12345\"}}"))
			.andDo(print())
			.andExpect(status().isCreated());

		verify(memberService).register(any(Member.class));
	}
}