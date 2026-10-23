package edu.course.lab04;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void valuePutByOneInstanceIsFoundByAnotherEqualInstance() {
        Book key   = new Book("978-0-13-468599-1", "Effective Java");
        Book probe = new Book("978-0-13-468599-1", "Effective Java, 3rd edition");

        assertEquals(key, probe);
        assertEquals(key.hashCode(), probe.hashCode());

        Map<Book, String> shelf = new HashMap<>();
        shelf.put(key, "shelf A-1");

        assertTrue(shelf.containsKey(probe));
        assertEquals("shelf A-1", shelf.get(probe));
    }

    @Test
    void booksWithDifferentIsbnAreNotEqual() {
        Book a = new Book("111-1", "Some book");
        Book b = new Book("222-2", "Some book"); 

        assertNotEquals(a, b);
        assertNotEquals(a.hashCode(), b.hashCode());
    }
}