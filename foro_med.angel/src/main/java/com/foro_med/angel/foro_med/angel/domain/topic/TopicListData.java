package com.foro_med.angel.foro_med.angel.domain.topic;

public record TopicListData(
        Long id,
        String title,
        String message,
        String date ) {

    public TopicListData(Topic topic){
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getDate());
    }
}
