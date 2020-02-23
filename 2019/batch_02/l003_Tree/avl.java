public class avl {

    public static class Node {

        int data = 0;
        Node left = null;
        Node right = null;

        int height = 0;
        int bal = 0;

        public Node() {

        }

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {

            String str = "";
            str += ((left != null ? left.data : "."));
            str += (" -> " + data + " <- ");
            str += ((right != null ? right.data : "."));
            str += "\n";

            if (left != null)
                str += left.toString();
            if (right != null)
                str += right.toString();

            return str;
        }

    }

    public static void updateHeightAndBalance(Node node) {
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
        Node yright = y.right;

        y.right = x;
        x.left = yright;

        updateHeightAndBalance(x);
        updateHeightAndBalance(y);

        return y;
    }

    public static Node rr(Node x) {
        Node y = x.right;
        Node yleft = y.left;

        y.left = x;
        x.right = yleft;

        updateHeightAndBalance(x);
        updateHeightAndBalance(y);

        return y;
    }

    public static Node roation(Node node) {
        updateHeightAndBalance(node);
        if (node.bal == 2) { // ll,lr
            if (node.left.bal == 1) { // ll
                return ll(node);
            } else { // lr
                node.left = rr(node.left);
                return ll(node);

            }
        } else if (node.bal == -2) { // rr,rl
            if (node.right.bal == -1) { // ll
                return rr(node);
            } else {
                node.right = ll(node.right);
                return rr(node);

            }
        }

        return node;

    }

    public static Node addData(Node node, int data) {
        if (node == null)
            return new Node(data);

        if (data < node.data)
            node.left = addData(node.left, data);
        else
            node.right = addData(node.right, data);

        node = roation(node);
        return node;
    }

    public static int maxInTree(Node root) {
        if (root == null)
            return Integer.MIN_VALUE;

        Node rnode = root;
        while (rnode.right != null) {
            rnode = rnode.right;
        }

        return rnode.data;
    }

    public static Node removeNode(Node node, int data) {
        if (node == null)
            return null;

        if (node.data == data) {
            if (node.left == null || node.right == null) {
                return node.left == null ? node.right : node.left;
            }

            int maxdata = maxInTree(node.left);
            node.data = maxdata;

            node.left = removeNode(node.left, maxdata);
        } else if (data < node.data)
            node.left = removeNode(node.left, data);
        else
            node.right = removeNode(node.right, data);

        node = roation(node);
        return node;
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

    public static void main(String[] args) {
        Node root = null;
        for (int i = 1; i <= 6; i++) {
            root = addData(root, i * 10);

        }

        display(root);

    }

}