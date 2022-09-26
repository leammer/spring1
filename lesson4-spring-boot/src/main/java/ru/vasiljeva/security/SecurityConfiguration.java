package ru.vasiljeva.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {
	@Autowired
	public void authConfig(AuthenticationManagerBuilder authBuilder, UserDetailsServiceImpl userDetailsService,
			PasswordEncoder encoder) throws Exception {

		authBuilder.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}
}
