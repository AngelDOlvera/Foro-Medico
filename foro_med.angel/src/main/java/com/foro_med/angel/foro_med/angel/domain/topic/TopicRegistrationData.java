package com.foro_med.angel.foro_med.angel.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRegistrationData(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        int author,
        @NotBlank
        String course) {
}
