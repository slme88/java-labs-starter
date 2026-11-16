package edu.course.lab06;

import java.util.Objects;

public class ProjectTask {
    private final int id;
    private final String title;
    private final String status;
    private final String category;
    private final int effortHours;

    public ProjectTask(int id, String title, String status, String category, int effortHours) {
        this.id = id;
        this.title = Objects.requireNonNull(title);
        this.status = Objects.requireNonNull(status);
        this.category = Objects.requireNonNull(category);
        this.effortHours = effortHours;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getStatus() { return status; }
    public String getCategory() { return category; }
    public int getEffortHours() { return effortHours; }
}