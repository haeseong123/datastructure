package structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.list.linked.single.SinglyLinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class SinglyLinkedListTest {
    private SinglyLinkedList<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new SinglyLinkedList<>();
    }

    @Test
    public void testAddFirst() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.addFirst(0);

        assertEquals(0, list.get(0));
        assertEquals(1, list.get(1));
        assertEquals(2, list.get(2));
    }

    @Test
    public void testAddLast() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.addLast(4);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
        assertEquals(4, list.get(3));
    }

    @Test
    public void testAddAtIndex() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1, 10);
        assertEquals(1, list.get(0));
        assertEquals(10, list.get(1));
        assertEquals(2, list.get(2));
        assertEquals(3, list.get(3));
    }

    @Test
    public void testRemoveByIndex() {
        list.add(1);
        list.add(2);
        list.add(3);
        int removed = list.remove(1);

        assertEquals(2, removed);
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    public void testRemoveByValue() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.remove(Integer.valueOf(2)));
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    public void testGet() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void testSet() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.set(1, 20);

        assertEquals(1, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void testContains() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.contains(2));
        assertFalse(list.contains(4));
    }

    @Test
    public void testIndexOf() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(1, list.indexOf(2));
        assertEquals(-1, list.indexOf(4));
    }

    @Test
    public void testSize() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(3, list.size());
        list.add(4);
        assertEquals(4, list.size());
    }

    @Test
    public void testIsEmpty() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testClear() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.clear();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    public void testIterator() {
        list.add(1);
        list.add(2);
        list.add(3);
        StringBuilder sb = new StringBuilder();
        for (Integer i : list) {
            sb.append(i);
        }

        assertEquals("123", sb.toString());
    }
}