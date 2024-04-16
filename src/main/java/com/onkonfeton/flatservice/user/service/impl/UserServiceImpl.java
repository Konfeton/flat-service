package com.onkonfeton.flatservice.user.service.impl;

import com.onkonfeton.flatservice.user.model.User;
import com.onkonfeton.flatservice.user.repository.UserRepository;
import com.onkonfeton.flatservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    public void save(User user) {
        userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
            return userRepository.findByEmail(email);
        }

}
