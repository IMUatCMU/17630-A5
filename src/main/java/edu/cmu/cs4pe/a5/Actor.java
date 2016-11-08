package edu.cmu.cs4pe.a5;

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
