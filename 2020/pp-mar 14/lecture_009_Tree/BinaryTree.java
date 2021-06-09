import java.util.ArrayList;

public class BinaryTree {
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

    public static void preOrder(Node root, ArrayList<Integer> ans) {
        if (root == null)
            return;

        ans.add(root.data);
        preOrder(root.left, ans);
        preOrder(root.right, ans);
    }

    public static void inOrder(Node root, ArrayList<Integer> ans) {
        if (root == null)
            return;

        inOrder(root.left, ans);
        ans.add(root.data);
        inOrder(root.right, ans);
    }

    public static void postOrder(Node root, ArrayList<Integer> ans) {
        if (root == null)
            return;

        postOrder(root.left, ans);
        postOrder(root.right, ans);
        ans.add(root.data);
    }

    public static int size(Node node) {
        if (node == null)
            return 0;

        int leftSize = size(node.left);
        int rightSize = size(node.right);

        return leftSize + rightSize + 1;

        // return node == null ? 0 : size(node.left) + size(node.right) + 1;
    }

    public static int sum(Node node) {
        if (node == null)
            return 0;

        int leftSum = sum(node.left);
        int rightSum = sum(node.right);

        return leftSum + rightSum + node.data;
        // return node == null ? 0 : sum(node.left) + sum(node.right) + node.data;
    }

    public static int max(Node node) {
        if (node == null)
            return -(int) 1e9;

        int leftMax = max(node.left);
        int rightMax = max(node.right);

        return Math.max(Math.max(leftMax, rightMax), node.data);

        // return node == null ? -(int) 1e9 : Math.max(node.data,
        // Math.max(max(node.left), max(node.right)));
    }

    public static int max2(Node node) {
        if (node == null)
            return -(int) 1e9;

        int maxEle = node.data;
        maxEle = Math.max(maxEle, max2(node.left));
        maxEle = Math.max(maxEle, max2(node.left));

        return maxEle;

        // return node == null ? -(int) 1e9 : Math.max(node.data,
        // Math.max(max(node.left), max(node.right)));
    }

    public static int min(Node node) {
        if (node == null)
            return -(int) 1e9;

        int leftMin = min(node.left);
        int rightMin = min(node.right);

        return Math.min(Math.min(leftMin, rightMin), node.data);

        // return node == null ? -(int) 1e9 : Math.max(node.data,
        // Math.min(min(node.left), min(node.right)));
    }

    // Height in Terms of Edges return -1 at null, Height in terms of Nodes return 0
    // at null
    public static int height(Node node) {
        return node == null ? -1 : Math.max(height(node.left), height(node.right)) + 1;
    }

    // https://practice.geeksforgeeks.org/problems/count-leaves-in-binary-tree/1
    public static int countLeaves(Node node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 1;

        return countLeaves(node.left) + countLeaves(node.right);
    }

    // Print the nodes having exactly one child in a Binary tree
    public static void exactlyOneChild(Node node, ArrayList<Integer> ans) {
        if (node == null || (node.left == null && node.right == null))
            return;

        if (node.left == null || node.right == null)
            ans.add(node.data);

        exactlyOneChild(node.left, ans);
        exactlyOneChild(node.right, ans);

    }

    public static int countExactlyOneChild(Node node) {
        if (node == null || (node.left == null && node.right == null))
            return 0;

        int left = countExactlyOneChild(node.left);
        int right = countExactlyOneChild(node.right);
        int sum = left + right;
        if (node.left == null || node.right == null)
            sum += 1;
        return sum;
    }

}