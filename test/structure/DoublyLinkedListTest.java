package structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.list.linked.doubled.DoublyLinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoublyLinkedListTest {
    private DoublyLinkedList<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new DoublyLinkedList<>();
    }

    @Test
    public void testAddFirst() {
        list.addFirst(1);
        list.addFirst(2);

        assertEquals(2, list.get(0));
        assertEquals(1, list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    public void testAddLast() {
        list.addLast(1);
        list.addLast(2);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    public void testAdd() {
        list.add(0, 1);
        list.add(1, 2);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    public void testRemove() {
        list.add(0, 1);
        list.add(1, 2);

        assertEquals(2, list.remove(1));
        assertEquals(1, list.size());
        assertEquals(1, list.get(0));
    }

    @Test
    public void testRemoveObject() {
        list.add(0, 1);
        list.add(1, 2);

        assertTrue(list.remove(Integer.valueOf(2)));
        assertFalse(list.remove(Integer.valueOf(3)));
        assertEquals(1, list.size());
        assertEquals(1, list.get(0));
    }

    @Test
    public void testGet() {
        list.add(0, 1);
        list.add(1, 2);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    public void testSet() {
        list.add(0, 1);
        list.add(1, 2);
        list.set(1, 3);

        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
    }

    @Test
    public void testContains() {
        list.add(0, 1);
        list.add(1, 2);

        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertFalse(list.contains(3));
    }

    @Test
    public void testIndexOf() {
        list.add(0, 1);
        list.add(1, 2);

        assertEquals(0, list.indexOf(1));
        assertEquals(1, list.indexOf(2));
        assertEquals(-1, list.indexOf(3));
    }

    @Test
    public void testSize() {
        list.add(0, 1);
        list.add(1, 2);

        assertEquals(2, list.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add(0, 1);

        assertFalse(list.isEmpty());
    }

    @Test
    public void testClear() {
        list.add(0, 1);
        list.add(1, 2);
        list.clear();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testIterator() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        StringBuilder sb = new StringBuilder();
        for (int i : list) {
            sb.append(i);
        }

        assertEquals("1234", sb.toString());
    }
}
