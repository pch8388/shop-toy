package me.study.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import me.study.shop.security.Jwt;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Jwt jwt(JwtTokenConfig jwtTokenConfigure) {
        return new Jwt(jwtTokenConfigure.getIssuer(),
            jwtTokenConfigure.getClientSecret(),
            jwtTokenConfigure.getExpirySeconds());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
            "/swagger-resources", "/webjars/**",
            "/static/**", "/templates/**", "/h2/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .headers().disable()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .authorizeRequests()
                .antMatchers("/api/**/users").permitAll()
                .antMatchers("/api/**/categories/").hasRole("ADMIN")
                .antMatchers("/api/**").hasRole("USER")
                .anyRequest().permitAll()
                .and()
            .formLogin().disable();
    }
}
