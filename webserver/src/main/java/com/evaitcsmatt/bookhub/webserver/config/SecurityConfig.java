package com.evaitcsmatt.bookhub.webserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity
        		.cors(cor -> cor.disable())
        		.csrf(cs -> cs.disable())
                .authorizeHttpRequests(http ->http
//                        .requestMatchers(
//                        		"/",
//                        		"/books",
//                        		"/register",
//                        		"/books/",
//                        		"/books/add",
//                        		"/books/add/", 
//                        		"/css/**", 
//                        		"/js/**", 
//                        		"/images/**")
//                        .permitAll()
//                        .anyRequest()
//                        .authenticated()
                		.anyRequest().permitAll()
                        )
//                		.formLogin(form -> form
//                        .defaultSuccessUrl("/books", true)
//                        .permitAll())
                .authenticationProvider(authenticationProvider);
        return httpSecurity.build();
    }
}
