import java.util.ArrayList;

public class l003_GenericTree {

    public static class Node {
        int data = 0;
        ArrayList<Node> childs;

        Node(int data) {
            this.data = data;
            this.childs = new ArrayList<>();
        }
    }

    public static int height(Node root) {
        int h = -1;
        for (Node child : root.childs)
            h = Math.max(height(child), h);

        return h + 1;
    }

    public static int size(Node root) {
        int count = 0;
        for (int i = 0; i < root.childs.size(); i++) {
            Node child = root.childs.get(i);
            count += size(child);
        }

        return count + 1;
    }

    public static int size2(Node root) {
        int count = 0;
        for (Node child : root.childs) {
            count += size(child);
        }

        return count + 1;
    }

    public static int maximum(Node root) {
        int max = root.data;
        for (Node child : root.childs)
            max = Math.max(maximum(child), max);

        return max;
    }

    public static int minimum(Node root) {
        int min = root.data;
        for (Node child : root.childs)
            min = Math.min(minimum(child), min);

        return min;
    }

    public static int sum(Node root) {
        int sum = root.data;
        for (Node child : root.childs)
            sum += sum(child);

        return sum;
    }

    public static boolean find(Node root, int data) {
        if (root.data == data)
            return true;

        boolean res = false;
        for (Node child : root.childs)
            res = res || find(child, data);

        return res;
    }

    public static boolean find2(Node root, int data) {
        if (root.data == data)
            return true;

        boolean res = false;
        for (Node child : root.childs)
            if (find2(child, data)) {
                res = true;
                break;
            }

        return res;
    }

    public static int countLeaves(Node node) {
        if (node.childs.size() == 0)
            return 1;

        int count = 0;
        for (Node child : node.childs)
            count += countLeaves(child);

        return count;
    }

    public static boolean nodeToRootPath(Node root, int data, ArrayList<Node> ans) {

        if (root.data == data) {
            ans.add(root);
            return true;
        }

        boolean res = false;
        for (Node child : root.childs)
            res = res || nodeToRootPath(child, data, ans);

        if (res)
            ans.add(root);
        return res;
    }

    public static ArrayList<Node> nodeToRootPath(Node root, int data) {
        if (root.data == data) {
            ArrayList<Node> base = new ArrayList<>();
            base.add(root);
            return base;
        }

        ArrayList<Node> smallAns = new ArrayList<>();
        for (Node child : root.childs) {
            smallAns = nodeToRootPath(child, data);
            if (smallAns.size() != 0)
                break;
        }
        if (smallAns.size() != 0)
            smallAns.add(root);
        return smallAns;
    }

    public Node lowestCommonAncestor(Node root, int p, int q) {
        ArrayList<Node> list1 = nodeToRootPath(root, p);
        ArrayList<Node> list2 = nodeToRootPath(root, q);

        int i = list1.size() - 1;
        int j = list2.size() - 1;

        Node LCA = null;
        while (i >= 0 && j >= 0) {
            if (list1.get(i) != list2.get(j))
                break;
            LCA = list2.get(j);
            i--;
            j--;

        }

        return LCA;
    }

}