package com.nyc.todoapp.service;

import com.nyc.todoapp.model.User;
import com.nyc.todoapp.repository.RoleRepository;
import com.nyc.todoapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(User user) {
        // Κρυπτογράφηση κωδικού πριν την αποθήκευση
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Ανάθεση του βασικού ρόλου Member
        user.setRoles(Collections.singleton(roleRepository.findByName("ROLE_MEMBER")));
        userRepository.save(user);
    }
}