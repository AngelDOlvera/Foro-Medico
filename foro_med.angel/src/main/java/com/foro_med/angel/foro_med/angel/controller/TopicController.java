package com.foro_med.angel.foro_med.angel.controller;

import com.foro_med.angel.foro_med.angel.domain.topic.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicRepository topicRepository;

    public TopicController(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @PostMapping
    public ResponseEntity<TopicResponseData> createTopic(@Valid @RequestBody TopicRegistrationData topicRegistrationData) {
        Topic topic = new Topic(topicRegistrationData);
        topicRepository.save(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TopicResponseData(topic));
    }

    @GetMapping
    public ResponseEntity<Page<TopicListData>> getAllTopics(@PageableDefault(size = 10, sort = "date", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Topic> topics = topicRepository.findByStatusTrue(pageable);
        return ResponseEntity.ok(topics.map(TopicListData::new));
    }
    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseData> getTopicById(@PathVariable Long id) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isPresent()) {
            return ResponseEntity.ok(new TopicResponseData(optionalTopic.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicResponseData> updateTopic(@PathVariable Long id, @Valid @RequestBody TopicUpdateData topicUpdateData) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isPresent()) {
            Topic topic = optionalTopic.get();
            topic.updateTopic(topicUpdateData);
            topicRepository.save(topic);
            return ResponseEntity.ok(new TopicResponseData(topic));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isPresent()) {
            topicRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
