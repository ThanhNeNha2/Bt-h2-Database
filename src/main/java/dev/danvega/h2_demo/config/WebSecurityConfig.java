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
                                                .requestMatchers("/login", "/register").permitAll() // Cho phép truy cập
                                                                                                    // vào /login và
                                                                                                    // /register mà
                                                                                                    // không cần đăng
                                                                                                    // nhập
                                                .anyRequest().authenticated() // Các trang khác yêu cầu đăng nhập
                                )
                                .formLogin(login -> login
                                                .loginPage("/login") // Chỉ định trang đăng nhập tùy chỉnh
                                                .defaultSuccessUrl("/") // Chuyển hướng sau khi đăng nhập thành công
                                                .permitAll())

                                .build();
        }
}
