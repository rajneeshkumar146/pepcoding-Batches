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

    public static void display(Node node) {
        System.out.print(node.data + " -> ");
        for (Node child : node.childs) {
            System.out.print(child.data + ", ");
        }
        System.out.println();

        for (Node child : node.childs)
            display(child);
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

    public static Node getTail(Node node) {
        Node curr = node;
        while (curr.childs.size() != 0) {
            curr = curr.childs.get(0);
        }

        return curr;
    }

    public static void linearize(Node node) {
        for (Node child : node.childs) {
            linearize(child);
        }

        for (int i = node.childs.size() - 2; i >= 0; i--) {
            Node tail = getTail(node.childs.get(i));
            tail.childs.add(node.childs.get(i + 1));
            node.childs.remove(i + 1);
        }
    }

    public static Node linearize_btr(Node node) {
        if (node.childs.size() == 0)
            return node;

        int n = node.childs.size();
        Node gTail = linearize_btr(node.childs.get(n - 1));
        for (int i = n - 2; i >= 0; i--) {
            Node tail = linearize_btr(node.childs.get(i));
            tail.childs.add(node.childs.get(i + 1));
            node.childs.remove(i + 1);
        }

        return gTail;
    }

    static int ceil;
    static int floor;

    public static void ceilAndFloor(Node node, int data) {
        if (node.data > data)
            ceil = Math.min(ceil, node.data);
        if (node.data < data)
            floor = Math.max(floor, node.data);

        for (Node child : node.childs) {
            ceilAndFloor(child, data);
        }
    }

    public static int kthLargest_(Node node, int bound) {
        int maxLessThanBound = -(int) 1e9;
        for (Node child : node.childs) {
            int recAns = kthLargest_(child, bound);
            maxLessThanBound = Math.max(maxLessThanBound, recAns);
        }

        if (node.data < bound) {
            maxLessThanBound = Math.max(maxLessThanBound, node.data);
        }

        return maxLessThanBound;
    }

    public static int kthLargest(Node node, int k) {
        int bound = (int) 1e9;
        while (k-- > 0) {
            bound = kthLargest_(node, bound);
        }

        return bound;
    }

    public static boolean areSimilar(Node n1, Node n2) {
        if (n1.childs.size() != n2.childs.size())
            return false;

        int n = n1.childs.size();
        for (int i = 0; i < n; i++) {
            Node c1 = n1.childs.get(i);
            Node c2 = n2.childs.get(i);

            if (!areSimilar(c1, c2))
                return false;
        }

        return true;
    }

    public static boolean areMirror(Node n1, Node n2) {
        if (n1.childs.size() != n2.childs.size())
            return false;

        int n = n1.childs.size();
        for (int i = 0, j = n - 1; i < n; i++, j--) {
            Node c1 = n1.childs.get(i);
            Node c2 = n2.childs.get(j);

            if (!areMirror(c1, c2))
                return false;
        }

        return true;
    }

    public static boolean areSimilar_(Node n1, Node n2) {
        if (n1.childs.size() != n2.childs.size())
            return false;

        int n = n1.childs.size();
        for (int i = 0, j = n - 1; i < n; i++, j--) {
            Node c1 = n1.childs.get(i);
            Node c2 = n2.childs.get(j);

            if (!areSimilar_(c1, c2))
                return false;
        }

        return true;
    }

    public static boolean IsSymmetric(Node node) {
        return areSimilar(node, node);
    }

}