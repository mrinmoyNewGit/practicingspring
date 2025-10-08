package com.example.practicingSpring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.practicingSpring.model.User;
import com.example.practicingSpring.repository.UserRepository;
import com.example.practicingSpring.security.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth") 
@Slf4j //for log
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    //private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping("/register-superadmin")
    public String registerSuperAdmin(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            log.error("Superadmin already exists!");
            return "Superadmin already exists!";
        }
        User superadmin = new User(null, user.getName(), user.getEmail(), encoder.encode(user.getPassword()), "SUPERADMIN");
        userRepository.save(superadmin);
        return "Superadmin created!";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User users = userRepository.findByEmail(user.getEmail());
        if (users != null && encoder.matches(user.getPassword(), users.getPassword())) {
            return jwtUtil.generateToken(users.getEmail(), users.getRole());
        }
        throw new RuntimeException("Invalid email or password");
    }

}
