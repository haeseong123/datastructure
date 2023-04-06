package structure;

import org.junit.jupiter.api.Test;
import structure.list.array.DynamicArray;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {

    @Test
    void add() {
        DynamicArray<String> array = new DynamicArray<>(2);

        assertTrue(array.add("banana"));
        assertTrue(array.add("apple"));
        assertTrue(array.add("orange"));
        assertEquals("orange", array.get(2));
    }

    @Test
    void addWithIdx() {
        DynamicArray<String> array = new DynamicArray<>(2);
        array.add("banana");
        array.add("apple");
        array.add(1, "orange");

        assertEquals("orange", array.get(1));
        assertEquals("apple", array.get(2));
    }

    @Test
    void removeByIdx() {
        DynamicArray<String> array = new DynamicArray<>(2);
        array.add("banana");
        array.add("apple");
        array.add("orange");

        assertEquals("apple", array.remove(1));
        assertEquals(2, array.size());
        assertEquals("orange", array.remove(1));

        Throwable lessException = assertThrows(IndexOutOfBoundsException.class, () -> array.remove(-1));
        assertEquals("index should be bigger than 0", lessException.getMessage());
        Throwable excessException = assertThrows(IndexOutOfBoundsException.class, () -> array.remove(1000));
        assertEquals("index should be within size", excessException.getMessage());
    }

    @Test
    void removeByObj() {
        DynamicArray<String> array = new DynamicArray<>(2);
        array.add("banana");
        array.add("apple");
        array.add("orange");

        assertTrue(array.remove("apple"));
        assertFalse(array.remove("I'm not fruit"));
        assertEquals(2, array.size());
        assertEquals("orange", array.get(1));
    }

    @Test
    void get() {
        DynamicArray<String> array = new DynamicArray<>(3);
        array.add("banana");
        array.add("apple");

        assertEquals("banana", array.get(0));
        assertEquals("apple", array.get(1));
    }

    @Test
    void set() {
        DynamicArray<String> array = new DynamicArray<>(3);
        array.add("banana");
        array.add("apple");

        assertEquals("banana", array.get(0));
        array.set(0, "orange");
        assertEquals("orange", array.get(0));
    }

    @Test
    void contains() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(0);
        array.add(1);
        array.add(2);

        assertTrue(array.contains(2));
        assertFalse(array.contains(10000));
    }

    @Test
    void indexOf() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(0);
        array.add(1);
        array.add(2);

        assertEquals(0, array.indexOf(0));
        assertEquals(1, array.indexOf(1));
        assertEquals(2, array.indexOf(2));
        assertEquals(-1, array.indexOf(100));
    }

    @Test
    void size() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(0);
        array.add(1);
        array.add(2);

        assertEquals(3, array.size());
        array.remove(1);
        assertEquals(2, array.size());
    }

    @Test
    void isEmpty() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(0);
        array.add(1);
        array.add(2);

        assertFalse(array.isEmpty());
        array.clear();
        assertTrue(array.isEmpty());
    }

    @Test
    void clear() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(0);
        array.add(1);
        array.add(2);
        array.clear();

        assertEquals(0, array.size());
    }

    @Test
    void iterator() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(0);
        array.add(1);
        array.add(2);
        StringBuilder sb = new StringBuilder();
        for (int i : array) {
            sb.append(i);
        }

        assertEquals("012", sb.toString());
    }
}