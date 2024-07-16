package com.foro_med.angel.foro_med.angel.domain.topic;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(name = "Topic")
@Table(name = "topics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private String date;
    private Boolean status;
    private int author;
    private String course;

    public Topic(TopicRegistrationData topicRegistrationData){
        this.title = topicRegistrationData.title();
        this.message = topicRegistrationData.message();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        this.date = currentDateTime.format(formatter);
        this.status = true;
        this.author = topicRegistrationData.author();
        this.course = topicRegistrationData.course();
    }

    public void updateTopic(TopicUpdateData topicUpdateData){
        if(topicUpdateData.title() != null)
            this.title = topicUpdateData.title();
        if(topicUpdateData.message() != null)
            this.message = topicUpdateData.message();
    }

    public void deactivateTopic(){
        this.status = false;
    }
}
