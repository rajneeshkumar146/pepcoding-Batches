import java.util.ArrayList;
import java.util.Collections;

public class l002_BST {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        Node(int data) {
            this(data, null, null);
        }
    }

    // T : O(n), S : O(1)
    public static int size(Node node) {
        return node == null ? 0 : size(node.left) + size(node.right) + 1;
    }

    // T : O(n), S : O(1)
    public static int hight(Node node) {
        return node == null ? -1 : Math.max(hight(node.left), hight(node.right)) + 1;
    }

    // T : O(logn), S : O(1)
    public static int maximum(Node node) {
        while (node.right != null)
            node = node.right;

        return node.data;
    }

    // T : O(logn), S : O(1)
    public static int minimum(Node node) {
        while (node.left != null)
            node = node.left;

        return node.data;
    }

    // T : O(logn), S : O(1)
    public static boolean find(Node node, int data) {
        while (node != null) {
            if (node.data == data)
                return true;
            else if (node.data < data)
                node = node.right;
            else
                node = node.left;
        }

        return false;
    }

    public static int sum(Node node) {
        return node == null ? 0 : sum(node.left) + sum(node.right) + node.data;
    }

    // T : O(logn), S : O(1)
    public static ArrayList<Node> nodeToRootPath(Node node, int data) {
        ArrayList<Node> list = new ArrayList<>();
        boolean flag = false;
        while (node != null) {
            list.add(node);
            if (node.data == data) {
                flag = true;
                break;
            } else if (node.data < data)
                node = node.right;
            else
                node = node.left;
        }

        if (!flag)
            list.clear();

        Collections.reverse(list);
        return list;
    }

    public static int lca(Node node, int d1, int d2) {
        int lca = -1;
        while (node != null) {
            if (node.data < d1 && node.data < d2)
                node = node.right;
            else if (node.data > d1 && node.data > d2)
                node = node.left;
            else {
                lca = node.data;
                break;
            }
        }

        return lca;
    }

}