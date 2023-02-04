package com.singfung.demo.service;

import com.singfung.demo.model.dto.UserDTO;
import com.singfung.demo.model.entity.User;
import com.singfung.demo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public User addUser(UserDTO dto) {
        if(userRepository.findByUsername(dto.getUsername()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "username has been registered");
        }

        if(userRepository.findByEmail(dto.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "email has been registered");
        }

        User user = new User(dto);

        user.setCreateTime(new Date());
        user.setTs(new Date());

        user = userRepository.save(user);
        return user;
    }

    public List<User> listAllUsers() {
        return userRepository.findByOrderByIdDesc();
    }

    public User getUserById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return userOptional.get();
    }

    public User updateUser(Integer id, UserDTO dto) {
        if(dto.getUsername() != null && userRepository.findByUsernameAndIdNot(dto.getUsername(), id) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "username has been registered");
        }

        if(dto.getEmail() != null && userRepository.findByEmailAndIdNot(dto.getEmail(), id) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "email has been registered");
        }

        User user = getUserById(id);
        dto.setPassword(user.getPassword());
        BeanUtils.copyProperties(dto, user);
        user.setTs(new Date());

        return userRepository.save(user);
    }
}
