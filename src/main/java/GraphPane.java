import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphPane extends JPanel {

    private List<Node> nodes;
    private List<Edge> edges;

    public GraphPane(List<Node> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }
    protected void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (Edge edge : edges) {
            if (edge.isCurrent()) {
                g2d.setColor(Color.RED);
            } else {
                g2d.setColor(Color.BLACK);
            }
            g2d.drawLine(
                    (int) edge.node1.getPosx(),
                    (int) edge.node1.getPosy(),
                    (int) edge.node2.getPosx(),
                    (int) edge.node2.getPosy()
            );
            int midX = (int) ((edge.node1.getPosx() + edge.node2.getPosx()) / 2);
            int midY = (int) ((edge.node1.getPosy() + edge.node2.getPosy()) / 2);

            // Draw the length of the edge at the midpoint
            g2d.setColor(Color.BLACK);
            g2d.drawString(String.format("%.2f", edge.getEdgeLegth()), midX, midY);
        }

        // Draw nodes
        for (Node node : nodes) {
            if (node.isVisited()) {
                g2d.setColor(Color.GREEN);
            } else {
                g2d.setColor(Color.BLUE);
            }
            g2d.fillOval((int) node.getPosx() - 15, (int) node.getPosy() - 15, 30, 30);
            g2d.setColor(Color.WHITE);
            g2d.drawString(String.valueOf(node.getPosx()) + "," + String.valueOf(node.getPosy()), (int) node.getPosx() - 10, (int) node.getPosy() + 5);
        }

    }

}
