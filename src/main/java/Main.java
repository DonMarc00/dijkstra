public class Main {

    public static void main(String[] args) {
        Node node1 = new Node(1,2);
        Node node2 = new Node(5,7);
        Edge edge = new Edge(node1, node2);
        System.out.println(edge.calcEdgeLength());
    }
}
