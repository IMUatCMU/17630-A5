package edu.cmu.cs4pe.a5;

public class NDegreeSearch {

    private final int maxDegree;
    private final Actor from;
    private final Actor to;

    public NDegreeSearch(int maxDegree, final Actor from, final Actor to) {
        this.maxDegree = maxDegree;
        this.from = from;
        this.to = to;
    }

    public void search() {
        if (from.getName().equals(to.getName()))
            throw new RuntimeException("same actors provided");

        Stack progress = new Stack();
        Stack history = new Stack();
        doSearch(progress, history, from, to, -1);
    }

    private void printPath(Stack progress) {
        Stack result = progress.reverseStack();

        String separation;
        if (result.getSize() == 0)
            separation = String.format("No linkage found within %d degree%s of separation", this.maxDegree, this.maxDegree > 1 ? "s" : "");
        else
            separation = String.format("%d degree%s of separation", result.getSize() - 1, result.getSize() - 1 > 1 ? "s" : "");

        String query = String.format("%s -> %s", from.getName(), to.getName());

        StringBuilder details = new StringBuilder();
        Actor lastActor = from;
        while(result.getSize() > 0) {
            String format = "\t %s: %s; %s\n";
            Movie edge = (Movie) result.pop();
            details.append(String.format(format, edge.getName(), lastActor.getName(), edge.getOtherActor(lastActor).getName()));
            lastActor = edge.getOtherActor(lastActor);
        }

        System.out.printf("%s: %s\n%s", query, separation, details.toString());
        System.out.print("\n------\n");
    }

    @SuppressWarnings("unchecked")
    private void doSearch(Stack progress, Stack history, Actor cursor, Actor target, int currentDegree) {
        if (currentDegree > this.maxDegree)
            return;
        else if (cursor.getName().equals(target.getName()))
            printPath(progress);

        history.push(cursor);
        cursor.getMovies().forEach(o -> {
            Movie edge = (Movie) o;
            Actor nextCursor = edge.getOtherActor(cursor);

            // we want to check if "edge" is pointing back again
            if (history.contains(nextCursor)) {
                return;
            }
            // do not allow the next edge be the same movie
            else if (progress.getSize() > 0) {
                if (((Movie) progress.peek()).getName().equals(edge.getName()))
                    return;
            }

            progress.push(edge);
            doSearch(progress, history, nextCursor, target, currentDegree + 1);
            progress.pop();
        });
        history.pop();
    }

    public int getMaxDegree() {
        return maxDegree;
    }

    private static class Found extends RuntimeException {}
}
