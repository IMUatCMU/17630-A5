package edu.cmu.cs4pe.a5;

/**
 * The ActorGraph ADT. This data structure represents a graph,
 * where the nodes are Actors and the edges are Movies.
 */
public class ActorGraph {

    /**
     * A list of the actors that are in the graph.
     */
    private LinkedList actors;

    /**
     * Constructor. Creates a graph
     * with no actors.
     */
    public ActorGraph() {
        this.actors = new LinkedList();
    }

    /**
     * Searches the graph for an actor with a specific name.
     * The search has a complexity of O(n).
     *
     * @param name The name of the actor to find.
     * @return The Actor, or null if not found.
     */
    public Actor findActor(String name) {
        Object found = this.actors.find(o -> {
            if (o == null || !(o instanceof Actor))
                return false;
            return ((Actor) o).getName().equals(name);
        });
        return found == null ? null : (Actor) found;
    }

    /**
     * Adds an actor to the graph.
     *
     * @param actor The actor to add to the graph.
     */
    public void addActor(Actor actor) {
        this.actors.add(actor, this.actors.getSize());
    }
}
