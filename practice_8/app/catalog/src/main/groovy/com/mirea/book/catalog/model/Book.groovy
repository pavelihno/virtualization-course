package com.mirea.book.catalog

import jakarta.persistence.*
import lombok.Data

@Data
@Entity
public class Book {
    
    @Id
    @GeneratedValue
    Long id

    @Column
    String author
    
    @Column
    String title
    
    @Column
    int year
    
    @Column
    String genre
}