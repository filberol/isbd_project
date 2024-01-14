package com.isbd.coursework.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static jakarta.servlet.DispatcherType.ERROR;
import static jakarta.servlet.DispatcherType.FORWARD;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

    @Bean
    @SuppressWarnings("deprecation")
    public UserDetailsService userDetailsService() {
        UserDetails adminUser = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN", "BRIGADE", "USER")
                .build();
        UserDetails brigadeUser = User.withDefaultPasswordEncoder()
                .username("brigade")
                .password("brigade")
                .roles("BRIGADE", "USER")
                .build();
        userDetailsManager.createUser(adminUser);
        userDetailsManager.createUser(brigadeUser);
        return userDetailsManager;
    }

    @Bean
    @SuppressWarnings("removal")
    public SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests(authorize -> authorize
                        .dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
                        .requestMatchers("/register", "/login", "/logout").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/brigade/**").hasRole("BRIGADE")
                        .requestMatchers("/api/**", "/home/**").authenticated()
                        .anyRequest().authenticated()
                ).formLogin(withDefaults());
        return http.build();
    }

}
