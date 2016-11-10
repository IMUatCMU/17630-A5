package edu.cmu.cs4pe.a5;

/**
 * A stack ADT. This stack allows you to push and pop objects
 * in a FIFO manner.
 */
public class Stack {

    private LinkedList list;

    public Stack() {
        this.list = new LinkedList();
    }

    public Object pop() {
        Object o = peek();
        this.list.remove(last());
        return o;
    }

    public Object peek() {
        return this.list.get(last());
    }

    public void push(Object item) {
        this.list.add(item, last() + 1);
    }

    public int getSize() {
        return this.list.getSize();
    }

    public boolean contains(Object obj) {
        return this.list.find(o -> o == obj) != null;
    }

    private int last() {
        return this.list.getSize() - 1;
    }

    public Stack reverseStack() {
        Stack reverse = new Stack();
        this.list.forEachReversed(reverse::push);
        return reverse;
    }
}
