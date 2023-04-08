package structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.set.linkedhash.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LinkedHashSetTest {
    private LinkedHashSet<Integer> linkedHashSet;

    @BeforeEach
    void setUp() {
        linkedHashSet = new LinkedHashSet<>();
    }

    @Test
    void testAdd() {
        assertTrue(linkedHashSet.add(1));
        assertTrue(linkedHashSet.add(2));
        assertFalse(linkedHashSet.add(1));
        assertFalse(linkedHashSet.add(2));
    }

    @Test
    void testRemove() {
        linkedHashSet.add(1);
        linkedHashSet.add(2);

        assertTrue(linkedHashSet.remove(1));
        assertTrue(linkedHashSet.remove(2));
        assertFalse(linkedHashSet.remove(3));
    }

    @Test
    void testContains() {
        linkedHashSet.add(1);
        linkedHashSet.add(2);

        assertTrue(linkedHashSet.contains(1));
        assertTrue(linkedHashSet.contains(2));
        assertFalse(linkedHashSet.contains(3));
    }

    @Test
    void testIsEmpty() {
        assertTrue(linkedHashSet.isEmpty());

        linkedHashSet.add(1);
        assertFalse(linkedHashSet.isEmpty());
    }

    @Test
    void testSize() {
        assertEquals(0, linkedHashSet.size());

        linkedHashSet.add(1);
        assertEquals(1, linkedHashSet.size());

        linkedHashSet.add(2);
        assertEquals(2, linkedHashSet.size());

        linkedHashSet.remove(1);
        assertEquals(1, linkedHashSet.size());

        linkedHashSet.clear();
        assertEquals(0, linkedHashSet.size());
    }

    @Test
    void testClear() {
        linkedHashSet.add(1);
        linkedHashSet.add(2);
        linkedHashSet.clear();

        assertTrue(linkedHashSet.isEmpty());
    }

    @Test
    void testIterator() {
        linkedHashSet.add(1);
        linkedHashSet.add(2);
        linkedHashSet.add(3);
        linkedHashSet.add(4);
        linkedHashSet.add(5);
        linkedHashSet.add(6);
        linkedHashSet.add(7);
        linkedHashSet.add(8);
        linkedHashSet.add(9);
        linkedHashSet.add(10);
        linkedHashSet.add(11);
        linkedHashSet.add(12);
        StringBuilder sb = new StringBuilder();

        for (int i : linkedHashSet) {
            sb.append(i);
        }

        assertEquals("123456789101112", sb.toString());
    }
}