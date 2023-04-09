package com.techway.configuration;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.techway.jwt.JwtTokenFilter;
import com.techway.repository.AccountRepository;

@Configuration
@EnableWebSecurity

///enable the attribute jsr250Enabled in order to use the @RolesAllowed annotation 
//in our API code for method-level authorization
@EnableGlobalMethodSecurity(
	    prePostEnabled = false, securedEnabled = false, jsr250Enabled = true
	)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private JwtTokenFilter jwtTokenFilter;
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        http.authorizeRequests()
        	.anyRequest().permitAll();        
        //the server will return HTTP status 401 (Unauthorized) 
        //if any error occurs during authentication process
        http.exceptionHandling()
	        .authenticationEntryPoint(
	            (request, response, ex) -> {
	                response.sendError(
	                    HttpServletResponse.SC_UNAUTHORIZED,
	                    ex.getMessage()
	                );
	            }
        );
 
        //adds our custom filter before the UsernameAndPasswordAuthenticationFilter 
        //in Spring Security filters chain
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(
	            username -> accountRepository.findByEmail(username)
	                .orElseThrow(
	                    () -> new UsernameNotFoundException("User " + username + " not found.")));
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } 
	
	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
