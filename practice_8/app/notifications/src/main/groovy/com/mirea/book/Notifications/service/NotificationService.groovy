package com.mirea.book.notifications

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
public class KafkaMessageListener {

    @Autowired
    private NotificationRepository notificationRepository

    @KafkaListener(topics = "book-created", groupId = "notifications-group")
    public void listen(BookDTO bookDTO) {
        String title = "New reading journey begins: \"${bookDTO.getTitle()}\" by ${bookDTO.getAuthor()}"
        String text = "Embark on an enchanting adventure published in ${bookDTO.getYear()}, this masterpiece falls under the genre of ${bookDTO.getGenre()}. Let the pages unfold the magic!"
    
        Notification notification = new Notification()
        notification.setTitle(title)
        notification.setText(text)
        notification.setTimestamp(new Date())

        notificationRepository.save(notification)

        println "Notification created: ${title}; ${text}"
    }
}