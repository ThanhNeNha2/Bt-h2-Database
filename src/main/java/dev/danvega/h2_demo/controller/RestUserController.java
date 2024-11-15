package dev.danvega.h2_demo.controller;

import dev.danvega.h2_demo.domain.UserDemo;
import dev.danvega.h2_demo.service.UserDemoService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestUserController {

    private final UserDemoService userService;

    public RestUserController(UserDemoService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getHomePage(Model model) {
        List<UserDemo> users = this.userService.getAllUser();

        return users.toString();
    }

    @PostMapping(value = "/addUser")
    public UserDemo addUser(@RequestBody UserDemo user) {
        userService.handleSaveUser(user);
        return user;
    }

    @PutMapping("/update/{id}") // Sử dụng PUT để cập nhật người dùng
    public UserDemo updateUser(@PathVariable long id, @RequestBody UserDemo user) {
        UserDemo currentUser = userService.getUserDetailById(id);
        if (currentUser != null) {
            // Cập nhật các trường cần thiết
            currentUser.setFirstName(user.getFirstName());
            currentUser.setLastName(user.getLastName());
            // Cập nhật thông tin người dùng
            userService.handleSaveUser(currentUser);
            return currentUser; // Trả về người dùng sau khi cập nhật
        }
        // Nếu không tìm thấy người dùng, có thể trả về null hoặc ném ra Exception
        return null; // hoặc throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // Xóa người dùng theo ID
    @DeleteMapping("/delete/{id}") // Sử dụng DELETE cho việc xóa
    public void deleteUser(@PathVariable long id) {
        userService.handleDeleteUser(id); // Gọi phương thức xóa người dùng
    }
}
