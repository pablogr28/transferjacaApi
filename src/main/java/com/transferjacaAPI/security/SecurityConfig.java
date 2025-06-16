package com.transferjacaAPI.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final RequestFilter requestFilter;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    public SecurityConfig(UserDetailsService userDetailsService,
                          RequestFilter requestFilter,
                          CustomAuthenticationEntryPoint authenticationEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.requestFilter = requestFilter;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                // Swagger abierto
                .requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/webjars/**"
                ).permitAll()

                // Registro y Login públicos
                .requestMatchers("/users/registrar", "/users/login","/users/**","/players/**","/matches/**","/player-tournaments/**","/teams/**","/tournaments/**","/transfers/**").permitAll()

                // Cualquier otra petición requiere autenticación
                .anyRequest().authenticated()
        );

        http.csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint));

        http.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
