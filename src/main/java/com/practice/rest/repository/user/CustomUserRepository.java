package com.practice.rest.repository.user;

import com.practice.rest.model.User;

public interface CustomUserRepository {
    public User findOneById(Long id);
}
