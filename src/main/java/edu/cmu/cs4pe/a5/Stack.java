package edu.cmu.cs4pe.a5;

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

    private int last() {
        return this.list.getSize() - 1;
    }
}
