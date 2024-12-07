package com.dc.pharmacy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dc.pharmacy.security.CustomAuthenticationEntryPoint;
import com.dc.pharmacy.security.CustomAuthenticationProvider;
import com.dc.pharmacy.security.CustomUserDetailsService;
import com.dc.pharmacy.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, CustomAuthenticationEntryPoint authenticationEntryPoint,
    JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
        .requestMatchers("/api/public/**", "/actuator/**").permitAll() 
        .requestMatchers("/api/user/**").hasRole("USER")
        .requestMatchers("/api/admin/**").hasAnyRole("ADMIN", "MANAGER")
        .anyRequest().authenticated())
        .httpBasic()
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(authenticationEntryPoint)
        .and()
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .logout(logout -> logout.logoutUrl("/logout").permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // InMemory Authentication
    // @Bean
    // public UserDetailsService userDetailsService() {
    //     InMemoryUserDetailsManager user = new InMemoryUserDetailsManager();
    //     user.createUser(User.withUsername("user")
    //                            .password(passwordEncoder().encode("User@1234"))
    //                            .roles("USER")
    //                            .build());
    //     user.createUser(User.withUsername("admin")
    //                            .password(passwordEncoder().encode("Admin@1234"))
    //                            .roles("ADMIN","MANAGER")
    //                            .build());
    //     user.createUser(User.withUsername("manager")
    //                            .password(passwordEncoder().encode("Manager@1234"))
    //                            .roles("MANAGER")
    //                            .build());
    //     return user;
    // }

    // Custom User Detail service
    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;  
    }

    @SuppressWarnings("removal")
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
               .userDetailsService(userDetailsService()) 
               .passwordEncoder(passwordEncoder())
               .and()
               .build();
    }
}

