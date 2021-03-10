import java.util.ArrayList;

public class l003GT {
    public static class Node {
        int data = 0;
        ArrayList<Node> childs = new ArrayList();;

        Node(int data) {
            this.data = data;
        }
    }

    public static int size(Node node) {
        int sz = 0;
        for (Node child : node.childs) {
            sz += size(child);
        }

        return sz + 1;
    }

    public static int height(Node node) {
        int h = -1;
        for (Node child : node.childs) {
            h = Math.max(h, height(child));
        }

        return h + 1;
    }

    public static int maximum(Node node) {
        int maxEle = node.data;
        for (Node child : node.childs) {
            maxEle = Math.max(maxEle, maximum(child));
        }

        return maxEle;
    }

    public static int minimum(Node node) {
        int minEle = node.data;
        for (Node child : node.childs) {
            minEle = Math.min(minEle, minimum(child));
        }

        return minEle;
    }

    public static boolean find(Node node, int data) {
        boolean res = node.data == data;
        for (Node child : node.childs) {
            res = res || find(child, data);
        }

        return res;
    }

    public static boolean nodeToRootPath(Node node, int data, ArrayList<Node> list) {
        boolean res = node.data == data;
        for (Node child : node.childs) {
            res = res || find(child, data);
        }

        if (res)
            list.add(node);

        return res;
    }

    public static Node LCANode(Node node, int d1, int d2) {
        ArrayList<Node> list1 = new ArrayList<>();
        ArrayList<Node> list2 = new ArrayList<>();

        nodeToRootPath(node, d1, list1);
        nodeToRootPath(node, d2, list2);

        int i = list1.size() - 1;
        int j = list2.size() - 1;

        Node LCA = null;
        int LCADistance = 0;

        while (i >= 0 && j >= 0) {
            if (list1.get(i) != list2.get(j))
                break;

            LCADistance++;
            LCA = list1.get(i);
            i--;
            j--;
        }

        return LCA;
    }

    public static int NodeToNodeDistance(Node node, int d1, int d2) {
        ArrayList<Node> list1 = new ArrayList<>();
        ArrayList<Node> list2 = new ArrayList<>();

        nodeToRootPath(node, d1, list1);
        nodeToRootPath(node, d2, list2);

        int i = list1.size() - 1;
        int j = list2.size() - 1;

        int LCADistance = 0;

        while (i >= 0 && j >= 0) {
            if (list1.get(i) != list2.get(j))
                break;

            LCADistance++;
            i--;
            j--;
        }

        int dis = (list1.size() + list2.size() - 2 * (LCADistance) + 1); // distance in terms of No of Nodes

        // int dis = (list1.size() + list2.size() - 2 * (LCADistance) + 1 - 1);
        // distance in terms of No of Edges

        return dis;
    }

}