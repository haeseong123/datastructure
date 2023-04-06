package structure;

import org.junit.jupiter.api.Test;
import structure.stack.MyStack;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    @Test
    void testPush() {
        MyStack<String> stack = new MyStack<>();
        stack.push("banana");
        stack.push("apple");

        assertEquals(2, stack.size());
    }

    @Test
    void testPop() {
        MyStack<String> stack = new MyStack<>();
        stack.push("banana");
        stack.push("apple");

        assertEquals("apple", stack.pop());
        assertEquals("banana", stack.pop());
    }

    @Test
    void testRemoveByObj() {
        MyStack<String> stack = new MyStack<>();
        stack.push("banana");
        stack.push("apple");

        assertTrue(stack.remove("banana"));
        assertFalse(stack.remove("apple apple"));
        assertEquals(1, stack.size());
    }

    @Test
    void testRemoveByIdx() {
        MyStack<String> stack = new MyStack<>();
        stack.push("banana");
        stack.push("apple");

        assertEquals("banana", stack.remove(0));
        assertEquals("apple", stack.remove(0));
        assertEquals(0, stack.size());
    }

    @Test
    void testPeek() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(0);
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.peek());
        assertEquals(2, stack.pop());

        assertEquals(1, stack.peek());
        assertEquals(1, stack.pop());

        assertEquals(0, stack.peek());
        assertEquals(0, stack.pop());
    }

    @Test
    void testContains() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(0);
        stack.push(1);
        stack.push(2);

        assertTrue(stack.contains(0));
        assertFalse(stack.contains(1000));
    }

    @Test
    void testIndexOf() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(0);
        stack.push(1);
        stack.push(2);

        assertEquals(0, stack.indexOf(0));
        assertEquals(1, stack.indexOf(1));
        assertEquals(2, stack.indexOf(2));
    }

    @Test
    void testEmpty() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(0);
        stack.push(1);
        stack.push(2);

        assertFalse(stack.empty());

        stack.pop();
        stack.pop();
        stack.pop();

        assertTrue(stack.empty());
    }
}