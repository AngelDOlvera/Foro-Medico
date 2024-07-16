package com.foro_med.angel.foro_med.angel.domain.topic;

public record TopicResponseData(
        Long id,
        String title,
        String message,
        String date,
        int author,
        String course ) {
    public TopicResponseData(Topic topic){
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getDate(), topic.getAuthor(), topic.getCourse());
    }
}
