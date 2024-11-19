package dev.danvega.h2_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.danvega.h2_demo.domain.User;
import dev.danvega.h2_demo.service.UserService;
import dev.danvega.h2_demo.service.UserDemoService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("arrUsers", userService.getAllUsers());
        return "UserAuthTable";
    }

    // // UPDATE USER

    @RequestMapping("/admin/userAuth/update/{id}")
    public String getUpdateUserPage(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("updateUser", user);
        return "UserAuthUpdate";
    }

    @PostMapping("/admin/userAuth/update")
    public String updateUser(@ModelAttribute("updateUser") User user) {
        userService.saveOrUpdateUser(user);
        return "redirect:/";
    }

    // // DELETE USER

    @RequestMapping("/admin/userAuth/delete/{id}")
    public String getDeleteUserPage(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("deleteUser", user);
        return "UserAuthDelete";
    }

    @PostMapping("/admin/userAuth/delete")
    public String PostDeleteUser(Model model, @ModelAttribute("deleteUser") User user) {
        System.out.println("check ne nha " + user.getId());
        this.userService.deleteUser(user.getId());
        return "redirect:/";
    }
}
