package edu.course.lab03;

public class ProjectTask {
    private final String title;
    private final int effort;
    private final int status;

    public ProjectTask(String title, int effort, int status) {
        this.title = title;
        this.effort = effort;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public int getEffort() {
        return effort;
    }

    public int getStatus() {
        return status;
    }
}