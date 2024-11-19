package dev.danvega.h2_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import dev.danvega.h2_demo.domain.Role;
import dev.danvega.h2_demo.domain.User;
import dev.danvega.h2_demo.domain.UserDTO;
import dev.danvega.h2_demo.repository.RoleRepository;
import dev.danvega.h2_demo.repository.UserRepository;
import dev.danvega.h2_demo.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RestUserRole {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserService userService;

    @Autowired
    public RestUserRole(UserRepository userRepository, RoleRepository roleRepository, UserService userService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    // Sửa đổi ở đây để dùng UserDto
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        // Mã hóa mật khẩu
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Kiểm tra và gán role từ JSON
        Set<Role> roles = new HashSet<>();
        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            for (Role role : user.getRoles()) {
                Role existingRole = roleRepository.findByName(role.getName());
                if (existingRole == null) {
                    existingRole = new Role(role.getName());
                    roleRepository.save(existingRole);
                }
                roles.add(existingRole);
            }
        } else {
            // Mặc định gán ROLE_USER nếu không có role trong payload
            Role defaultRole = roleRepository.findByName("ROLE_USER");
            if (defaultRole == null) {
                defaultRole = new Role("ROLE_USER");
                roleRepository.save(defaultRole);
            }
            roles.add(defaultRole);
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

}
