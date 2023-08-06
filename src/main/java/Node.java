import java.util.ArrayList;

public class Node {

    private ArrayList<Edge> edges = new ArrayList<>();
    private double posx;
    private double posy;

    public Node(double posx, double posy){
        this.posx = posx;
        this.posy = posy;
    }

    public double getPosx() {
        return posx;
    }

    public double getPosy() {
        return posy;
    }

    public void addEdge(Edge edge){
        edges.add(edge);
    }
}
