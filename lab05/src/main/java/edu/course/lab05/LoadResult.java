package edu.course.lab05;

import java.util.List;

public final class LoadResult {

    private final List<Task> tasks;
    private final List<ValidationError> errors;

    public LoadResult(List<Task> tasks, List<ValidationError> errors) {
        this.tasks = List.copyOf(tasks);
        this.errors = List.copyOf(errors);
    }

    public List<Task> getTasks() { return tasks; }
    public List<ValidationError> getErrors() { return errors; }

    @Override
    public String toString() {
        return "LoadResult{tasks=" + tasks + ", errors=" + errors + '}';
    }
}