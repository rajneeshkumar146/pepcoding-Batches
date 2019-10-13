public class BTTree {
    private class Node {
        private int data = 0;
        private Node left = null;
        private Node right = null;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    Node root = null;

    public BTTree(int[] arr) {
        root = construct(arr);
    }

    static int idx = 0;
    public Node construct(int[] arr) {
        if (idx >= arr.length || arr[idx] == -1) {
            idx++;
            return null;
        }

        Node node = new Node(arr[idx], null, null);
        idx++;
        node.left = construct(arr);
        node.right = construct(arr);

        return node;
    }









}