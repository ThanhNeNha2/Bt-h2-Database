package dev.danvega.h2_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

        @Bean
        protected UserDetailsService userDetailsService() {
                // Không cần cấu hình UserDetails tại đây nếu bạn muốn sử dụng user từ database
                return new InMemoryUserDetailsManager();
        }

        @Bean
        protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                return http
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(request -> request
                                                .requestMatchers("/login", "/register").permitAll() // Cho phép truy cập
                                                                                                    // vào /login và
                                                                                                    // /register
                                                .anyRequest().authenticated() // Các trang khác yêu cầu đăng nhập
                                )
                                .formLogin(login -> login
                                                .loginPage("/login") // Chỉ định trang đăng nhập tùy chỉnh
                                                .defaultSuccessUrl("/")
                                                .permitAll())
                                .build();
        }
}
