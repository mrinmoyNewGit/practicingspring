package com.example.practicingSpring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to my Spring Boot app practicing";
    }

    @GetMapping("/admin-dashboard")
    public String adminDashboard(@RequestAttribute("email") String email,
            @RequestAttribute("role") String role) {
        if (!"SUPERADMIN".equals(role)) {
            return "Access Denied! Only SUPERADMIN can access this dashboard.";
        }
        return "Welcome to Admin Dashboard, " + email;
    }
}
