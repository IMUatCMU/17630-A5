package edu.cmu.cs4pe.a5;

/**
 * A stack ADT. It utilizes {@link LinkedList} to be its underlying data structure. This is fit as
 * the maximum number of elements to be put into stack is unknown or unpredictable to be within a
 * reasonable range.
 *
 * @since 2016.11.11
 *
 * @author Weinan Qiu
 */
public class Stack {

    private LinkedList list;

    public Stack() {
        this.list = new LinkedList();
    }

    /**
     * Return the top data and remove it from stack, returns NULL if there's nothing on top of the stack.
     *
     * @return Top data value on the stack, or NULL
     */
    public Object pop() {
        Object o = peek();
        this.list.remove(last());
        return o;
    }

    /**
     * Return the top data, but does not remove it from the stack. Returns NULL if there's nothing on top.
     *
     * @return Top data value on the stack, or NULL
     */
    public Object peek() {
        return this.list.get(last());
    }

    /**
     * Push new data onto the stack.
     *
     * @param item new data to be pushed onto stack.
     */
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

    /**
     * Convenience method to reverse the order of elements in the current stack. It does so by creating a new stack
     * and pop all the data from the current stack onto the new stack.
     *
     * @return a stack with elements from this stack in reversed order.
     */
    public Stack reverseStack() {
        Stack reverse = new Stack();
        this.list.forEachReversed(reverse::push);
        return reverse;
    }
}
