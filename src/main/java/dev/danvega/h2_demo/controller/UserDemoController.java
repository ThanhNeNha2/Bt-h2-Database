package dev.danvega.h2_demo.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.danvega.h2_demo.domain.UserDemo;

import dev.danvega.h2_demo.service.UserDemoService;

@Controller
public class UserDemoController {

    private final UserDemoService userService;

    public UserDemoController(UserDemoService userService) {
        this.userService = userService;
    }

    @GetMapping("/userDemo")
    public String getHomePage(Model model) {
        List<UserDemo> users = this.userService.getAllUser();
        model.addAttribute("arrUsers", users);
        return "UserTable";
    }

    @RequestMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("newUser", new UserDemo());

        return "CreateUser";
    }

    @RequestMapping("/create-user")
    public String CreateUser(Model model, @ModelAttribute("newUser") UserDemo user) {
        System.out.println("haha vao day roi " + user);
        this.userService.handleSaveUser(user);
        return "redirect:/";
    }

    // UPDATE USER
    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        UserDemo user = this.userService.getUserDetailById(id);
        model.addAttribute("updateUser", user);
        return "UpdateUser";
    }

    @PostMapping("/admin/user/update")
    public String PostUpdateUser(Model model, @ModelAttribute("updateUser") UserDemo user) {
        UserDemo currentUser = this.userService.getUserDetailById(user.getId());
        if (currentUser != null) {
            currentUser.setFirstName(user.getFirstName());
            currentUser.setLastName(user.getLastName());

            this.userService.handleSaveUser(user);
        }
        return "redirect:/";
    }

    // DELETE user

    @RequestMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        UserDemo user = this.userService.getUserDetailById(id);
        model.addAttribute("deleteUser", user);
        return "DeleteUser";
    }

    @PostMapping("/admin/user/delete")
    public String PostDeleteUser(Model model, @ModelAttribute("deleteUser") UserDemo user) {
        System.out.println("check ne nha " + user.getId());
        this.userService.handleDeleteUser(user.getId());
        return "redirect:/";
    }

}