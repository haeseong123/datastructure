package structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.queue.linked.single.LinkedQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LinkedQueueTest {

    private LinkedQueue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new LinkedQueue<>();
    }

    @Test
    void testOffer() {
        assertTrue(queue.offer(1));
        assertTrue(queue.offer(2));
        assertTrue(queue.offer(3));
        assertEquals(3, queue.size());
        assertFalse(queue.isEmpty());
    }

    @Test
    void testPoll() {
        assertNull(queue.poll());
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        assertEquals(1, queue.poll());
        assertEquals(2, queue.poll());
        assertEquals(3, queue.poll());
        assertNull(queue.poll());
        assertTrue(queue.isEmpty());
    }

    @Test
    void testPeek() {
        assertNull(queue.peek());
        queue.offer(1);
        queue.offer(2);

        assertEquals(1, queue.peek());
        assertEquals(1, queue.peek()); // peek는 요소를 삭제하지 않으므로 여러번 호출해도 동일한 결과
        queue.poll();
        assertEquals(2, queue.peek());
    }

    @Test
    void testSize() {
        assertEquals(0, queue.size());
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        assertEquals(3, queue.size());
        queue.poll();
        assertEquals(2, queue.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(queue.isEmpty());
        queue.offer(1);
        assertFalse(queue.isEmpty());
        queue.poll();
        assertTrue(queue.isEmpty());
    }

    @Test
    void testContains() {
        assertFalse(queue.contains(1));
        assertFalse(queue.contains(null));
        queue.offer(1);

        assertTrue(queue.contains(1));
        assertFalse(queue.contains(2));
        assertFalse(queue.contains(null));
        queue.offer(null);
        assertTrue(queue.contains(null));
    }

    @Test
    void testClear() {
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        assertFalse(queue.isEmpty());

        queue.clear();
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }
}
