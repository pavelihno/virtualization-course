package com.mirea.book.notifications

import java.util.Date

import jakarta.persistence.*

@Entity
public class Customer {
    
    @Id
    @GeneratedValue
    Long id

    @Column(unique=true)
    String username

    @Column
    String password

    @Column(name='last_read_notification_timestamp')
    Date lastReadNotificationTimestamp

    public Customer() {}

    public Customer(String username, String password) {
        this.username = username
        this.password = password
    }
    
    public String getUsername() {
        return username
    }

    public void setUsername(String username) {
        this.username = username
    }

    public String getPassword() {
        return password
    }

    public void setPassword(String password) {
        this.password = password
    }

    public void setLastReadNotificationTimestamp(Date lastReadNotificationTimestamp) {
        this.lastReadNotificationTimestamp = lastReadNotificationTimestamp
    }
}