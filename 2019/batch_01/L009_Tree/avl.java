public class avl {
    public static class Node {
        int data = 0;
        Node left = null;
        Node right = null;

        int height = -1;
        int bal = 0;

        Node() {

        }

        Node(int data) {
            this.data = data;
        }

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static void display(Node node) {
        if (node == null)
            return;
        String ans = "";
        ans += node.left != null ? node.left.data : ".";
        ans += " -> " + node.data + "(" + node.bal + ")" + " <- ";
        ans += node.right != null ? node.right.data : ".";
        System.out.println(ans);

        display(node.left);
        display(node.right);
    }

    public static Node addData(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }

        if (data < node.data) {
            node.left = addData(node.left, data);
        } else {
            node.right = addData(node.right, data);
        }

        node = rotation(node);
        return node;

    }

    public static Node maxInTree(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public static Node minInTree(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static Node removeData(Node node, int data) {
        if (node == null) {
            return null;
        }

        if (data < node.data) {
            node.left = removeData(node.left, data);
        } else if (data > node.data) {
            node.right = removeData(node.right, data);
        } else {
            if (node.left == null || node.right == null) {
                return node.left != null ? node.left : node.right;
            }

            Node maxNode = maxInTree(node.left);
            node.data = maxNode.data;
            node.left = removeData(node.left, maxNode.data);
        }

        node = rotation(node);
        return node;
    }

    // avl.==========================================

    public static void updateHeightBalance(Node node) {
        int lh = -1;
        int rh = -1;
        if (node.left != null)
            lh = node.left.height;
        if (node.right != null)
            rh = node.right.height;

        node.height = Math.max(lh, rh) + 1;
        node.bal = lh - rh;
    }

    public static Node ll(Node x) {
        Node y = x.left;
        Node yKaRight = y.right;

        y.right = x;
        x.left = yKaRight;

        updateHeightBalance(x);
        updateHeightBalance(y);

        return y;
    }

    public static Node rr(Node x) {
        Node y = x.right;
        Node yKaLeft = y.left;

        y.left = x;
        x.right = yKaLeft;

        updateHeightBalance(x);
        updateHeightBalance(y);

        return y;
    }

    public static Node rotation(Node node) {
        updateHeightBalance(node);
        if (node.bal == 2) { // ll,lr
            if (node.left.bal == 1) { // ll
                return ll(node);
            } else { // lr
                node.left = rr(node.left);
                return ll(node);

            }
        } else if (node.bal == -2) { // rr,rl
            if (node.right.bal == -1) { // rr
                return rr(node);
            } else { // rl
                node.right = ll(node.right);
                return rr(node);
            }
        }

        return node;
    }

    public static void main(String[] args) {
        Node root = null;
        for (int i = 1; i < 12; i++) {
            root = addData(root, i * 10);
        }

        display(root);

    }

}