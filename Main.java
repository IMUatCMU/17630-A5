public class Main {
	public static void main(String[] args) {
		// Create nodes
		ActorGraph graph = new ActorGraph();
		Movie abandon = new Movie("Abandon");
		Actor katie = new Actor("Katie Holmes");
		Actor benjamin = new Actor("Benjamin Bratt");

		// Link Actors
		katie.addMovie(abandon, benjamin);
		benjamin.addMovie(abandon, katie);

		// Add Actors to graph
		graph.addActor(katie);
		graph.addActor(benjamin);

		// Print out graph
		LinkedList<Actor> actors = graph.getActors();
		while (actors != null) {
			Actor currentActor = actors.getData();
			System.out.println(currentActor.getName());
			System.out.println();

			LinkedList<Pair<Actor, Movie>> movies = currentActor.getMovies();
			while (movies != null) {
				Pair<Actor, Movie> currentMovie = movies.getData();
				System.out.println(currentMovie.getSecond().getName());
				movies = movies.getNext();
			}

			actors = actors.getNext();
		}
	}
}
