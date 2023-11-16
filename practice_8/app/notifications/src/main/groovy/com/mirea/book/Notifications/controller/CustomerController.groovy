package com.mirea.book.notifications

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping('/customer')
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder

    @Autowired
    private RedisTemplate<String, Object> redisTemplate

    @PostMapping('/register')
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerDTO customerDTO) {

        if (customerRepository.findByUsername(customerDTO.getUsername()) != null) {
            return ResponseEntity.badRequest().body('Customer already exists')
        }

        Customer customer = new Customer(customerDTO.getUsername(), bCryptPasswordEncoder.encode(customerDTO.getPassword()))
        
        customerRepository.save(customer)
        
        return ResponseEntity.ok('Customer registered successfully')
    }

    @PostMapping('/login')
    public ResponseEntity<String> loginCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findByUsername(customerDTO.getUsername())

        if (existingCustomer == null || 
            !bCryptPasswordEncoder.matches(customerDTO.getPassword(), existingCustomer.getPassword())) {
            return ResponseEntity.badRequest().body('Invalid username or password')
        }

        String sessionToken = UUID.randomUUID().toString()

        redisTemplate.opsForValue().set(sessionToken, existingCustomer, 30, TimeUnit.MINUTES)

        return ResponseEntity.ok().body(sessionToken)
    }

    @PostMapping('/logout')
    public ResponseEntity<String> logoutCustomer(@RequestHeader('Authorization') String sessionToken) {
        Boolean deleted = redisTemplate.delete(sessionToken)

        if (deleted != null && deleted) {
            return ResponseEntity.ok('Logout successful')
        } else {
            return ResponseEntity.badRequest().body('Invalid session token')
        }
    }
}