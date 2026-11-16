package edu.course.lab06;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoxTest {

    @Test
    void testStringBox() {
        Box<String> stringBox = new Box<>("Initial");
        assertEquals("Initial", stringBox.get());

        stringBox.set("Updated");
        assertEquals("Updated", stringBox.get());
    }

    @Test
    void testIntegerBox() {
        Box<Integer> intBox = new Box<>(100);
        assertEquals(100, intBox.get());

        intBox.set(250);
        assertEquals(250, intBox.get());
    }
}