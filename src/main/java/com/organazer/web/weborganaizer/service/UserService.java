package com.organazer.web.weborganaizer.service;


import com.organazer.web.weborganaizer.model.User;
import com.organazer.web.weborganaizer.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private  final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public void delete(User user){
        userRepository.delete(user);
    }
    public User getUserById(Long id){
        return userRepository.findById(id).orElse(new User());
    }
    public User findUserByLogin(String login){
        return userRepository.findUserByLogin(login);
    }
    public boolean userIsExistsByLoginAndPassword(User user){
        return userRepository.existsByLoginAndPassword(user.getLogin(), user.getPassword());
    }
    public boolean userIsExistsByLogin(String login){
        return userRepository.existsByLogin(login);
    }
}
