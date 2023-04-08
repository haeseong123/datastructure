package structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.set.hash.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class HashSetTest {
    private HashSet<Integer> hashSet;

    @BeforeEach
    public void setUp() {
        hashSet = new HashSet<>();
    }

    @Test
    public void testAdd() {
        assertTrue(hashSet.add(1));
        assertTrue(hashSet.add(2));
        assertFalse(hashSet.add(1)); // 중복된 원소는 추가되지 않아야 함
        assertEquals(2, hashSet.size());
    }

    @Test
    public void testRemove() {
        hashSet.add(1);
        hashSet.add(2);
        assertTrue(hashSet.remove(1));
        assertFalse(hashSet.remove(3)); // 존재하지 않는 원소는 삭제되지 않아야 함
        assertEquals(1, hashSet.size());
    }

    @Test
    public void testContains() {
        hashSet.add(1);
        hashSet.add(2);
        assertTrue(hashSet.contains(1));
        assertTrue(hashSet.contains(2));
        assertFalse(hashSet.contains(3));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(hashSet.isEmpty());

        hashSet.add(1);
        assertFalse(hashSet.isEmpty());

        hashSet.remove(1);
        assertTrue(hashSet.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, hashSet.size());

        hashSet.add(1);
        hashSet.add(2);
        assertEquals(2, hashSet.size());

        hashSet.remove(1);
        assertEquals(1, hashSet.size());
    }

    @Test
    public void testClear() {
        hashSet.add(1);
        hashSet.add(2);
        assertFalse(hashSet.isEmpty());

        hashSet.clear();
        assertTrue(hashSet.isEmpty());
        assertEquals(0, hashSet.size());
    }
}