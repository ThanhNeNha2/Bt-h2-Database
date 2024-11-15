package dev.danvega.h2_demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.danvega.h2_demo.domain.User;
import dev.danvega.h2_demo.service.UserSevice;
import dev.danvega.h2_demo.service.UserDemoService;

@Controller
public class UserController {
    // private final UserSevice userAuthService;

    // public UserController(UserSevice userAuthService) {
    //     this.userAuthService = userAuthService;
    // }

    // @GetMapping("/userAuthTable")
    // public String getHomePage(Model model) {
    //     List<User> users = this.userAuthService.getAllUser();
    //     model.addAttribute("arrUsers", users);
    //     return "UserAuthTable";
    // }

 

    // // UPDATE USER

    // @RequestMapping("/admin/userAuth/update/{id}")
    // public String getUpdateUserPage(Model model, @PathVariable long id) {
    //     User user = this.userAuthService.getUserDetailById(id);
    //     model.addAttribute("updateUser", user);
    //     return "UserAuthUpdate";
    // }

    // @PostMapping("/admin/userAuth/update")
    // public String PostUpdateUser(Model model, @ModelAttribute("updateUser") User user) {
    //     User currentUser = this.userAuthService.getUserDetailById(user.getId());
    //     if (currentUser != null) {
    //         currentUser.setUsername(user.getUsername());
    //         currentUser.setPassword(user.getPassword());

    //         this.userAuthService.handleSaveUser(user);
    //     }
    //     return "redirect:/userAuthTable";
    // }

    // // DELETE USER

    // @RequestMapping("/admin/userAuth/delete/{id}")
    // public String getDeleteUserPage(Model model, @PathVariable long id) {
    //     User user = this.userAuthService.getUserDetailById(id);
    //     model.addAttribute("deleteUser", user);
    //     return "UserAuthDelete";
    // }

    // @PostMapping("/admin/userAuth/delete")
    // public String PostDeleteUser(Model model, @ModelAttribute("deleteUser") User user) {
    //     System.out.println("check ne nha " + user.getId());
    //     this.userAuthService.handleDeleteUser(user.getId());
    //     return "redirect:/userAuthTable";
    // }
}
