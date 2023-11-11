package com.mirea.book.notifications

import java.util.Date

import jakarta.persistence.*
import lombok.Data

@Data
@Entity
public class Customer {
    
    @Id
    @GeneratedValue
    Long id
    
    @Column
    String email

    @Column
    String password

    @Column
    String username

    @Column(name='last_read_notification_timestamp')
    Date lastReadNotificationTimestamp
}