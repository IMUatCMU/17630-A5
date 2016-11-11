package edu.cmu.cs4pe.a5;

/**
 * The Movie model. A movie has a name, and has two actors
 * that appeared in this movie. This represents an edge in an ActorGraph.
 */
public class Movie {

    /**
     * The name of the movie.
     */
    private final String name;

    /**
     * One of the actors that appeared in the movie.
     */
    private final Actor actorOne;

    /**
     * Another actor that appeared in the movie.
     */
    private final Actor actorTwo;

    /**
     * Constructor. Creates a movie with a name
     * and two actors that appeared in this movie.
     *
     * @param name The name of the movie.
     * @param one An actor that appeared in the movie.
     * @param two Another actor that appeared in the movie.
     */
    public Movie(String name, Actor one, Actor two) {
        this.name = name;
        this.actorOne = one;
        this.actorTwo = two;

        this.actorOne.addMovie(this);
        this.actorTwo.addMovie(this);
    }

    /**
     * Gets the name of the movie.
     *
     * @return The movie's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get one actor that appeared in this movie.
     *
     * @return An actor that appeared in the movie.
     */
    public Actor getActorOne() {
        return actorOne;
    }

    /**
     * Get a second actor that appeared in this movie.
     *
     * @return Another actor that appeared in the movie.
     */
    public Actor getActorTwo() {
        return actorTwo;
    }

    /**
     * Given an actor, returns the other actor that appeared
     * in this movie.
     *
     * @param me An actor that appeared in this movie.
     * @return The other actor that appeared in this movie, or null if the given actor did not appear in this movie.
     */
    public Actor getOtherActor(Actor me) {
        if (me == actorOne) {
            return actorTwo;
        } else if (me == actorTwo) {
            return actorOne;
        } else {
            return null;
        }
    }

    /**
     * Compares two movies to see if they are equal.
     * Two movies are the same if they have the same
     * name and actors.
     *
     * @param obj A movie to compare with
     * @return true if the movies have the same name and actors.
     */
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
