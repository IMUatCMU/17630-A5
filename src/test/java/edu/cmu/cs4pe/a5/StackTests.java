package edu.cmu.cs4pe.a5;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class StackTests {

    @Test
    public void testPush() {
        Stack stack = new Stack();
        stack.push("A");
        stack.push("B");
        stack.push("C");

        assertEquals(3, stack.getSize());
    }

    @Test
    public void testPeek() {
        Stack stack = new Stack();

        stack.push("A");
        assertEquals("A", stack.peek());

        stack.push("B");
        assertEquals("B", stack.peek());

        stack.push("C");
        assertEquals("C", stack.peek());
    }

    @Test
    public void testPop() {
        Stack stack = new Stack();
        stack.push("A");
        stack.push("B");
        stack.push("C");

        assertEquals("C", stack.pop());
        assertEquals("B", stack.pop());
        assertEquals("A", stack.pop());
    }
}
