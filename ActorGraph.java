public class ActorGraph {
	private LinkedList<Actor> actors;

	public Actor findActor(String name) {

		LinkedList<Actor> currentNode = this.actors;
		while (currentNode != null) {
			if (currentNode.getNext().equals(name)) {
				return currentNode.getData();
			}
			currentNode = currentNode.getNext();
		}

		return null;
	}

	public LinkedList<Actor> getActors() {
		return this.actors;
	}

	public void addActor(Actor actor) {
		LinkedList<Actor> newNode = new LinkedList<Actor>(actor);
		if (this.actors == null) {
			this.actors = newNode;
		} else {
			this.actors.append(actor);
		}
	}
}
