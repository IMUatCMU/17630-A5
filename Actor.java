public class Actor {

	private String name;

	public String getName() {
		return this.name;
	}

	public Actor(String name) {
		this.name = name;
	}

	private LinkedList<Pair<Actor, Movie>> movies;

	public void addMovie(Movie movie, Actor otherActor) {
		Pair<Actor, Movie> moviePair = new Pair<Actor, Movie>(otherActor, movie);
		if (this.movies == null) {
			LinkedList<Pair<Actor, Movie>> newNode = new LinkedList<Pair<Actor, Movie>>(moviePair);
			this.movies = newNode;
		} else {
			this.movies.append(moviePair);
		}
	}

	public LinkedList<Pair<Actor, Movie>> getMovies() {
		return this.movies;
	}
}
