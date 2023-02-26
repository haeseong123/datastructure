import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {

    @Test
    void get() {
        DynamicArray<String> array = new DynamicArray<>(3);
        array.add("banana");
        array.add("apple");
        assertEquals("apple", array.get(1));
    }

    @Test
    void add() {
        DynamicArray<String> array = new DynamicArray<>(2);
        assertTrue(array.add("banana"));
        assertTrue(array.add("apple"));
        assertTrue(array.add("orange"));
    }

    @Test
    void remove() {
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
    void removeAtIndex() {
        DynamicArray<String> array = new DynamicArray<>(2);
        array.add("banana");
        array.add("apple");
        array.add("orange");
        assertEquals("apple", array.remove(1));
        assertEquals(2, array.size());
        assertEquals("orange", array.get(1));

        Throwable lessException = assertThrows(IndexOutOfBoundsException.class, () -> {
            array.remove(-1);
        });
        assertEquals("index should be bigger than 0", lessException.getMessage());

        Throwable excessException = assertThrows(IndexOutOfBoundsException.class, () -> {
            array.remove(1000);
        });
        assertEquals("index should be within size", excessException.getMessage());
    }

    @Test
    void contains() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(1);
        array.add(2);
        array.add(3);
        assertTrue(array.contains(2));
        assertFalse(array.contains(10));
    }

    @Test
    void size() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(1);
        array.add(2);
        array.add(3);
        assertEquals(3, array.size());

        array.remove(1);
        assertEquals(2, array.size());

        array.clear();
        assertEquals(0, array.size());
    }

    @Test
    void clear() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(1);
        array.add(2);
        array.add(3);
        array.clear();
        assertEquals(0, array.size());
    }

    @Test
    void indexOf() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(1);
        array.add(2);
        array.add(3);
        assertEquals(0, array.indexOf(1));
        assertEquals(1, array.indexOf(2));
        assertEquals(2, array.indexOf(3));
        assertEquals(-1, array.indexOf(100));
    }

    @Test
    void iterator() {
        DynamicArray<Integer> array = new DynamicArray<>();
        array.add(1);
        array.add(2);
        array.add(3);
        StringBuilder sb = new StringBuilder();
        for (int i : array) {
            sb.append(i);
        }
        assertEquals("123", sb.toString());
    }
}