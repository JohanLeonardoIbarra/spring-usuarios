package com.practice.rest.controller;

import com.practice.rest.dto.UserDto;
import com.practice.rest.repository.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/user")
    public com.practice.rest.model.User create(@Valid @RequestBody UserDto userData) {
        try {
            com.practice.rest.model.User user = new com.practice.rest.model.User();
            user.setData(userData);

            return userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/user")
    public List<com.practice.rest.model.User> list(Pageable pageable) {
        Page<com.practice.rest.model.User> users = userRepository.findAll(pageable);

        return users.getContent();
    }

    @PutMapping("/user/{id}")
    public com.practice.rest.model.User Update(@Valid @RequestBody UserDto user, @PathVariable Long id) {
        com.practice.rest.model.User savedUser = userRepository.findOneById(id);
        savedUser.setData(user);

        return userRepository.save(savedUser);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        com.practice.rest.model.User user = userRepository.findOneById(id);
        userRepository.delete(user);
    }
}
