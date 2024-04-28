package com.example.demo.Config;

import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private  final UserRepository repository;
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> repository.findByGmail(username)
                .orElseThrow(()->new UsernameNotFoundException("user nort found"));
    }
}




























































