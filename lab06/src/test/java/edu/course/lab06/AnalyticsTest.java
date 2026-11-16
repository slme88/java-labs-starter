package edu.course.lab06;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class AnalyticsTest {

    @Test
    void testTaskAnalytics() {
        ProjectTask t1 = new ProjectTask(10, "API", "OPEN", "Backend", 8);
        ProjectTask t2 = new ProjectTask(5, "UI", "DONE", "Frontend", 4);
        ProjectTask t3 = new ProjectTask(2, "DB", "OPEN", "Backend", 4);

        List<ProjectTask> original = List.of(t1, t2, t3);

        Map<String, Integer> statusCounts = TaskAnalytics.countByStatus(original);
        assertEquals(2, statusCounts.get("OPEN"));
        assertEquals(1, statusCounts.get("DONE"));

        Set<String> categories = TaskAnalytics.getUniqueCategories(original);
        assertEquals(Set.of("Backend", "Frontend"), categories);

        List<ProjectTask> sorted = TaskAnalytics.sortByEffortAndId(original);
        assertEquals(List.of(t3, t2, t1), sorted);
        assertEquals(List.of(t1, t2, t3), original); 
    }
}