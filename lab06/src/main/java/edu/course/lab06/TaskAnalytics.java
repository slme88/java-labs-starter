package edu.course.lab06;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TaskAnalytics {

    public static Map<String, Integer> countByStatus(List<ProjectTask> tasks) {
        Map<String, Integer> counts = new LinkedHashMap<>();
        for (ProjectTask task : tasks) {
            String status = task.getStatus();
            Integer current = counts.get(status);
            counts.put(status, (current == null) ? 1 : current + 1);
        }
        return counts;
    }

    public static Set<String> getUniqueCategories(List<ProjectTask> tasks) {
        Set<String> categories = new LinkedHashSet<>();
        for (ProjectTask task : tasks) {
            categories.add(task.getCategory());
        }
        return categories;
    }

    public static List<ProjectTask> sortByEffortAndId(List<ProjectTask> tasks) {
        List<ProjectTask> copy = new ArrayList<>(tasks);
        copy.sort(
            Comparator.comparingInt(ProjectTask::getEffortHours)
                      .thenComparingInt(ProjectTask::getId)
        );
        return copy;
    }
}