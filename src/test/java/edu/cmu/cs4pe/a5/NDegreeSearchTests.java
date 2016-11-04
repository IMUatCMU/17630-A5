package edu.cmu.cs4pe.a5;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class NDegreeSearchTests {

    private static ActorGraph graph;
    private NDegreeSearch twoDegree = new NDegreeSearch(2);

    /**
     * Setup the actor graphs
     *
     *   A     C
     *   --> 2 -> 4
     * 1      \D
     *  \B     \   F
     *   -> 3 -> 5 -> 6
     *         E
     */
    @BeforeClass
    public static void setup() {
        Actor a1 = new Actor("1");
        Actor a2 = new Actor("2");
        Actor a3 = new Actor("3");
        Actor a4 = new Actor("4");
        Actor a5 = new Actor("5");
        Actor a6 = new Actor("6");

        a1.addLink(new Movie("A"), a2);
        a1.addLink(new Movie("B"), a3);
        a2.addLink(new Movie("C"), a4);
        a2.addLink(new Movie("D"), a5);
        a3.addLink(new Movie("E"), a5);
        a5.addLink(new Movie("F"), a6);

        graph = new ActorGraph();
        graph.addActor(a1);
        graph.addActor(a2);
        graph.addActor(a3);
        graph.addActor(a4);
        graph.addActor(a5);
        graph.addActor(a6);
    }

    @Test
    public void testFind() {
        Actor a1 = graph.findActor("1");
        Actor a2 = graph.findActor("2");
        Actor a3 = graph.findActor("3");
        Actor a4 = graph.findActor("4");
        Actor a5 = graph.findActor("5");
        Actor a6 = graph.findActor("6");

        // a1 - a2
        assertPath(twoDegree.search(a1, a2), new String[]{"A"});

        // a1 - a3
        assertPath(twoDegree.search(a1, a3), new String[]{"B"});

        // a1 - a4
        assertPath(twoDegree.search(a1, a4), new String[]{"A", "C"});

        // a1 - a5
        assertPath(twoDegree.search(a1, a5), new String[]{"A", "D"});

        // a1 - a6
        assertPath(twoDegree.search(a1, a6), new String[]{"A", "D", "F"});

        // a2 - a3
        assertPath(twoDegree.search(a2, a3), new String[]{});

        // a2 - a4
        assertPath(twoDegree.search(a2, a4), new String[]{"C"});

        // a2 - a5
        assertPath(twoDegree.search(a2, a5), new String[]{"D"});

        // a2 - a6
        assertPath(twoDegree.search(a2, a6), new String[]{"D", "F"});

        // a3 - a4
        assertPath(twoDegree.search(a3, a4), new String[]{});

        // a3 - a5
        assertPath(twoDegree.search(a3, a5), new String[]{"E"});

        // a3 - a6
        assertPath(twoDegree.search(a3, a6), new String[]{"E", "F"});

        // a4 - a5
        assertPath(twoDegree.search(a4, a5), new String[]{});

        // a4 - a6
        assertPath(twoDegree.search(a4, a6), new String[]{});

        // a5 - a6
        assertPath(twoDegree.search(a5, a6), new String[]{"F"});
    }

    @Test
    public void testSearchFormatted() {
        Actor a1 = graph.findActor("1");
        Actor a2 = graph.findActor("2");
        Actor a3 = graph.findActor("3");
        Actor a4 = graph.findActor("4");
        Actor a5 = graph.findActor("5");
        Actor a6 = graph.findActor("6");

        twoDegree.searchFormatted(a1, a2);
        twoDegree.searchFormatted(a1, a4);
        twoDegree.searchFormatted(a1, a6);
        twoDegree.searchFormatted(a3, a4);
    }

    @SuppressWarnings("unchecked")
    private void assertPath(Stack result, String[] path) {
        assertEquals(path.length, result.getSize());
        for (int i = 0; i < path.length; i++) {
            Pair<Movie, Actor> edge = (Pair<Movie, Actor>) result.pop();
            assertEquals(path[i], edge.getFirst().getName());
        }
    }
}
