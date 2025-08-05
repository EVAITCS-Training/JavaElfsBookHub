package com.evaitcsmatt.bookhub.webserver.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private AuthenticationProvider authenticationProvider;
    
    @Autowired
    private JwtAuthFilter authFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity
        		.cors(cor -> cor.configurationSource(corConfig()))
        		.csrf(cs -> cs.disable())
                .authorizeHttpRequests(http ->http
                        .requestMatchers(
                        		"/api/v1/book",
                        		"/api/v1/auth/register",
                        		"/api/v1/book/",
                        		"/api/v1/book/add",
                        		"/api/v1/book/add/",
                        		"/api/v1/auth/login",
                                "/actuator/**"
                        		)
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                        )
                .sessionManagement(ses -> ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
    
    @Bean
    protected UrlBasedCorsConfigurationSource corConfig() {
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	CorsConfiguration configuration = new CorsConfiguration();
    	configuration.setAllowCredentials(false);
    	configuration.addAllowedOrigin("http://localhost:5173");
    	configuration.addAllowedHeader("*");
    	configuration.addAllowedMethod("*");
    	source.registerCorsConfiguration("/**", configuration);
    	return source;
    }
}
