package dev.danvega.h2_demo.service;

import dev.danvega.h2_demo.domain.UserDemo;
import dev.danvega.h2_demo.repository.UserDemoRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserDemoService {
    @Autowired
    private final UserDemoRepository userRepository;
    // public void saveOrUpdate(UserDemo user)
    // {
    // userRepository.save(user);
    // }
    // public String handleHello() {
    // return "Xin chao minh la thanh ";
    // };

    public UserDemoService(UserDemoRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDemo handleSaveUser(UserDemo user) {

        return this.userRepository.save(user);
    }

    public List<UserDemo> getAllUser() {
        return this.userRepository.findAll();
    }

    public UserDemo getUserDetailById(long id) {
        return this.userRepository.findById(id);

    }

    public UserDemo handleDeleteUser(long id) {
        return this.userRepository.deleteById(id);
    }
}