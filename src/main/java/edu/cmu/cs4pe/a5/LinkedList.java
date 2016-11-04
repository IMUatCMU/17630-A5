package edu.cmu.cs4pe.a5;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class LinkedList {

	private Element head;
	private Element tail;
	private int size;

	public LinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public void add(Object data, int pos) {
		if (pos < 0 || pos > this.size)
			throw new RuntimeException("index out of bounds");
		else if (data == null)
			throw new RuntimeException("no data provided");

		Element elem = new Element(data);

		if (this.size == 0) {
			this.head = elem;
			this.tail = elem;
		} else {
			if (pos == 0)
				this.head.setPrevious(elem);
			else {
				Element p = this.getElem(pos - 1);
				if (p != null) {
					elem.setNext(p.getNext());
					p.setNext(elem);
				}
			}

			if (!elem.hasPrevious())
				this.head = elem;
			if (!elem.hasNext())
				this.tail = elem;
		}

		this.size++;
	}

	public void remove(int pos) {
		if (pos < 0 || pos > this.size - 1)
			throw new RuntimeException("index out of bounds");

		if (pos == 0) {
			this.head = this.head.getNext();
			if (this.head != null)
				this.head.setPrevious(null);
		} else if (pos == this.size - 1) {
			this.tail = this.tail.getPrevious();
			if (this.tail != null)
				this.tail.setNext(null);
		} else {
			Element c = this.getElem(pos);
			c.getPrevious().setNext(c.getNext());
		}

		this.size--;
	}

	private Element getElem(int pos) {
		if (pos > this.size - 1)
			return null;

		Element cur = null;
		if (pos < size / 2) {
			cur = this.head;
			for (int i = 0; i < pos; i++)
				cur = cur.getNext();
		} else {
			cur = this.tail;
			for (int i = this.size - 1; i > pos; i--)
				cur = cur.getPrevious();
		}

		return cur;
	}

	public Object get(int pos) {
		Element elem = this.getElem(pos);
		return elem == null ? null : elem.getData();
	}

	public int getSize() {
		return size;
	}

	public Object find(Predicate<Object> predicate) {
		for (Element cur = this.head; cur != null; cur = cur.getNext()) {
			if (predicate.test(cur.getData())) {
				return cur.getData();
			}
		}
		return null;
	}

	public void forEach(Consumer<Object> consumer) {
		for (Element cur = this.head; cur != null; cur = cur.getNext()) {
			consumer.accept(cur.getData());
		}
	}

	/**
	 * Element in a linked list.
     */
	private static class Element {
		private final Object data;
		private Element previous;
		private Element next;

		public Element(Object data) {
			this.data = data;
		}

		public Object getData() {
			return data;
		}

		public Element getPrevious() {
			return previous;
		}

		public void setPrevious(Element previous) {
			if (this.hasPrevious()) {
				this.getPrevious().next = null;
			}
			this.previous = previous;
			if (this.hasPrevious() && this.getPrevious().getNext() != this) {
				this.getPrevious().setNext(this);
			}
		}

		public Element getNext() {
			return next;
		}

		public void setNext(Element next) {
			if (this.hasNext()) {
				this.getNext().previous = null;
			}
			this.next = next;
			if (this.hasNext() && this.getNext().getPrevious() != this) {
				this.getNext().setPrevious(this);
			}
		}

		public boolean hasNext() {
			return this.next != null;
		}

		public boolean hasPrevious() {
			return this.previous != null;
		}
	}
}
