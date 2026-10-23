package edu.course.lab04;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class IssueRegistryTest {


    @Test
    void basicOperations_put_get_remove_contains_size() {
        IssueRegistry reg = new IssueRegistry();
        IssueId id     = new IssueId("CORE", 1);
        ProjectTask t1 = new ProjectTask("Fix bug", "Alice", false);

        assertTrue(reg.isEmpty());
        assertEquals(0, reg.size());
        assertNull(reg.put(id, t1));         
        assertEquals(1, reg.size());
        assertTrue(reg.contains(id));
        assertEquals(t1, reg.get(id));

        ProjectTask t2 = new ProjectTask("Fix bug", "Bob", true);
        assertEquals(t1, reg.put(id, t2));   
        assertEquals(1, reg.size());         
        assertEquals(t2, reg.get(id));

        assertEquals(t2, reg.remove(id));
        assertFalse(reg.contains(id));
        assertEquals(0, reg.size());
        assertTrue(reg.isEmpty());
    }


    @Test
    void experiment1_equalKeyFromDifferentInstanceFindsSameValue() {
        IssueRegistry reg = new IssueRegistry();
        ProjectTask task  = new ProjectTask("Add logs", "Alice", false);

        IssueId storedKey = new IssueId("CORE", 42);
        reg.put(storedKey, task);

        IssueId probe = new IssueId("CORE", 42); 
        assertEquals(storedKey, probe);
        assertEquals(storedKey.hashCode(), probe.hashCode());

        assertTrue(reg.contains(probe));
        assertEquals(task, reg.get(probe));
    }


    @Test
    void experiment2_reinsertWithEqualKeyReplacesValueWithoutGrowth() {
        IssueRegistry reg = new IssueRegistry();
        ProjectTask oldTask = new ProjectTask("Old", "Alice", false);
        ProjectTask newTask = new ProjectTask("New", "Bob",   true);

        reg.put(new IssueId("API", 1), oldTask);
        int sizeBefore = reg.size();

        ProjectTask previous = reg.put(new IssueId("API", 1), newTask);

        assertEquals(oldTask, previous);       
        assertEquals(sizeBefore, reg.size());  
        assertEquals(newTask, reg.get(new IssueId("API", 1)));
    }

    @Test
    void experiment3_unequalKeysWithSameHashCodeKeepSeparateValues() {
        IssueId a = new IssueId("B", 1);
        IssueId b = new IssueId("A", 32);

        assertEquals(a.hashCode(), b.hashCode(),
                "precondition: эти IssueId должны иметь одинаковый hashCode");
        assertNotEquals(a, b);

        IssueRegistry reg = new IssueRegistry();
        ProjectTask ta = new ProjectTask("TaskA", "Alice", false);
        ProjectTask tb = new ProjectTask("TaskB", "Bob",   false);

        reg.put(a, ta);
        reg.put(b, tb);

        assertEquals(2, reg.size(), "коллизия не должна склеивать разные ключи");
        assertEquals(ta, reg.get(new IssueId("B", 1)));
        assertEquals(tb, reg.get(new IssueId("A", 32)));
    }


    @Test
    void experiment4_equalsReturnsFalseForNullOrOtherType() {
        IssueId id = new IssueId("CORE", 7);

        assertFalse(id.equals(null));
        assertFalse(id.equals("CORE-7"));
        assertFalse(id.equals(new Object()));
        assertNotEquals(id, "CORE-7");

        assertTrue(id.equals(id));
        IssueId twin = new IssueId("CORE", 7);
        assertTrue(id.equals(twin));
        assertTrue(twin.equals(id));
    }


    @Test
    void experiment5_mutatingKeyAfterInsertBreaksLookup() {
        MutableKey key = new MutableKey("CORE", 1);

        Map<MutableKey, String> map = new HashMap<>();
        map.put(key, "stored");

        assertEquals("stored", map.get(key));

        key.number = 2;

        assertNull(map.get(key));

        assertEquals(1, map.size());
    }

    static final class MutableKey {
        String project;
        int number;

        MutableKey(String project, int number) {
            this.project = project;
            this.number  = number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof MutableKey)) {
                return false;
            }
            MutableKey other = (MutableKey) o;
            return number == other.number
                    && project.equals(other.project);
        }

        @Override
        public int hashCode() {
            return 31 * project.hashCode() + number;
        }
    }
}