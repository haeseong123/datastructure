package structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.queue.array.ArrayDeque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrayDequeTest {

    private ArrayDeque<Integer> arrayDeque;

    @BeforeEach
    public void setUp() {
        arrayDeque = new ArrayDeque<>();
    }

    @Test
    public void testOffer() {
        assertTrue(arrayDeque.offer(1));
        assertEquals(1, arrayDeque.size());
        assertEquals(Integer.valueOf(1), arrayDeque.peek());
    }

    @Test
    public void testOfferLast() {
        assertTrue(arrayDeque.offer(1));
        assertTrue(arrayDeque.offerLast(2));
        assertEquals(2, arrayDeque.size());
        assertEquals(Integer.valueOf(2), arrayDeque.peekLast());
    }

    @Test
    public void testOfferFirst() {
        assertTrue(arrayDeque.offer(1));
        assertTrue(arrayDeque.offer(2));
        assertTrue(arrayDeque.offerFirst(0));
        assertEquals(3, arrayDeque.size());
        assertEquals(Integer.valueOf(0), arrayDeque.peekFirst());
    }

    @Test
    public void testPoll() {
        assertNull(arrayDeque.poll());
        arrayDeque.offer(1);
        arrayDeque.offer(2);
        assertEquals(Integer.valueOf(1), arrayDeque.poll());
        assertEquals(1, arrayDeque.size());
    }

    @Test
    public void testPollFirst() {
        assertNull(arrayDeque.pollFirst());
        arrayDeque.offer(1);
        arrayDeque.offer(2);
        assertEquals(Integer.valueOf(1), arrayDeque.pollFirst());
        assertEquals(1, arrayDeque.size());
    }

    @Test
    public void testPollLast() {
        assertNull(arrayDeque.pollLast());
        arrayDeque.offer(1);
        arrayDeque.offer(2);
        assertEquals(Integer.valueOf(2), arrayDeque.pollLast());
        assertEquals(1, arrayDeque.size());
    }

    @Test
    public void testPeek() {
        assertNull(arrayDeque.peek());
        arrayDeque.offer(1);
        arrayDeque.offer(2);
        assertEquals(Integer.valueOf(1), arrayDeque.peek());
    }

    @Test
    public void testPeekFirst() {
        assertNull(arrayDeque.peekFirst());
        arrayDeque.offer(1);
        arrayDeque.offer(2);
        assertEquals(Integer.valueOf(1), arrayDeque.peekFirst());
    }

    @Test
    public void testPeekLast() {
        assertNull(arrayDeque.peekLast());
        arrayDeque.offer(1);
        arrayDeque.offer(2);
        assertEquals(Integer.valueOf(2), arrayDeque.peekLast());
    }

    @Test
    public void testSize() {
        assertEquals(0, arrayDeque.size());
        arrayDeque.offer(1);
        arrayDeque.offer(2);
        assertEquals(2, arrayDeque.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(arrayDeque.isEmpty());
        arrayDeque.offer(1);
        assertFalse(arrayDeque.isEmpty());
    }

    @Test
    public void testContains() {
        assertFalse(arrayDeque.contains(1));
        arrayDeque.offer(1);
        assertTrue(arrayDeque.contains(1));
        assertFalse(arrayDeque.contains(2));
    }

    @Test
    public void testClear() {
        arrayDeque.offer(1);
        arrayDeque.offer(2);
        arrayDeque.clear();
        assertEquals(0, arrayDeque.size());
        assertNull(arrayDeque.peek());
    }
}
