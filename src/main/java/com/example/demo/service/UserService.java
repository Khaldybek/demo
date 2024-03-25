package com.example.demo.service;

import  ch.qos.logback.classic.spi.IThrowableProxy;
import com.example.demo.entity.User;
import com.example.demo.entity.Vacancy;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VacancyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public List<User> getUser(){
        return userRepository.findAll();

    }
    public void addNewUser(User user){
        String email= user.getGmail();

        Optional<User> userByGmail = userRepository.findUserByGmail(user.getGmail());
        if(userByGmail.isPresent()){
            throw new IllegalStateException("email token");
        }
        userRepository.save(user);
    }
    public  void deleteUser(Long userId){
        boolean exists=userRepository.existsById(userId);
        if (!exists){
            throw new IllegalStateException("user whith id : "+userId+" does not exists");
        }
        else {
            userRepository.deleteById(userId);
        }
    }
    public void updateUser(User user) {
        Long id = user.getId();
        User userUp = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User with id does not exist"));

        Optional<User> userByEmail = userRepository.findUserByGmail(user.getGmail());
        if (userByEmail.isPresent() && !userByEmail.get().getId().equals(user.getId())) {
            throw new IllegalStateException("Email is already taken");
        }


        userRepository.save(user);
    }
    public User getById(Long Id){
        return userRepository.getById(Id);
    }


}
