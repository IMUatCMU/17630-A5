package edu.cmu.cs4pe.a5;

/**
 * The Movie model. A movie has a name, and has two actors
 * that appeared in this movie. This represents an edge in an ActorGraph.
 */
public class Movie {

    private final String name;
    private final Actor actorOne;
    private final Actor actorTwo;

    public Movie(String name, Actor one, Actor two) {
        this.name = name;
        this.actorOne = one;
        this.actorTwo = two;

        this.actorOne.addMovie(this);
        this.actorTwo.addMovie(this);
    }

    public String getName() {
        return name;
    }

    public Actor getActorOne() {
        return actorOne;
    }

    public Actor getActorTwo() {
        return actorTwo;
    }

    public Actor getOtherActor(Actor me) {
        if (me == actorOne) {
            return actorTwo;
        } else if (me == actorTwo) {
            return actorOne;
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        else if (this == obj)
            return true;
        else if (!(obj instanceof Movie))
            return false;
        else {
            Movie that = (Movie) obj;
            return this.name.equals(that.name) &&
                    (
                            (this.actorOne == that.actorOne && this.actorTwo == that.actorTwo) ||
                            (this.actorOne == that.actorTwo && this.actorTwo == that.actorOne)
                    );
        }
    }
}
