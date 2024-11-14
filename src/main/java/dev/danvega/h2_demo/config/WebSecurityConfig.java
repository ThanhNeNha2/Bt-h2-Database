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
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf().disable() // Tắt CSRF cho API
                                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                                                .requestMatchers("/api/login", "/api/register", "/api/user",
                                                                "/api/addUser", "/api/delete/{id}", "/api/update/{id}",
                                                                "/register", "/api/userAuth")
                                                .permitAll() // Cho phép tất cả truy cập vào API login và register
                                                .requestMatchers("/companies", "/nV_CP").hasRole("USER") // Cấu hình cho
                                                                                                         // người dùng
                                                                                                         // có vai trò
                                                                                                         // "USER"
                                                                                                         // Admin có
                                                                                                         // quyền truy
                                                                                                         // cập tất cả

                                                .requestMatchers("/userAuthTable").hasRole("ADMIN") // Chỉ cho phép
                                                                                                    // ADMIN truy cập
                                                                                                    // vào
                                                                                                    // /userAuthTable
                                                .anyRequest().authenticated() // Các yêu cầu khác yêu cầu đăng nhập
                                )
                                .httpBasic() // Kích hoạt HTTP Basic Authentication cho API
                                .and()
                                .formLogin(form -> form
                                                // .requestMatchers("/**").hasRole("ADMIN")
                                                .loginPage("/login") // Đường dẫn trang đăng nhập của bạn
                                                .defaultSuccessUrl("/", true) // Trang chuyển hướng sau khi đăng nhập
                                                                              // thành công
                                                .permitAll() // Cho phép tất cả truy cập vào trang đăng nhập và đăng ký
                                );

                return http.build(); // Dùng build() để tạo SecurityFilterChain
        }
}