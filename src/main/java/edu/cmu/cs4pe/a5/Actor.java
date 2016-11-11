package edu.cmu.cs4pe.a5;

/**
 * The Actor model. An actor has a name, and a list of movies
 * the actor has appeared in. This represents a node in an ActorGraph.
 */
public class Actor {

    /**
     * The name of the actor.
     */
    private final String name;

    /**
     * The movies this actor has appeared in.
     * The movies are stored in a linked list.
     */
    private LinkedList movies;

    /**
     * Constructor.
     *
     * @param name The name of the actor
     */
    public Actor(String name) {
        this.name = name;
        this.movies = new LinkedList();
    }

    /**
     * Adds a movie to the list of movies
     * this actor has appeared in.
     *
     * @param movie The movie this actor has appeared in.
     */
    public void addMovie(Movie movie) {
        this.movies.add(movie, this.movies.getSize());
    }

    /**
     * Gets the name of the actor.
     *
     * @return The actor's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the list of movies this actor
     * has appeared in.
     *
     * @return The list of movies this actor appeared in.
     */
    public LinkedList getMovies() {
        return movies;
    }
}
