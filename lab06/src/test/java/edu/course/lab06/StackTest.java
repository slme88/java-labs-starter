package edu.course.lab06;

import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void testEmptyStack() {
        Stack<String> stack = new Stack<>();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        assertThrows(NoSuchElementException.class, stack::pop);
        assertThrows(NoSuchElementException.class, stack::peek);
    }

    @Test
    void testPushPeekPopWithStrings() {
        Stack<String> stack = new Stack<>();
        stack.push("first");
        stack.push("second");

        assertFalse(stack.isEmpty());
        assertEquals(2, stack.size());
        assertEquals("second", stack.peek());
        assertEquals(2, stack.size());

        assertEquals("second", stack.pop());
        assertEquals("first", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void testStackWithCustomObjectsAndNullRejection() {
        Stack<ProjectTask> taskStack = new Stack<>();
        assertThrows(NullPointerException.class, () -> taskStack.push(null));

        ProjectTask t1 = new ProjectTask(1, "Setup", "DONE", "Infra", 2);
        ProjectTask t2 = new ProjectTask(2, "Code", "IN_PROGRESS", "Backend", 5);

        taskStack.push(t1);
        taskStack.push(t2);

        assertEquals(t2, taskStack.pop());
        assertEquals(t1, taskStack.peek());
    }
}