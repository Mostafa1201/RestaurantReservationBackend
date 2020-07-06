package io.taher.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.Arrays;
import java.util.Collections;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtMiddleware jwtMiddleware;
	@Autowired
	private UserDetailsService jwtUserDetailsService;
	@Autowired
	private IsAuthorized isAuthorized;
	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
		logger.info("testt: ",auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder()));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
				httpSecurity.csrf().disable()
						// dont authenticate this particular request
						.authorizeRequests().antMatchers("/","/auth/login", "/auth/register").permitAll().
						antMatchers(HttpMethod.OPTIONS, "/**").permitAll().
						// all other requests need to be authenticated
						anyRequest().authenticated().and().
						// make sure we use stateless session; session won't be used to
						// store user's state.
						exceptionHandling().authenticationEntryPoint(jwtMiddleware).and().sessionManagement()
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

				// Add a filter to validate the tokens with every request
				httpSecurity.addFilterBefore(isAuthorized, UsernamePasswordAuthenticationFilter.class);
	}
	
}
