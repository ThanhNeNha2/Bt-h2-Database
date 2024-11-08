package dev.danvega.h2_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import dev.danvega.h2_demo.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

        private final CustomUserDetailsService userDetailsService;

        public WebSecurityConfig(CustomUserDetailsService userDetailsService) {
                this.userDetailsService = userDetailsService;
        }

        @Bean
        public AuthenticationManager authManager(HttpSecurity http) throws Exception {
                AuthenticationManagerBuilder authenticationManagerBuilder = http
                                .getSharedObject(AuthenticationManagerBuilder.class);
                authenticationManagerBuilder.userDetailsService(userDetailsService);
                return authenticationManagerBuilder.build();
        }

        @Bean
        protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                return http
                                .csrf().disable() // Tắt bảo vệ CSRF, phù hợp cho các API
                                .authorizeHttpRequests(request -> request
                                                .requestMatchers("/login", "/register").permitAll() // Cho phép truy cập
                                                                                                    // không cần đăng
                                                                                                    // nhập vào /login
                                                                                                    // và /register
                                                .anyRequest().authenticated()) // Các yêu cầu khác yêu cầu đăng nhập
                                .formLogin(login -> login
                                                .loginPage("/login") // Chỉ định trang đăng nhập tùy chỉnh
                                                .defaultSuccessUrl("/") // Trang chuyển tiếp sau khi đăng nhập thành
                                                                        // công
                                                .permitAll())
                                .build();
        }
}
