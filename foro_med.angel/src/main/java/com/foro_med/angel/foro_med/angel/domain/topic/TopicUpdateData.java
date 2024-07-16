package com.foro_med.angel.foro_med.angel.domain.topic;

import jakarta.validation.constraints.NotNull;

public record TopicUpdateData(
        @NotNull
        Long id,
        String title,
        String message) {
}
