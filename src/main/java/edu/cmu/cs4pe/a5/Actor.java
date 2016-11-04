package edu.cmu.cs4pe.a5;

public class Actor {

    private final String name;
    private LinkedList linkages;

    public Actor(String name) {
        this.name = name;
        this.linkages = new LinkedList();
    }

    public void addLink(Movie movie, Actor anotherActor) {
        this.linkages.add(new Pair<>(movie, anotherActor), this.linkages.getSize());
    }

    public String getName() {
        return name;
    }

    public LinkedList getLinkages() {
        return linkages;
    }
}
