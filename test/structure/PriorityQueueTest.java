package structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.queue.priority.PriorityQueue;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PriorityQueueTest {

    private PriorityQueue<Integer> priorityQueue;

    @BeforeEach
    public void setUp() {
        priorityQueue = new PriorityQueue<>();
    }

    @Test
    public void testOffer() {
        assertTrue(priorityQueue.offer(1));
        assertTrue(priorityQueue.offer(2));
        assertTrue(priorityQueue.offer(3));
        assertEquals(3, priorityQueue.size());
    }

    @Test
    public void testPoll() {
        assertNull(priorityQueue.poll());

        priorityQueue.offer(3);
        priorityQueue.offer(1);
        priorityQueue.offer(2);

        assertEquals(1, priorityQueue.poll());
        assertEquals(2, priorityQueue.poll());
        assertEquals(3, priorityQueue.poll());
        assertNull(priorityQueue.poll());
    }

    @Test
    public void testPeek() {
        assertNull(priorityQueue.peek());

        priorityQueue.offer(3);
        priorityQueue.offer(1);
        priorityQueue.offer(2);

        assertEquals(1, priorityQueue.peek());
        assertEquals(1, priorityQueue.peek());
        assertEquals(1, priorityQueue.poll());
    }

    @Test
    public void testSize() {
        assertEquals(0, priorityQueue.size());

        priorityQueue.offer(3);
        priorityQueue.offer(1);
        priorityQueue.offer(2);

        assertEquals(3, priorityQueue.size());

        priorityQueue.poll();

        assertEquals(2, priorityQueue.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(priorityQueue.isEmpty());

        priorityQueue.offer(1);

        assertFalse(priorityQueue.isEmpty());

        priorityQueue.poll();

        assertTrue(priorityQueue.isEmpty());
    }

    @Test
    public void testContains() {
        assertFalse(priorityQueue.contains(1));

        priorityQueue.offer(3);
        priorityQueue.offer(1);
        priorityQueue.offer(2);

        assertTrue(priorityQueue.contains(1));
        assertTrue(priorityQueue.contains(2));
        assertTrue(priorityQueue.contains(3));
        assertFalse(priorityQueue.contains(77));
    }

    @Test
    public void testClear() {
        priorityQueue.offer(3);
        priorityQueue.offer(1);
        priorityQueue.offer(2);

        assertEquals(3, priorityQueue.size());

        priorityQueue.clear();

        assertEquals(0, priorityQueue.size());
        assertNull(priorityQueue.poll());
    }

    @Test
    public void testComparator() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        // 5 - 4 - 3 - 2 - 1
        pq.offer(5);
        pq.offer(3);
        pq.offer(2);
        pq.offer(1);
        pq.offer(4);

        assertEquals(5, pq.poll());
        assertEquals(4, pq.poll());
        assertEquals(3, pq.poll());
        assertEquals(2, pq.poll());
        assertEquals(1, pq.poll());
    }

    @Test
    public void testNullElement() {
        assertThrows(NullPointerException.class, () -> priorityQueue.offer(null));
    }
}