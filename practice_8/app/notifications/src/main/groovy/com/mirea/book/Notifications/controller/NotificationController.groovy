package com.mirea.book.notifications

import java.util.List
import java.util.Date

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping('/notifications')
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository
    
    @Autowired
    private CustomerRepository customerRepository

    @Autowired
    private RedisTemplate<String, Object> redisTemplate

    @GetMapping('/unread')
    public ResponseEntity<List<Notification>> getUnreadNotifications(@RequestHeader('Authorization') String sessionToken) {
        Customer customer = (Customer) redisTemplate.opsForValue().get(sessionToken)

        if (customer == null) {
            return ResponseEntity.badRequest().body('Invalid session token')
        }

        String username = customer.getUsername()

        if (username == null) {
            return ResponseEntity.badRequest().body('Username not found')
        }

        Customer persistedCustomer = customerRepository.findByUsername(username)

        if (persistedCustomer == null) {
            return ResponseEntity.badRequest().body('Customer not found')
        }

        List<Notification> unreadNotifications = notificationRepository.findByTimestampAfter(
            persistedCustomer.getLastReadNotificationTimestamp()
        )

        persistedCustomer.setLastReadNotificationTimestamp(new Date())
        
        customerRepository.save(persistedCustomer)

        return ResponseEntity.ok(unreadNotifications)
    }
}