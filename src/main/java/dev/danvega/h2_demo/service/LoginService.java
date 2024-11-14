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
        if (loginRepository.findByUsername(user.getUsername()) != null) {
            return false; // Username đã tồn tại
        }
        // Thêm {noop} vào trước mật khẩu để Spring Security nhận biết mật khẩu không mã
        // hóa
        user.setPassword("{noop}" + user.getPassword());
        loginRepository.save(user);
        return true; // Đăng ký thành công
    }

    public boolean login(String username, String password) {
        User existingUser = loginRepository.findByUsername(username);
        System.out.println("pass data " + existingUser.getPassword());
        System.out.println("pass truyen vao " + password);
        // Kiểm tra xem người dùng có tồn tại và mật khẩu có khớp không
        if (existingUser != null && existingUser.getPassword().equals(password)) {
            return true; // Đăng nhập thành công
        }
        return false; // Đăng nhập thất bại
    }

    public User findByUsername(String username) {
        return loginRepository.findByUsername(username);
    }

    public boolean loginPostman(String username, String password) {
        User existingUser = loginRepository.findByUsername(username);

        if (existingUser != null) {
            String storedPassword = existingUser.getPassword();

            // Loại bỏ {noop} nếu tồn tại trong storedPassword
            if (storedPassword.startsWith("{noop}")) {
                storedPassword = storedPassword.substring("{noop}".length());
            }

            System.out.println("pass data " + storedPassword);
            System.out.println("pass truyen vao " + password);

            // So sánh mật khẩu đã bỏ {noop}
            if (storedPassword.equals(password)) {
                return true; // Đăng nhập thành công
            }
        }
        return false; // Đăng nhập thất bại
    }
}
