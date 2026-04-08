package com.example.citasmedicas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager users() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .roles("ADMIN")
                .build();

        UserDetails regular = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user123")
                .roles("REGULAR")
                .build();

        return new InMemoryUserDetailsManager(admin, regular);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/graphql", "/graphiql").hasAnyRole("ADMIN", "REGULAR")
                        .requestMatchers("/cambiar-estado/**", "/cancelar-cita/**").hasRole("ADMIN")
                        .requestMatchers("/citas", "/nueva-cita", "/guardar-cita", "/agenda", "/agenda-semanal").hasAnyRole("ADMIN", "REGULAR")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/citas", false)
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/citas")
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**", "/graphql"))
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}