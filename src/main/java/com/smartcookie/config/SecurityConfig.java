package com.smartcookie.config;

import com.smartcookie.domain.service.impl.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final String SECRET_KEY = "123456";

	private final CustomUserDetailsService customUserDetailsService;
	private final AccessDeniedHandler accessDeniedHandler;
	private final CustomAuthenticationProvider customAuthenticationProvider;

	public SecurityConfig(CustomUserDetailsService customUserDetailsService, AccessDeniedHandler accessDeniedHandler, CustomAuthenticationProvider customAuthenticationProvider) {
		this.customUserDetailsService = customUserDetailsService;
		this.accessDeniedHandler = accessDeniedHandler;
		this.customAuthenticationProvider = customAuthenticationProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.sessionManagement()
				.sessionFixation().migrateSession()
				.maximumSessions(2)
				.and()
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				.and()
				.authorizeRequests()
				.antMatchers("/registration").not().fullyAuthenticated()
				.antMatchers("/", "/home", "/pricing", "/about", "/contact", "/logo/**").permitAll()
				.antMatchers("/profile/*").fullyAuthenticated()
				.antMatchers("/users/*", "/roles/*", "/courses/*", "/categories/*", "/admin/*").hasRole("ADMIN")
//                .antMatchers("/teacher/*").hasAnyRole("ADMIN", "TEACHER")
//                .antMatchers("/student/*").hasRole("STUDENT")
				.anyRequest().authenticated()
//                .anyRequest().permitAll()
				.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/")
				.usernameParameter("username")
				.passwordParameter("password").permitAll()
				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/").permitAll()
				.and()
				.exceptionHandling().accessDeniedHandler(accessDeniedHandler);


	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService);
		auth.authenticationProvider(customAuthenticationProvider);
	}


	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
