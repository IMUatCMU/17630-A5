package edu.cmu.cs4pe.a5;

/**
 * This class provides the capability to search and print linkages of two actors within a certain number of degrees.
 *
 * @since 2016.11.11
 *
 * @author Weinan Qiu
 */
public class NDegreeSearch {

    // maximum degrees to search
    // although the assignment requires 6-degree search. We made this option configurable
    // so writing test cases before there is parsing capability is much easier. For instance,
    // test cases are written to test searching within 2 degrees. Hence, graph can be setup
    // manually.
    private final int maxDegree;

    // The source actor of the search
    private final Actor from;

    // The destination actor of the search
    private final Actor to;

    /**
     * Default constructor. Throws {@link RuntimeException} if source and destination actor are the same.
     * In that case, we refuse to do the search.
     *
     * @param maxDegree the limit of the degree of separation between the source and destination actors.
     * @param from source actor
     * @param to destination actor
     */
    public NDegreeSearch(int maxDegree, final Actor from, final Actor to) {
        this.maxDegree = maxDegree;
        this.from = from;
        this.to = to;
        if (this.from.getName().equals(this.to.getName()))
            throw new RuntimeException("same actors provided");
    }

    /**
     * Search all possible linkages within the {@link #maxDegree} between {@link #from} and {@link #to}. This is the
     * main entry point for this algorithm.
     */
    public void search() {
        // Prepare progress stack to keep track of the movies (in progress) that form the link
        Stack progress = new Stack();
        // Prepare history stack to keep track of the source actor (in progress) in each movie that is on the progress stack.
        // We keep track of this for two reasons: 1) we would like to avoid cyclic graph by preventing a proposed new movie
        // pointing to an actor that has already been part of the link. 2) we created the movie to be bidirectional (avoided
        // duplicating movie name storage), hence there needs to be something keeping track of which is source and
        // which is destination.
        Stack history = new Stack();
        // call internal search method
        doSearch(progress, history, from, to, -1);
    }

    /**
     * UI part of the algorithm. Takes a progress stack (which has been proven to link
     * source and destination) and prints it out in some format.
     *
     * @param progress progress stack used in searching links
     */
    private void printPath(Stack progress) {
        // Reverse the progress stack (not in place) first.
        // We need to reverse it since when pushing movies onto progress, the first
        // movie goes in first, but during printing, we need it out first.
        Stack result = progress.reverseStack();

        // First line printing how many degrees of separation or "no linkage found" message
        String separation;
        if (result.getSize() == 0)
            separation = String.format("No linkage found within %d degree%s of separation", this.maxDegree, this.maxDegree > 1 ? "s" : "");
        else
            separation = String.format("%d degree%s of separation", result.getSize() - 1, result.getSize() - 1 > 1 ? "s" : "");

        // Original query (i.e. Tom Hanks -> Tom Cruise)
        String query = String.format("%s -> %s", from.getName(), to.getName());

        // Main body of the linkages, carefully distinguish
        // between source actor and destination actor for each movie
        // that is popped out
        StringBuilder details = new StringBuilder();
        Actor lastActor = from;
        while(result.getSize() > 0) {
            String format = "\t %s: %s; %s\n";
            Movie edge = (Movie) result.pop();
            details.append(String.format(format, edge.getName(), lastActor.getName(), edge.getOtherActor(lastActor).getName()));
            lastActor = edge.getOtherActor(lastActor);
        }

        // Compose all three parts and print it out.
        System.out.printf("%s: %s\n%s", query, separation, details.toString());
        System.out.print("\n------\n");
    }

    /**
     * Internal method to perform the search.
     *
     * @param progress progress stack keeping track of all movies on the link
     * @param history history stack keeping track of all source actors on the link
     * @param cursor the current "actor to explore"
     * @param target the target actor for the search
     * @param currentDegree current degrees of separation
     */
    @SuppressWarnings("unchecked")
    private void doSearch(Stack progress, Stack history, Actor cursor, Actor target, int currentDegree) {
        // Terminate search if degrees of separation goes beyond limit
        if (currentDegree > this.maxDegree)
            return;
        // Print progress if we have a hit
        else if (cursor.getName().equals(target.getName()))
            printPath(progress);

        // Put the current "actor to explore" onto the history stack.
        // We are about to explore all his/her movies, so he/she will
        // be one to the source actors on the progress stack for now.
        history.push(cursor);
        // Explore all movies for the current actor
        cursor.getMovies().forEach(o -> {
            // Get the movie
            Movie edge = (Movie) o;
            // Get the destination actor of the movie (the one who isn't
            // the current "actor to explore")
            Actor nextCursor = edge.getOtherActor(cursor);

            // If the destination of this movie points back to one of the source
            // actors, we will have a cyclic graph. To avoid that, we jump to the
            // next iteration immediately. (Note we use "return" instead of "continue"
            // because we are in a lamda function scope)
            if (history.contains(nextCursor)) {
                return;
            }
            // Do not allow the same movie title appear twice consecutively.
            // For instance movie Foo has actor A, B, C. To route from A to B,
            // we want Foo: A->B directly instead of Foo: A->C followed by
            // Foo: C->B.
            else if (progress.getSize() > 0) {
                if (((Movie) progress.peek()).getName().equals(edge.getName()))
                    return;
            }

            // The movie passed the previous two screening, and shall be considered
            // as a viable "link to explore", hence let's push it onto the progress
            // stack
            progress.push(edge);
            // Recursive search on the destination actor of the current "actor to explore"
            // to be the next "actor to explore"
            doSearch(progress, history, nextCursor, target, currentDegree + 1);
            // Done with exploring the destination actor for this level, pop the link out.
            progress.pop();
        });
        // Pop the current "actor to explore" off the history stack. We have finished
        // exploring all his/her movies, and he/she is no longer part of the source
        // actors on the progress stack.
        history.pop();
    }

    public int getMaxDegree() {
        return maxDegree;
    }
}
