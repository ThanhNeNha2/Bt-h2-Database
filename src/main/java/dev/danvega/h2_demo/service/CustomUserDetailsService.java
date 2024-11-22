package dev.danvega.h2_demo.service;

import dev.danvega.h2_demo.domain.User;
import dev.danvega.h2_demo.repository.LoginRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final LoginService loginService;

    public CustomUserDetailsService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tìm người dùng trong cơ sở dữ liệu
        User user = this.loginService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Lấy vai trò từ đối tượng User và trả về UserDetails với các quyền tương ứng
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole()) // Sử dụng vai trò được lấy từ cơ sở dữ liệu
                .build();
    }
}