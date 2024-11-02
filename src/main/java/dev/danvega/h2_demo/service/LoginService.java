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
}