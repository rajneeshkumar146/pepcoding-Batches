public class l003_AVL {
    public static class Node {
        int data = 0;
        Node left = null;
        Node right = null;

        int height = 0;
        int bal = 0;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void display(Node node) {
        if (node == null)
            return;
        String str = "";
        str += node.left != null ? node.left.data : ".";
        str += " <- " + node.data + " -> ";
        str += node.right != null ? node.right.data : ".";
        System.out.println(str);

        display(node.left);
        display(node.right);
    }

    // AVL_Util.============================================================================

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

    // left left skew
    public static Node ll(Node A) {
        Node B = A.left;
        Node BKaRight = B.right;

        B.right = A;
        A.left = BKaRight;

        updateHeightBalance(A);
        updateHeightBalance(B);

        return B;
    }

    // right right skew
    public static Node rr(Node A) {
        Node B = A.right;
        Node BKaLeft = B.left;

        B.left = A;
        A.right = BKaLeft;

        updateHeightBalance(A);
        updateHeightBalance(B);

        return B;
    }

    public static Node rotateSubTree(Node node) {
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

    // BST_Functions.==========================================================================

    public static Node addData(Node root, int data) {
        if (root == null)
            return new Node(data);
        if (data < root.data)
            root.left = addData(root.left, data);
        else
            root.right = addData(root.right, data);
        return rotateSubTree(root);
    }

    public static int maximum(Node node) {
        Node curr = node;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr.data;
    }

    public static Node removeData(Node root, int data) {
        if (root == null)
            return null;

        if (data < root.data)
            root.left = removeData(root.left, data);
        else if (data > root.data)
            root.right = removeData(root.right, data);
        else if (root.data == data) {
            if (root.left == null || root.right == null)
                return root.left != null ? root.left : root.right;
            int maxEle = maximum(root.left);
            root.data = maxEle;
            root.left = removeData(root.left, maxEle);
        }
        return rotateSubTree(root);
    }

    public static void solve() {
        int[] arr = new int[15];
        for (int i = 0; i < arr.length; i++)
            arr[i] = (i + 1) * 10;

        Node root = null;
        for (int ele : arr)
            root = addData(root, ele);

        display(root);
        System.out.println();
        int i = 0;
        for (int ele : arr) {
            root = removeData(root, ele);
            if (i++ == 6)
                break;
        }
        display(root);
    }

    public static void main(String[] args) {
        solve();
    }

}