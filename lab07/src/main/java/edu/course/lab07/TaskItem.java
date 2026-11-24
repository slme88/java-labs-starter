package edu.course.lab07;

import java.util.Objects;

public record TaskItem(
        TaskStatus status,
        String category,
        String title,
        int effortHours
) {
    public TaskItem {
        Objects.requireNonNull(status, "status must not be null");
        Objects.requireNonNull(category, "category must not be null");
        Objects.requireNonNull(title, "title must not be null");
        if (effortHours < 0) {
            throw new IllegalArgumentException("effortHours must be non-negative");
        }
    }
}