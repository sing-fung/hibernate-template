package com.singfung.demo.service;

import com.singfung.demo.model.entity.User;
import com.singfung.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sing-fung
 * @since 1/22/2023
 */

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        user = userRepository.save(user);
        return user;
    }
}
