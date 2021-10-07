import java.awt.*;

public class Edge<Label> {
    public int source;
    public int destination;
    public Label label;

    public Edge(int from, int to, Label label) {
        this.source = from;
        this.destination = to;
        this.label = label;
    }
}

