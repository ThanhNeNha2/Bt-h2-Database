package dev.danvega.h2_demo.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import dev.danvega.h2_demo.domain.Role;
import dev.danvega.h2_demo.domain.User;
import dev.danvega.h2_demo.repository.RoleRepository;
import dev.danvega.h2_demo.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Controller
public class RegisterController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public RegisterController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "Register"; // the name of your HTML page
    }

    @PostMapping("/process-register")
    public String processRegister(@ModelAttribute User user) {
        // Check if username already exists
        if (userRepository.existsByUsername(user.getUsername())) {
            // Add a message to the model to inform the user
            return "redirect:/register?error=username_exists"; // or handle with a model attribute
        }

        // Encode the password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Retrieve or create the USER role
        Role role = roleRepository.findByName("ROLE_USER");
        if (role == null) {
            role = new Role();
            role.setName("ROLE_USER");
            roleRepository.save(role);
        }

        // Set the role for the user
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        // Save the user
        userRepository.save(user);

        return "redirect:/login"; // Redirect after successful registration
    }
}
