package com.example.practicingSpring.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.practicingSpring.filter.JwtFilter;

@Configuration
public class FilterConfig {

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter(); // ✅ create the filter bean
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterRegistration(JwtFilter jwtFilter) {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(jwtFilter);

        // Protect selected routes
        registrationBean.addUrlPatterns("/admin-dashboard/*");

        return registrationBean;
    }
}
