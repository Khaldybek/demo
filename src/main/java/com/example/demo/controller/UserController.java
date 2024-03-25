package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService users;
    @Autowired
    public UserController(UserService users) {
        this.users = users;
    }
    @GetMapping("/allUsers")
    public List<User> getUsers(){
        return users.getUser();
    };
    @PostMapping("/adduser")
    public void registerNewUser(@RequestBody User user){
        users.addNewUser(user);
    }
    @DeleteMapping(path="{userId}")
    public void deleteUser(@PathVariable("userId")Long id){
        users.deleteUser(id);
    }
    @PutMapping("update")
    @PatchMapping
    public void updateUser(@RequestBody User userUpdate){
        users.updateUser(userUpdate);
    }
}
