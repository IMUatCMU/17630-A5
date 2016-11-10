package edu.cmu.cs4pe.a5;

/**
 * The ActorGraph ADT. This data structure represents a graph,
 * where the nodes are Actors and the edges are Movies.
 */
public class ActorGraph {

    private LinkedList actors;

    public ActorGraph() {
        this.actors = new LinkedList();
    }

    public Actor findActor(String name) {
        Object found = this.actors.find(o -> {
            if (o == null || !(o instanceof Actor))
                return false;
            return ((Actor) o).getName().equals(name);
        });
        return found == null ? null : (Actor) found;
    }

    public void addActor(Actor actor) {
        this.actors.add(actor, this.actors.getSize());
    }
}
