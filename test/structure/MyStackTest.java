package structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.stack.MyStack;

import static org.junit.jupiter.api.Assertions.*;

public class MyStackTest {

    private MyStack<Integer> myStack;

    @BeforeEach
    void setUp() {
        myStack = new MyStack<>();
    }

    @Test
    void testPushAndPop() {
        assertEquals(1, myStack.push(1));
        assertEquals(2, myStack.push(2));
        assertEquals(3, myStack.push(3));
        assertEquals(3, myStack.pop());
        assertEquals(2, myStack.pop());
        assertEquals(1, myStack.pop());
    }

    @Test
    void testPeek() {
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);

        assertEquals(3, myStack.peek());
        assertEquals(3, myStack.size());
    }

    @Test
    void testSearch() {
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);

        assertEquals(3, myStack.search(1));
        assertEquals(-1, myStack.search(4));
    }

    @Test
    void testSize() {
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);

        assertEquals(3, myStack.size());
        myStack.pop();
        myStack.pop();
        assertEquals(1, myStack.size());
    }

    @Test
    void testClear() {
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.clear();

        assertEquals(0, myStack.size());
        assertTrue(myStack.empty());
    }

    @Test
    void testRemove() {
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);


        // remove using index
        assertEquals(3, myStack.remove(2));

        // remove using Obj
        assertTrue(myStack.remove(Integer.valueOf(1)));
        assertEquals(1, myStack.size());
    }

    @Test
    void testContains() {
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);

        assertTrue(myStack.contains(2));
        assertFalse(myStack.contains(4));
    }

    @Test
    void testIndexOf() {
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);

        assertEquals(1, myStack.indexOf(2));
        assertEquals(-1, myStack.indexOf(4));
    }
}