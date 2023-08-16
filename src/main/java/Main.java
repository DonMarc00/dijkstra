import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "src/main/java/nodeCoordinates";
        String edgePath = "src/main/java/edgeNodes";
        int [][] coordinates = readCoordinates(filePath);
        int [][] edgeNodes = readCoordinates(edgePath);

        ArrayList<Node> nodes = new ArrayList<Node>();
        int index = 0;
        for (int[] coord:coordinates) {
            nodes.add(new Node(index, coord[0],coord[1]));
            index++;
        }
        ArrayList<Edge> edges = new ArrayList<>();
        for (int[] edge:edgeNodes) {
            //-1 to use natural counting in configs
            edges.add(new Edge(nodes.get(edge[0]-1), nodes.get(edge[1]-1)));
        }

        GraphVisualizer frame = new GraphVisualizer(nodes, edges);

        PriorityQueue<PathInfo> queue = new PriorityQueue<>(Comparator.comparingDouble(PathInfo::getShortestPath));
        Map<Node, PathInfo> pathInfoMap = new HashMap<>();

        // Initialize the starting node
        PathInfo startInfo = new PathInfo(nodes.get(0), 0, null);
        queue.add(startInfo);
        pathInfoMap.put(nodes.get(0), startInfo);

        // Initialize other nodes with infinite distance
        for (Node node : nodes) {
            if (node != nodes.get(0)) {
                pathInfoMap.put(node, new PathInfo(node, Double.POSITIVE_INFINITY, null));
            }
        }

        Object[][] data = TableDataUtility.prepareDataForTable(pathInfoMap);
        String[] columnNames = {"Node", "Shortest Path", "Previous Node"};
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 150));  // Adjust dimensions as needed
        frame.add(scrollPane, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);


        // Process nodes from the priority queue
        while (!queue.isEmpty()) {
            PathInfo currentInfo = queue.poll();
            Node currentNode = currentInfo.getNode();
            double currentDistance = currentInfo.getShortestPath();

            // Process each edge connected to the current node
            for (Edge edge : currentNode.getEdges()) {
                edge.setCurrent(true);  // Set the edge as current
                frame.repaint();  // Refresh the visualization
                try {
                    Thread.sleep(500);  // Pause for half a second for visualization
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Node neighbor = (edge.getNode2() == currentNode) ? edge.node1 : edge.getNode2();
                double newDistance = currentDistance + edge.getEdgeLegth();

                // Update the shortest path if a shorter one is found
                PathInfo neighborInfo = pathInfoMap.get(neighbor);
                if (newDistance < neighborInfo.getShortestPath()) {
                    neighborInfo.setShortestPath(newDistance);
                    neighborInfo.setPreviousNode(currentNode);
                    queue.add(neighborInfo); // Re-add to the queue to reprocess
                }
                edge.setCurrent(false);
            }

            currentNode.visit();
            // Update the data
            data = TableDataUtility.prepareDataForTable(pathInfoMap);

// Update the table model
            tableModel.setDataVector(data, columnNames);

// Optional: Add a delay to visualize the changes more clearly
            try {
                Thread.sleep(500);  // Pause for half a second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            frame.repaint();
        }
        for (Node node: pathInfoMap.keySet()
             ) {
            System.out.println(node + "  Shortest Path: " + pathInfoMap.get(node).getShortestPath() + " Previous Node: " + pathInfoMap.get(node).getPreviousNode());
        }

    }

    public static int[][] readCoordinates(String filePath){
        ArrayList<int[]> tempList = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(filePath));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");

                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);

                tempList.add(new int[]{x, y});
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Convert ArrayList to 2D array
        int[][] coordinates = new int[tempList.size()][2];
        for (int i = 0; i < tempList.size(); i++) {
            coordinates[i] = tempList.get(i);
        }

        return coordinates;
    }


}
