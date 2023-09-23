package com.setxpro.setxpro.controllers;

import com.setxpro.setxpro.domain.user.User;
import com.setxpro.setxpro.domain.user.UserDTO;
import com.setxpro.setxpro.domain.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserDTO user) {
        if(this.userRepository.findByLogin(user.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.password());
        User newUser = new User(user.name(), user.login(), encryptedPassword, user.role());
        // Save

        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
