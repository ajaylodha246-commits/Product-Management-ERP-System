package com.codeonet.product_managenent.security;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter filter;

    @Bean
    public SecurityFilterChain security(HttpSecurity http) throws Exception {

        http
                .cors(cors -> {})
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/me").authenticated()
                        //.requestMatchers(HttpMethod.POST, "/auth/register").hasRole("ADMIN")

//                        .requestMatchers(HttpMethod.GET,
//                                "/category/**",
//                                "/subCategory/**",
//                                "/vendor/**",
//                                "/platform/**",
//                                "/inventory/**",
//                                "/products/**",
//                                "/sales/**",
//                                "/purchase/**"
//                        ).permitAll()
//
//                        .requestMatchers(HttpMethod.POST, "/products/add").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/products/**").hasRole("EMPLOYEE")
//                        .requestMatchers(HttpMethod.POST, "/category/add").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST, "/subCategory/add").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST, "/vendor/add").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST, "/platform/add").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST, "/inventory/add").hasRole("ADMIN")
//
//                        .requestMatchers(HttpMethod.POST, "/purchase/add").hasAnyRole("ADMIN", "MANAGER")
//                        .requestMatchers(HttpMethod.PUT, "/purchase/status/**").hasAnyRole("ADMIN", "EMPLOYEE")
//
//                        .requestMatchers(HttpMethod.POST, "/sales/add").hasAnyRole("ADMIN", "EMPLOYEE")
//                        .requestMatchers(HttpMethod.DELETE, "/sales/delete/**").hasAnyRole("ADMIN", "EMPLOYEE")
//                        .requestMatchers(HttpMethod.PUT, "/sales/soldStock").hasAnyRole("ADMIN", "EMPLOYEE")
//                        .requestMatchers(HttpMethod.PUT, "/sales/item/**").hasAnyRole("ADMIN", "EMPLOYEE")

                        .anyRequest()
                        .permitAll()
                )
                .addFilterBefore(
                        filter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:5173");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
