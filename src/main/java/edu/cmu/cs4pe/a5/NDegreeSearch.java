package edu.cmu.cs4pe.a5;

public class NDegreeSearch {

    private final int maxDegree;

    public NDegreeSearch(int maxDegree) {
        this.maxDegree = maxDegree;
    }

    public Stack search(Actor one, Actor two) {
        if (one.getName().equals(two.getName()))
            throw new RuntimeException("same actors provided");

        Stack progress = new Stack();
        doSearch(progress, one, two, -1);

        return reverse(progress);
    }

    @SuppressWarnings("unchecked")
    public void searchFormatted(Actor one, Actor two) {
        Stack result = search(one, two);

        String separation;
        if (result.getSize() == 0)
            separation = String.format("No linkage found within %d degree%s of separation", this.maxDegree, this.maxDegree > 1 ? "s" : "");
        else
            separation = String.format("%d degree%s of separation", result.getSize() - 1, result.getSize() - 1 > 1 ? "s" : "");

        String query = String.format("%s -> %s", one.getName(), two.getName());

        StringBuilder details = new StringBuilder();
        String lastName = one.getName();
        while(result.getSize() > 0) {
            String format = "\t %s: %s; %s\n";
            Pair<Movie, Actor> edge = (Pair<Movie, Actor>) result.pop();
            details.append(String.format(format, edge.getFirst().getName(), lastName, edge.getSecond().getName()));
            lastName = edge.getSecond().getName();
        }

        System.out.printf("%s: %s\n%s", query, separation, details.toString());
    }

    @SuppressWarnings("unchecked")
    private boolean doSearch(Stack progress, Actor cursor, Actor target, int currentDegree) {
        if (currentDegree > this.maxDegree)
            return false;
        else if (cursor.getName().equals(target.getName()))
            return true;

        try {
            cursor.getLinkages().forEach(o -> {
                Pair<Movie, Actor> edge = (Pair<Movie, Actor>) o;
                progress.push(edge);
                boolean found = doSearch(progress, edge.getSecond(), target, currentDegree + 1);
                if (found)
                    throw new Found();
                progress.pop();
            });
        } catch (Found f) {
            return true;
        }

        return false;
    }

    private Stack reverse(Stack s) {
        Stack s2 = new Stack();
        while (s.getSize() != 0)
            s2.push(s.pop());
        return s2;
    }

    public int getMaxDegree() {
        return maxDegree;
    }

    private static class Found extends RuntimeException {}
}
