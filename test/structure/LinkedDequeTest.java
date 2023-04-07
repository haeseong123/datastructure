package structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.queue.linked.doubled.LinkedDeque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LinkedDequeTest {
    private LinkedDeque<Integer> linkedDeque;

    @BeforeEach
    public void setUp() {
        linkedDeque = new LinkedDeque<>();
    }

    @Test
    public void testOffer() {
        assertTrue(linkedDeque.offer(1));
        assertTrue(linkedDeque.offer(2));
        assertTrue(linkedDeque.offer(3));
        assertEquals(3, linkedDeque.size());
    }

    @Test
    public void testOfferLast() {
        assertTrue(linkedDeque.offerLast(1));
        assertTrue(linkedDeque.offerLast(2));
        assertTrue(linkedDeque.offerLast(3));
        assertEquals(3, linkedDeque.size());
    }

    @Test
    public void testOfferFirst() {
        assertTrue(linkedDeque.offerFirst(1));
        assertTrue(linkedDeque.offerFirst(2));
        assertTrue(linkedDeque.offerFirst(3));
        assertEquals(3, linkedDeque.size());
    }

    @Test
    public void testPoll() {
        linkedDeque.offer(1);
        linkedDeque.offer(2);
        linkedDeque.offer(3);

        assertEquals(Integer.valueOf(1), linkedDeque.poll());
        assertEquals(Integer.valueOf(2), linkedDeque.poll());
        assertEquals(Integer.valueOf(3), linkedDeque.poll());
        assertEquals(0, linkedDeque.size());
    }

    @Test
    public void testPollLast() {
        linkedDeque.offer(1);
        linkedDeque.offer(2);
        linkedDeque.offer(3);

        assertEquals(Integer.valueOf(3), linkedDeque.pollLast());
        assertEquals(Integer.valueOf(2), linkedDeque.pollLast());
        assertEquals(Integer.valueOf(1), linkedDeque.pollLast());
        assertEquals(0, linkedDeque.size());
    }

    @Test
    public void testPollFirst() {
        linkedDeque.offer(1);
        linkedDeque.offer(2);
        linkedDeque.offer(3);

        assertEquals(Integer.valueOf(1), linkedDeque.pollFirst());
        assertEquals(Integer.valueOf(2), linkedDeque.pollFirst());
        assertEquals(Integer.valueOf(3), linkedDeque.pollFirst());
        assertEquals(0, linkedDeque.size());
    }

    @Test
    public void testPeek() {
        assertNull(linkedDeque.peek());
        linkedDeque.offer(1);
        linkedDeque.offer(2);
        linkedDeque.offer(3);

        assertEquals(Integer.valueOf(1), linkedDeque.peek());
        assertEquals(Integer.valueOf(1), linkedDeque.peek());
        linkedDeque.poll();
        assertEquals(Integer.valueOf(2), linkedDeque.peek());
    }

    @Test
    public void testPeekLast() {
        assertNull(linkedDeque.peekLast());
        linkedDeque.offer(1);
        linkedDeque.offer(2);
        linkedDeque.offer(3);

        assertEquals(Integer.valueOf(3), linkedDeque.peekLast());
        assertEquals(Integer.valueOf(3), linkedDeque.peekLast());
        linkedDeque.pollLast();
        assertEquals(Integer.valueOf(2), linkedDeque.peekLast());
    }

    @Test
    public void testPeekFirst() {
        assertNull(linkedDeque.peekFirst());
        linkedDeque.offer(1);
        linkedDeque.offer(2);
        linkedDeque.offer(3);

        assertEquals(Integer.valueOf(1), linkedDeque.peekFirst());
        assertEquals(Integer.valueOf(1), linkedDeque.peekFirst());
        linkedDeque.pollFirst();
        assertEquals(Integer.valueOf(2), linkedDeque.peekFirst());
    }

    @Test
    public void testSize() {
        linkedDeque.offer(1);
        linkedDeque.offer(2);
        linkedDeque.offer(3);

        assertEquals(3, linkedDeque.size());
    }

    @Test
    public void testEmpty() {
        linkedDeque.offer(1);
        linkedDeque.offer(2);
        linkedDeque.offer(3);

        assertFalse(linkedDeque.isEmpty());
        assertEquals(1, linkedDeque.poll());
        assertEquals(2, linkedDeque.poll());
        assertEquals(3, linkedDeque.poll());
        assertTrue(linkedDeque.isEmpty());
    }

    @Test
    public void testContains() {
        assertFalse(linkedDeque.contains(1));
        linkedDeque.offer(1);
        linkedDeque.offer(2);
        linkedDeque.offer(3);

        assertTrue(linkedDeque.contains(1));
        assertTrue(linkedDeque.contains(2));
        assertTrue(linkedDeque.contains(3));
        assertTrue(linkedDeque.contains(3));
        assertFalse(linkedDeque.contains(77));

        assertEquals(1, linkedDeque.poll());
        assertEquals(2, linkedDeque.poll());
        assertEquals(3, linkedDeque.poll());

        assertFalse(linkedDeque.contains(1));
        assertFalse(linkedDeque.contains(2));
        assertFalse(linkedDeque.contains(3));
        assertFalse(linkedDeque.contains(77));
    }
}
