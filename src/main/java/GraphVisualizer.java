import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraphVisualizer extends JFrame {

    List<Node> nodes;
    List<Edge> edges;

    public GraphVisualizer(List<Node> nodes, List<Edge> edges){
        this.nodes = nodes;
        this.edges = edges;

        GraphPane graphPane = new GraphPane(nodes, edges);
        // Inside GraphVisualizer's constructor
        graphPane.setPreferredSize(new Dimension(800, 200));
        this.setPreferredSize(new Dimension(1200, 1200));// Adjust dimensions as needed

        setTitle("Dijkstra Visualization");
        this.setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(graphPane, BorderLayout.CENTER);
        setVisible(true);
    }

}
