package ru.vasiljeva.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {
	@Autowired
	public void authConfig(AuthenticationManagerBuilder authBuilder, UserDetailsServiceImpl userDetailsService,
			PasswordEncoder encoder) throws Exception {

		authBuilder.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//@formatter:off
		http
			.authorizeRequests()
			.antMatchers("/**/*.css", "/**/*.js").permitAll()
			.antMatchers("/").permitAll();
		return http.build();
		//@formatter:on
	}
}
