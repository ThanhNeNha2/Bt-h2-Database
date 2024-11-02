package dev.danvega.h2_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

        @Bean
        protected UserDetailsService userDetailsService() {
                UserDetails user = User.builder()
                                .username("user")
                                .password(passwordEncoder().encode("password"))
                                .roles("USER")
                                .build();
                return new InMemoryUserDetailsManager(user);
        }

        @Bean
        protected PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                return http
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(request -> request
                                                .anyRequest().permitAll() // Cho phép tất cả các trang đều truy cập mà
                                                                          // không cần đăng nhập
                                )
                                .build();
        }
}