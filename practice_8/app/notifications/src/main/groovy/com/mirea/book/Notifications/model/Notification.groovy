package com.mirea.book.notifications

import jakarta.persistence.*

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

    public String getTitle() {
        return title
    }

    public void setTitle(String title) {
        this.title = title
    }

    public String getText() {
        return text
    }

    public void setText(String text) {
        this.text = text
    }

    public Date getTimestamp() {
        return timestamp
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp
    }
}