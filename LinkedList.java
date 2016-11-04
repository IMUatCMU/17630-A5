public class LinkedList<T> {
	private T data;
	private LinkedList<T> next;

	public LinkedList(T data) {
		this.data = data;
	}

	public void append(T next) {
		LinkedList<T> newNode = new LinkedList<T>(next);
		LinkedList<T> currentNode = this;
		while (currentNode.getNext() != null) {
			currentNode = currentNode.getNext();
		}

		currentNode.setNext(newNode);
	}

	public T getData() {
		return this.data;
	}

	public LinkedList<T> getNext() {
		return this.next;
	}

	public void setNext(LinkedList<T> next) {
		this.next = next;
	}
}
