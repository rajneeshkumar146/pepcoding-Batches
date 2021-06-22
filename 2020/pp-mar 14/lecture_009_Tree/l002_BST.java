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

    public static void printInRange(Node node, int lr, int rr) {
        if (node == null)
            return;

        printInRange(node.left, lr, rr);

        if (node.data >= lr && node.data <= rr)
            System.out.print(node.data);

        printInRange(node.right, lr, rr);
    }

    public static Node addData(Node node, int data) {
        if (node == null)
            return new Node(data);

        if (node.data < data)
            node.right = addData(node.right, data);
        else if (node.data > data)
            node.left = addData(node.left, data);

        return node;
    }

    public static Node removeNode(Node node, int data) {
        if (node == null)
            return null;

        if (node.data < data)
            node.right = removeNode(node.right, data);
        else if (node.data > data)
            node.left = removeNode(node.left, data);
        else {
            if (node.left == null || node.right == null)
                return node.left != null ? node.left : node.right;

            int minEle = minimum(node.right);
            node.data = minEle;

            node.right = removeNode(node.right, minEle);
        }

        return node;
    }

    public void modify(Node root, int[] arr) {
        if (root == null)
            return;

        modify(root.right, arr);

        root.data += arr[0];
        arr[0] = root.data;

        modify(root.left, arr);
    }

    public Node modify(Node root) {
        int[] arr = new int[1];
        modify(root, arr);
        return root;
    }

}