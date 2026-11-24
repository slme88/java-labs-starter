package edu.course.lab07;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataAnalyticsTest {

    private final DataAnalytics analytics = new DataAnalytics();

    @Test
    void shouldHandleEmptyListAcrossOperations() {
        List<TaskItem> empty = List.of();

        assertTrue(analytics.filterByStatus(empty, TaskStatus.DONE).isEmpty());
        assertTrue(analytics.sortedNormalizedCategories(empty).isEmpty());
        assertTrue(analytics.countByCategory(empty).isEmpty());
        assertEquals(Optional.empty(), analytics.findMaxEffortTask(empty));
        assertTrue(analytics.sortByEffortDescThenTitle(empty).isEmpty());
    }

    @Test
    void shouldHandleSingleElementList() {
        TaskItem single = new TaskItem(TaskStatus.IN_PROGRESS, " Backend ", "REST API", 6);
        List<TaskItem> input = List.of(single);

        assertEquals(List.of(single), analytics.filterByStatus(input, TaskStatus.IN_PROGRESS));
        assertTrue(analytics.filterByStatus(input, TaskStatus.DONE).isEmpty());
        assertEquals(List.of("backend"), analytics.sortedNormalizedCategories(input));
        assertEquals(Map.of("backend", 1L), analytics.countByCategory(input));
        assertEquals(Optional.of(single), analytics.findMaxEffortTask(input));
        assertEquals(List.of(single), analytics.sortByEffortDescThenTitle(input));
    }

    @Test
    void shouldFilterGroupAndHandleEqualPrimarySortKeysWithoutMutatingInput() {
        TaskItem t1 = new TaskItem(TaskStatus.TODO, "Backend", "Auth Service", 8);
        TaskItem t2 = new TaskItem(TaskStatus.DONE, "frontend", "UI Table", 8);
        TaskItem t3 = new TaskItem(TaskStatus.DONE, "BACKEND", "DB Migration", 12);
        TaskItem t4 = new TaskItem(TaskStatus.IN_PROGRESS, "DevOps", "CI Pipeline", 4);

        List<TaskItem> mutableInput = new ArrayList<>(List.of(t1, t2, t3, t4));
        List<TaskItem> snapshot = List.copyOf(mutableInput);

        List<TaskItem> doneTasks = analytics.filterByStatus(mutableInput, TaskStatus.DONE);
        assertEquals(List.of(t2, t3), doneTasks);

        assertEquals(List.of("backend", "devops", "frontend"), analytics.sortedNormalizedCategories(mutableInput));
        assertEquals(Map.of("backend", 2L, "frontend", 1L, "devops", 1L), analytics.countByCategory(mutableInput));

        Optional<TaskItem> maxTask = analytics.findMaxEffortTask(mutableInput);
        assertTrue(maxTask.isPresent());
        assertEquals(t3, maxTask.orElseThrow());

        List<TaskItem> sorted = analytics.sortByEffortDescThenTitle(mutableInput);
        assertEquals(List.of(t3, t1, t2, t4), sorted);

        Map<String, Double> avgEffort = analytics.averageEffortByCategory(mutableInput);
        assertEquals(10.0, avgEffort.get("backend"));
        assertEquals(8.0, avgEffort.get("frontend"));
        assertEquals(4.0, avgEffort.get("devops"));

        assertEquals(snapshot, mutableInput);
    }
}