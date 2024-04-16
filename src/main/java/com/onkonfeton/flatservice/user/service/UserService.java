package com.onkonfeton.flatservice.user.service;


import com.onkonfeton.flatservice.user.model.User;

import java.util.Optional;

public interface UserService {

    void save(User user);

    Optional<User> findByEmail(String email);
}
