package edu.course.lab04;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class IssueRegistry {

    private final Map<IssueId, ProjectTask> issues = new HashMap<>();

    public ProjectTask put(IssueId id, ProjectTask task) {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(task, "task");
        return issues.put(id, task);
    }

    public ProjectTask get(IssueId id) {
        return issues.get(id);
    }

    public ProjectTask remove(IssueId id) {
        return issues.remove(id);
    }

    public boolean contains(IssueId id) {
        return issues.containsKey(id);
    }

    public int size() {
        return issues.size();
    }

    public boolean isEmpty() {
        return issues.isEmpty();
    }
}