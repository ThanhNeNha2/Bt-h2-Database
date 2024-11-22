package dev.danvega.h2_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import dev.danvega.h2_demo.service.CustomUserDetailsService;
import dev.danvega.h2_demo.service.LoginService;
import dev.danvega.h2_demo.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    // private final CustomUserDetailsService userDetailsService;

    // public WebSecurityConfig(CustomUserDetailsService userDetailsService) {
    // this.userDetailsService = userDetailsService;
    // }

    // @Bean
    // public AuthenticationManager authManager(HttpSecurity http) throws Exception
    // {
    // AuthenticationManagerBuilder authenticationManagerBuilder = http
    // .getSharedObject(AuthenticationManagerBuilder.class);
    // authenticationManagerBuilder.userDetailsService(userDetailsService);
    // return authenticationManagerBuilder.build();
    // }

    @Bean
    public UserDetailsService userDetailsService(LoginService loginService) {
        return new CustomUserDetailsService(loginService);
    }

    @Bean
    public DaoAuthenticationProvider authProvider(
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        ;
        // authProvider.setHideUserNotFoundExceptions(false);
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests

                        .requestMatchers("/api/register", "/api/userAuth", "/api/generateToken", "/h2-console/*")
                        .permitAll()

                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/companies").hasRole("ADMIN")
                        .requestMatchers("/register").permitAll()
                        .anyRequest().authenticated())
                .httpBasic() // Kích hoạt HTTP Basic Authentication cho API
                .and()
                .formLogin(form -> form

                        .loginPage("/login") // Đường dẫn trang đăng nhập của bạn
                        .defaultSuccessUrl("/userAuthTable", true) // Trang chuyển hướng sau khi đăng nhập
                        // thành công
                        .permitAll() // Cho phép tất cả truy cập vào trang đăng nhập và đăng ký
                );

        return http.build(); // Dùng build() để tạo SecurityFilterChain
    }
}