package com.techway.configuration;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.techway.security.UserDetailsSeviceImpl;
import com.techway.security.jwt.JwtTokenFilter;

@Configuration
@EnableWebSecurity

///enable the attribute jsr250Enabled in order to use the @RolesAllowed annotation 
//in our API code for method-level authorization
@EnableGlobalMethodSecurity(
	    prePostEnabled = true
	)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenFilter jwtTokenFilter;
	
	@Autowired
	UserDetailsSeviceImpl userDetailsSevice;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsSevice).passwordEncoder(passwordEncoder());
	}
	
	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } 
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.cors()
        	.and()
        	.csrf().disable()
        	.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
            .antMatchers(HttpMethod.PUT, "api/v1/**").hasAnyRole("ROLE_STAFF", "ROLE_DIRE")
            .antMatchers(HttpMethod.POST, "api/v1/**").hasAnyRole("ROLE_STAFF", "ROLE_DIRE")
            .antMatchers(HttpMethod.DELETE, "api/v1/**").hasAnyRole("ROLE_STAFF", "ROLE_DIRE")
            .antMatchers("/api/v1/auth/**").permitAll()
            .antMatchers(HttpMethod.PUT, "/api/v1/auth/**").hasAnyRole("ROLE_STAFF", "ROLE_DIRE", "ROLE_CUST")
            .antMatchers("/api/v1/cart/**").hasAnyRole("ROLE_STAFF", "ROLE_DIRE", "ROLE_CUST")
            .antMatchers("/api/v1/comments/**").hasAnyRole("ROLE_STAFF", "ROLE_DIRE", "ROLE_CUST")
            .antMatchers(HttpMethod.DELETE, "api/v1/comments/**").hasAnyRole("ROLE_CUST", "ROLE_DIRE")
            .antMatchers( "api/v1/orders/**").hasAnyRole("ROLE_CUST", "ROLE_STAFF", "ROLE_DIRE")
            .antMatchers(HttpMethod.DELETE, "/api/v1/replies/**").hasAnyRole("ROLE_CUST", "ROLE_DIRE")
            .antMatchers(HttpMethod.PUT, "/api/v1/users/**").hasAnyRole("ROLE_CUST", "ROLE_STAFF", "ROLE_DIRE")
            .anyRequest().authenticated()
            .and()
        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        	.and()        	
        	.httpBasic().disable()
        	.logout().disable();	       	
        
        http.authorizeRequests().anyRequest().permitAll();        
        
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
 
        //adds custom filter before the UsernameAndPasswordAuthenticationFilter 
        //in Spring Security filters chain
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }	
	
	@SuppressWarnings("deprecation")
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*").allowedHeaders("*").allowedOrigins("*");
            }
        };
    }

}
