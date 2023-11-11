package com.mirea.book.notifications

import java.util.Date

import jakarta.persistence.*
import lombok.Data

@Data
@Entity
public class Notification {
    
    @Id
    @GeneratedValue
    Long id

    @Column
    String title
    
    @Column
    String text
    
    @Column
    Date timestamp
}