import java.util.ArrayList;

public class Node {

    private ArrayList<Edge> edges = new ArrayList<>();
    private double posx;
    private double posy;
    private int id;

    private boolean visited = true;

    public Node(int id, double posx, double posy) {
        this.posx = posx;
        this.posy = posy;
        this.id = id;
    }

    public double getPosx() {
        return posx;
    }

    public double getPosy() {
        return posy;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public ArrayList<Edge> getEdges() {
        return this.edges;
    }

    public void visit() {
        this.visited = true;
    }

    public boolean isVisited() {
        return visited;
    }

    public int getId(){
        return id;
    }


}


