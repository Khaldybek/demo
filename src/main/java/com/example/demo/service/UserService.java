package com.example.demo.service;

import com.example.demo.dto.JWTauthToken;
import com.example.demo.service.JwtService;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private  final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public JWTauthToken addUser(User user) {
        if (userRepository.existsByGmail(user.getGmail())) {
            throw new RuntimeException("Email is already taken");
        }
         userRepository.save(user);
        String jwt =jwtService.generateToken(user);
        return new JWTauthToken(jwt);
    }
//    public UserDetailsService userDetailsService(){
//        return  userRepository.getById();
//    }



    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setLastName(userDetails.getLastName());
        user.setFirstName(userDetails.getFirstName());
        user.setGmail(userDetails.getGmail());
        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        user.setRole(userDetails.getRole());
        user.setBirthdate(userDetails.getBirthdate());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public JWTauthToken login(String email, String password) {
        try {
            userRepository.findByGmailAndPassword(email, password);
            return new JWTauthToken("");
        } catch (Exception e) {
            return null;
        }
    }


}
