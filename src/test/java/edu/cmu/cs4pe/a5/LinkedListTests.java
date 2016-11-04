package edu.cmu.cs4pe.a5;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class LinkedListTests {

    @Test
    public void testAddInFront() {
        LinkedList list = new LinkedList();
        list.add("a", 0);
        list.add("b", 0);
        list.add("c", 0);
        list.add("d", 0);

        assertEquals(4, list.getSize());
        assertEquals("d", list.get(0));
        assertEquals("c", list.get(1));
        assertEquals("b", list.get(2));
        assertEquals("a", list.get(3));
    }

    @Test
    public void testAddInTail() {
        LinkedList list = new LinkedList();
        list.add("a", 0);
        list.add("b", 1);
        list.add("c", 2);
        list.add("d", 3);

        assertEquals(4, list.getSize());
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
        assertEquals("d", list.get(3));
    }

    @Test
    public void testRemoveInFront() {
        LinkedList list = new LinkedList();
        list.add("a", 0);
        list.add("b", 0);
        list.add("c", 0);
        list.add("d", 0);

        list.remove(0);
        assertEquals(3, list.getSize());
        assertEquals("c", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("a", list.get(2));

        list.remove(0);
        assertEquals(2, list.getSize());
        assertEquals("b", list.get(0));
        assertEquals("a", list.get(1));

        list.remove(0);
        assertEquals(1, list.getSize());
        assertEquals("a", list.get(0));

        list.remove(0);
        assertEquals(0, list.getSize());
    }

    @Test
    public void testRemoveInTail() {
        LinkedList list = new LinkedList();
        list.add("a", 0);
        list.add("b", 1);
        list.add("c", 2);
        list.add("d", 3);

        list.remove(3);
        assertEquals(3, list.getSize());
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));

        list.remove(2);
        assertEquals(2, list.getSize());
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));

        list.remove(1);
        assertEquals(1, list.getSize());
        assertEquals("a", list.get(0));

        list.remove(0);
        assertEquals(0, list.getSize());
    }

    @Test
    public void testFind() {
        LinkedList list = new LinkedList();
        list.add("a", 0);
        list.add("b", 1);
        list.add("c", 2);
        list.add("d", 3);

        assertEquals("a", list.find("a"::equals));
        assertEquals("b", list.find("b"::equals));
        assertEquals("c", list.find("c"::equals));
        assertEquals("d", list.find("d"::equals));
    }

    @Test
    public void testForEach() {
        LinkedList list = new LinkedList();
        list.add("a", 0);
        list.add("b", 1);
        list.add("c", 2);
        list.add("d", 3);

        StringBuilder sb = new StringBuilder();
        list.forEach(o -> sb.append(o.toString()));
        assertEquals("abcd", sb.toString());
    }
}
