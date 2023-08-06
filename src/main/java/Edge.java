public class Edge {

    Node node1;
    Node node2;

    double edgelegth;

    public Edge(Node n1, Node n2){
        this.node1 = n1;
        this.node2 = n2;
        addEdge(node1, node2);
    }

    public double calcEdgeLength(){
        //Calc differences of coordinates --> Length on the corresponding axis
        double x = Math.abs(node1.getPosx() - node2.getPosx());
        double y = Math.abs(node1.getPosy() - node2.getPosy());
        //Pythagoras
        return Math.sqrt(x*x + y*y);
    }

    public double getEdgeLegth() {
        return edgelegth;
    }

    public void addEdge(Node node1, Node node2){
        node1.addEdge(this);
        node2.addEdge(this);
    }
}
