package me.study.shop.security;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;

import me.study.shop.security.Jwt;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JwtTest {

    private Jwt jwt;

    @BeforeAll
    void setUp() {
        final String issuer = "test-issuer";
        final String clientSecret = "secret1234567890";
        final int expirySeconds = 10;

        jwt = new Jwt(issuer, clientSecret, expirySeconds);
    }

    @Test
    @DisplayName("토큰 생성")
    void newToken() {
        final Jwt.Claims encodeToken =
            Jwt.Claims.of(1L, "tester", new String[]{"ROLE_USER"});
        final String newToken = jwt.newToken(encodeToken);

        final Jwt.Claims decodeToken = jwt.verify(newToken);

        assertThat(encodeToken.userKey).isEqualTo(decodeToken.userKey);
        assertThat(encodeToken.name).isEqualTo(decodeToken.name);
        assertThat(encodeToken.roles).isEqualTo(decodeToken.roles);
    }

    @Test
    @DisplayName("토큰 리프레시")
    void refreshToken() throws InterruptedException {
        final Jwt.Claims claims =
            Jwt.Claims.of(1L, "tester", new String[]{"ROLE_USER"});
        final String newToken = jwt.newToken(claims);

        Thread.sleep(1000);

        final String refreshToken = jwt.refreshToken(newToken);

        assertThat(newToken).isNotEqualTo(refreshToken);

        final Jwt.Claims oldJwt = jwt.verify(newToken);
        final Jwt.Claims newJwt = jwt.verify(refreshToken);

        long oldExp = oldJwt.exp();
        long newExp = newJwt.exp();

        // 1초 후에 토큰을 갱신했으므로, 새로운 토큰의 만료시각이 1초 이후임
        assertThat(newExp >= oldExp + 1_000L).isTrue();
    }
}