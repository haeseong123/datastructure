package structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.heap.Heap;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HeapTest {
    private Heap<Integer> heap;

    @BeforeEach
    void setUp() {
        heap = new Heap<>();
    }

    @Test
    void testAdd() {
        // 1 - 2 - 3 - 4 - 5
        assertTrue(heap.add(3));
        assertTrue(heap.add(4));
        assertTrue(heap.add(1));
        assertTrue(heap.add(5));
        assertTrue(heap.add(2));

        assertEquals(5, heap.size());
        assertEquals(1, heap.peek());
    }

    @Test
    void testOffer() {
        assertTrue(heap.offer(3));
        assertTrue(heap.offer(4));
        assertTrue(heap.offer(1));
        assertTrue(heap.offer(5));
        assertTrue(heap.offer(2));

        assertEquals(5, heap.size());
        assertEquals(1, heap.peek());
    }

    @Test
    void testRemove() {
        assertTrue(heap.offer(3));
        assertTrue(heap.offer(4));
        assertTrue(heap.offer(1));
        assertTrue(heap.offer(5));
        assertTrue(heap.offer(2));

        assertEquals(1, heap.remove());
        assertEquals(2, heap.remove());
        assertEquals(3, heap.remove());
        assertEquals(4, heap.remove());
        assertEquals(5, heap.remove());

        assertEquals(0, heap.size());
        assertNull(heap.peek());
    }

    @Test
    void testSize() {
        assertEquals(0, heap.size());

        heap.offer(1);
        heap.offer(2);
        heap.offer(3);

        assertEquals(3, heap.size());
    }

    @Test
    void testPeek() {
        assertNull(heap.peek());

        assertTrue(heap.offer(2));
        assertTrue(heap.offer(1));
        assertTrue(heap.offer(3));

        assertEquals(1, heap.peek());
    }

    @Test
    void testIsEmpty() {
        assertTrue(heap.isEmpty());

        heap.offer(1);

        assertFalse(heap.isEmpty());
    }

    @Test
    void testCustomComparator() {
        // 사용자 정의 Comparator를 사용한 Heap 테스트
        Heap<Integer> customHeap = new Heap<>(Comparator.reverseOrder());
        customHeap.offer(1);
        customHeap.offer(2);
        customHeap.offer(3);
        customHeap.offer(4);
        customHeap.offer(5);

        assertEquals(5, customHeap.remove());
        assertEquals(4, customHeap.remove());
        assertEquals(3, customHeap.remove());
        assertEquals(2, customHeap.remove());
        assertEquals(1, customHeap.remove());
    }
}
