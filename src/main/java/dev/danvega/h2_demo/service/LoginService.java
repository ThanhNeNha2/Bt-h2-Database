package dev.danvega.h2_demo.service;

import dev.danvega.h2_demo.domain.User;
import dev.danvega.h2_demo.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public boolean register(User user) {
        // Kiểm tra nếu username đã tồn tại
        if (loginRepository.findByUsername(user.getUsername()) != null) {
            return false; // Username đã tồn tại
        }
        // Lưu người dùng mới vào cơ sở dữ liệu
        loginRepository.save(user);
        return true; // Đăng ký thành công
    }

    public boolean login(String username, String password) {
        User existingUser = loginRepository.findByUsername(username);
        // Kiểm tra xem người dùng có tồn tại và mật khẩu có khớp không
        if (existingUser != null && existingUser.getPassword().equals(password)) {
            return true; // Đăng nhập thành công
        }
        return false; // Đăng nhập thất bại
    }

    public User findByUsername(String username) {
        return loginRepository.findByUsername(username);
    }
}
