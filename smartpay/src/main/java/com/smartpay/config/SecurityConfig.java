package com.smartpay.smartpay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http
                // ✅ Modern way to disable CSRF
                .csrf(AbstractHttpConfigurer::disable)

                // ✅ Allow all requests (temporarily, until JWT is added)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )

                // ✅ Disable default form login & HTTP Basic auth
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();

    }
}