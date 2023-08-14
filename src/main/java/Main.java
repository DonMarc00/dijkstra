import java.util.*;

public class Main {

    public static void main(String[] args) {
        Node startNode = new Node(1, 1);
        Node node2 = new Node(6, 4);
        Node node3 = new Node(2, 3);
        Node node4 = new Node(4, 4);
        Node node5 = new Node(8, 8);

        Edge edge12 = new Edge(startNode, node2);
        Edge edge13 = new Edge(startNode, node3);
        Edge edge24 = new Edge(node2, node4);
        Edge edge34 = new Edge(node3, node4);
        Edge edge45 = new Edge(node4, node5);

        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(startNode);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);
        nodes.add(node5);

        PriorityQueue<PathInfo> queue = new PriorityQueue<>(Comparator.comparingDouble(PathInfo::getShortestPath));
        Map<Node, PathInfo> pathInfoMap = new HashMap<>();

        // Initialize the starting node
        PathInfo startInfo = new PathInfo(startNode, 0, null);
        queue.add(startInfo);
        pathInfoMap.put(startNode, startInfo);

        // Initialize other nodes with infinite distance
        for (Node node : nodes) {
            if (node != startNode) {
                pathInfoMap.put(node, new PathInfo(node, Double.POSITIVE_INFINITY, null));
            }
        }

        // Process nodes from the priority queue
        while (!queue.isEmpty()) {
            PathInfo currentInfo = queue.poll();
            Node currentNode = currentInfo.getNode();
            double currentDistance = currentInfo.getShortestPath();

            // Process each edge connected to the current node
            for (Edge edge : currentNode.getEdges()) {
                Node neighbor = (edge.getNode2() == currentNode) ? edge.node1 : edge.getNode2();
                double newDistance = currentDistance + edge.getEdgeLegth();

                // Update the shortest path if a shorter one is found
                PathInfo neighborInfo = pathInfoMap.get(neighbor);
                if (newDistance < neighborInfo.getShortestPath()) {
                    neighborInfo.setShortestPath(newDistance);
                    neighborInfo.setPreviousNode(currentNode);
                    queue.add(neighborInfo); // Re-add to the queue to reprocess
                }
            }
        }
        for (Node node: pathInfoMap.keySet()
             ) {
            System.out.println(node + "  Shortest Path: " + pathInfoMap.get(node).getShortestPath() + " Previous Node: " + pathInfoMap.get(node).getPreviousNode());
        }

    }


}
