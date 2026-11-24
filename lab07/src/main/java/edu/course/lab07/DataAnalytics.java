package edu.course.lab07;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DataAnalytics {

    public List<TaskItem> filterByStatus(List<TaskItem> tasks, TaskStatus status) {
        return tasks.stream()
                .filter(task -> task.status() == status)
                .toList();
    }

    public List<String> sortedNormalizedCategories(List<TaskItem> tasks) {
        return tasks.stream()
                .map(task -> task.category().trim().toLowerCase(Locale.ROOT))
                .filter(category -> !category.isEmpty())
                .distinct()
                .sorted()
                .toList();
    }

    public Map<String, Long> countByCategory(List<TaskItem> tasks) {
        return tasks.stream()
                .collect(Collectors.groupingBy(
                        task -> task.category().trim().toLowerCase(Locale.ROOT),
                        Collectors.counting()
                ));
    }

    public Optional<TaskItem> findMaxEffortTask(List<TaskItem> tasks) {
        return tasks.stream()
                .max(Comparator.comparingInt(TaskItem::effortHours));
    }

    public List<TaskItem> sortByEffortDescThenTitle(List<TaskItem> tasks) {
        return tasks.stream()
                .sorted(Comparator.comparingInt(TaskItem::effortHours).reversed()
                        .thenComparing(TaskItem::title))
                .toList();
    }

    public Map<String, Double> averageEffortByCategory(List<TaskItem> tasks) {
        return tasks.stream()
                .collect(Collectors.groupingBy(
                        task -> task.category().trim().toLowerCase(Locale.ROOT),
                        Collectors.averagingInt(TaskItem::effortHours)
                ));
    }
}