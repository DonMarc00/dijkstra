import java.util.*;

public class TableDataUtility {

    public static Object[][] prepareDataForTable(Map<Node, PathInfo> pathInfoMap) {
        List<Node> sortedNodes = new ArrayList<>(pathInfoMap.keySet());
        Collections.sort(sortedNodes, Comparator.comparingInt(Node::getId));  // Assuming Node has a getId method

        Object[][] data = new Object[pathInfoMap.size()][3];
        int index = 0;
        for (Node node : sortedNodes) {
            PathInfo info = pathInfoMap.get(node);
            data[index][0] = node.toString();
            data[index][1] = info.getShortestPath();
            data[index][2] = (info.getPreviousNode() != null) ? info.getPreviousNode().toString() : "None";
            index++;
        }
        return data;
    }

}
