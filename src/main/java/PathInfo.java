public class PathInfo {
    private Node node;          // The current node
    private double shortestPath; // The shortest path to this node
    private Node previousNode;  // The preceding node in the path

    public PathInfo(Node node, double shortestPath, Node previousNode) {
        this.node = node;
        this.shortestPath = shortestPath;
        this.previousNode = previousNode;
    }

    // Getters and setters

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public double getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(double shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }
}
