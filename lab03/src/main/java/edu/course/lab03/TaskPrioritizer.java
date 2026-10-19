package edu.course.lab03;

public class TaskPrioritizer {
    private final PriorityPolicy priorityPolicy;

    public TaskPrioritizer(PriorityPolicy priorityPolicy) {
        this.priorityPolicy = priorityPolicy;
    }

    public ProjectTask selectMoreImportant(ProjectTask task1, ProjectTask task2) {
        if (task1 == null) return task2;
        if (task2 == null) return task1;

        int p1 = priorityPolicy.calculatePriority(task1);
        int p2 = priorityPolicy.calculatePriority(task2);

        return p1 >= p2 ? task1 : task2;
    }
}