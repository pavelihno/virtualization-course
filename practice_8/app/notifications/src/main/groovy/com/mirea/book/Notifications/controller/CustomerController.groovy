package com.mirea.book.notifications

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder

    @PostMapping("/register")
    public String registerUser(@RequestBody Customer user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "User already exists"
        }

        println user.getPassword()
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()))

        userRepository.save(user)
        return "User registered successfully"
    }

}