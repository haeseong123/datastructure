package structure;

import org.junit.jupiter.api.Test;
import structure.list.array.StaticArray;

import static org.junit.jupiter.api.Assertions.*;

class StaticArrayTest {

    @Test
    void testAdd() {
        StaticArray<Integer> array = new StaticArray<>(2);
        assertTrue(array.add(1));
        assertTrue(array.add(2));
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            array.add(3);
        });

        assertEquals("Index should be within size", exception.getMessage());
    }

    @Test
    void testRemove() {
        StaticArray<String> array = new StaticArray<>(2);
        array.add("apple");
        array.add("banana");
        assertTrue(array.remove("banana"));
        assertFalse(array.remove("orange"));
        assertEquals(1, array.size());
        assertEquals("apple", array.get(0));
    }

    @Test
    void testRemoveAtIndex() {
        StaticArray<String> array = new StaticArray<>(2);
        array.add("apple");
        array.add("banana");
        assertEquals("banana", array.remove(1));
        assertEquals("apple", array.get(0));
        assertEquals(1, array.size());

        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            array.remove(10);
        });
        assertEquals("Index should be within size", exception.getMessage());
    }

    @Test
    void testSize() {
        StaticArray<Double> array = new StaticArray<>(4);
        array.add(1.1);
        array.add(2.2);
        array.add(3.3);
        assertEquals(3, array.size());
        array.remove(1);
        assertEquals(2, array.size());
    }

    @Test
    void testContains() {
        StaticArray<String> array = new StaticArray<>(3);
        array.add("apple");
        array.add("banana");
        assertTrue(array.contains("apple"));
        assertFalse(array.contains("orange"));
    }

    @Test
    void testIndexOf() {
        StaticArray<String> array = new StaticArray<>(3);
        array.add("apple");
        array.add("banana");
        assertEquals(1, array.indexOf("banana"));
        assertEquals(-1, array.indexOf("orange"));
    }

    @Test
    void testGet() {
        StaticArray<String> array = new StaticArray<>(3);
        array.add("apple");
        array.add("banana");
        assertEquals("apple", array.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            array.get(2);
        });
    }

    @Test
    void testClear() {
        StaticArray<Integer> array = new StaticArray<>(2);
        array.add(1);
        array.add(2);
        array.clear();
        assertEquals(0, array.size());
    }

    @Test
    void testIterator() {
        StaticArray<Integer> array = new StaticArray<>(2);
        array.add(1);
        array.add(2);
        StringBuilder sb = new StringBuilder();
        for (int i : array) {
            sb.append(i);
        }
        assertEquals("12", sb.toString());
    }
}