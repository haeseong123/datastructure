package structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.queue.array.ArrayQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrayQueueTest {
    private ArrayQueue<Integer> queue;

    @BeforeEach
    public void setUp() {
        queue = new ArrayQueue<>();
    }

    @Test
    public void testOfferAndPoll() {
        assertTrue(queue.offer(1));
        assertTrue(queue.offer(2));
        assertTrue(queue.offer(3));

        assertEquals(1, queue.poll());
        assertEquals(2, queue.poll());
        assertEquals(3, queue.poll());
        assertNull(queue.poll());
    }

    @Test
    public void testPeek() {
        assertNull(queue.peek());
        queue.offer(1);
        queue.offer(2);

        assertEquals(1, queue.peek());
    }

    @Test
    public void testSize() {
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        assertEquals(3, queue.size());
    }

    @Test
    public void testIsEmpty() {
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        assertFalse(queue.isEmpty());

        queue.poll();
        queue.poll();
        queue.poll();

        assertTrue(queue.isEmpty());
    }

    @Test
    public void testContains() {
        assertFalse(queue.contains(null));
        assertFalse(queue.contains(1));

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        assertTrue(queue.contains(1));
        assertTrue(queue.contains(2));
        assertTrue(queue.contains(3));
        assertFalse(queue.contains(4));
    }

    @Test
    public void testClear() {
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        assertEquals(3, queue.size());
        assertFalse(queue.isEmpty());

        queue.clear();

        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }
}