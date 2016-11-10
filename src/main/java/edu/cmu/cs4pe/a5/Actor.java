package edu.cmu.cs4pe.a5;

/**
 * The Actor model. An actor has a name, and a list of movies
 * the actor has appeared in. This represents a node in an ActorGraph.
 */
public class Actor {

    private final String name;
    private LinkedList movies;

    public Actor(String name) {
        this.name = name;
        this.movies = new LinkedList();
    }

    public void addMovie(Movie movie) {
        this.movies.add(movie, this.movies.getSize());
    }

    public String getName() {
        return name;
    }

    public LinkedList getMovies() {
        return movies;
    }
}
