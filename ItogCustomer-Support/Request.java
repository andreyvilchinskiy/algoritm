public class Request implements Comparable<Request> {
    final int id;
    final String title;
    final int priority;

    Request(int id, String title, int priority) {
        this.id = id;
        this.title = title;
        this.priority = priority;
    }

    // Ключ кучи: (priority, id)
    @Override
    public int compareTo(Request o) {
        if (priority != o.priority) return Integer.compare(priority, o.priority);
        return Integer.compare(id, o.id);
    }

    @Override
    public String toString() { return id + "(" + priority + ")"; }
}