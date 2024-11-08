package dev.danvega.h2_demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.danvega.h2_demo.domain.User;

import dev.danvega.h2_demo.repository.UserAuthRepository;

@Service
public class UserAuthService {
    @Autowired
    private final UserAuthRepository userAuthRepository;

    public UserAuthService(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    public User handleSaveUser(User user) {

        return this.userAuthRepository.save(user);
    }

    public List<User> getAllUser() {
        return this.userAuthRepository.findAll();
    }

    public User getUserDetailById(long id) {
        return this.userAuthRepository.findById(id);

    }

    public User handleDeleteUser(long id) {
        return this.userAuthRepository.deleteById(id);
    }
}
