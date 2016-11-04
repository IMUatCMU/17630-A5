package edu.cmu.cs4pe.a5;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ActorGraphTests {

    @Test
    public void testFindActor() {
        ActorGraph graph = new ActorGraph();
        graph.addActor(new Actor("A"));
        graph.addActor(new Actor("B"));
        graph.addActor(new Actor("C"));

        assertEquals("A", graph.findActor("A").getName());
        assertEquals("B", graph.findActor("B").getName());
        assertEquals("C", graph.findActor("C").getName());
        assertNull(graph.findActor("D"));
    }
}
