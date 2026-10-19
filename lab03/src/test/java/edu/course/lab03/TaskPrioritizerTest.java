package edu.course.lab03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskPrioritizerTest {

    @Test
    void testEffortPriorityPolicy() {
        ProjectTask t1 = new ProjectTask("Task 1", 5, 0);
        ProjectTask t2 = new ProjectTask("Task 2", 10, 0);

        TaskPrioritizer prioritizer = new TaskPrioritizer(new EffortPriorityPolicy());
        assertEquals(t2, prioritizer.selectMoreImportant(t1, t2));
    }

    @Test
    void testEffortPriorityPolicySecondTest() {
        ProjectTask t1 = new ProjectTask("Task 1", 15, 0);
        ProjectTask t2 = new ProjectTask("Task 2", 10, 0);

        TaskPrioritizer prioritizer = new TaskPrioritizer(new EffortPriorityPolicy());
        assertEquals(t1, prioritizer.selectMoreImportant(t1, t2));
    }

    @Test
    void testStatusPriorityPolicy() {
        ProjectTask t1 = new ProjectTask("Task 1", 5, 1);
        ProjectTask t2 = new ProjectTask("Task 2", 5, 2);

        TaskPrioritizer prioritizer = new TaskPrioritizer(new StatusPriorityPolicy());
        assertEquals(t2, prioritizer.selectMoreImportant(t1, t2));
    }

    @Test
    void testStatusPriorityPolicySecondTest() {
        ProjectTask t1 = new ProjectTask("Task 1", 5, 2);
        ProjectTask t2 = new ProjectTask("Task 2", 5, 1);

        TaskPrioritizer prioritizer = new TaskPrioritizer(new StatusPriorityPolicy());
        assertEquals(t1, prioritizer.selectMoreImportant(t1, t2));
    }

    @Test
    void testPolicyReplacement() {
        ProjectTask t1 = new ProjectTask("Task 1", 10, 1);
        ProjectTask t2 = new ProjectTask("Task 2", 2, 2);

        TaskPrioritizer prioritizer = new TaskPrioritizer(new EffortPriorityPolicy());
        assertEquals(t1, prioritizer.selectMoreImportant(t1, t2));

        prioritizer = new TaskPrioritizer(new StatusPriorityPolicy());
        assertEquals(t2, prioritizer.selectMoreImportant(t1, t2));
    }
}