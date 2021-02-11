import java.util.Random;

public class l004convertBSTInToAVL {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        int bal = 0;
        int height = 0;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static int height(TreeNode node) {
        return node == null ? -1 : Math.max(height(node.left), height(node.right)) + 1;
    }

    public static int getBalance(TreeNode node) {
        return height(node.left) - height(node.right);
    }

    public static void updateBalanceAndHeight(TreeNode node) { // O(1)
        if (node == null)
            return;

        int lh = -1;
        int rh = -1;

        if (node.left != null)
            lh = node.left.height;
        if (node.right != null)
            rh = node.right.height;

        node.bal = lh - rh;
        node.height = Math.max(lh, rh) + 1;
    }

    public static TreeNode rightRotation(TreeNode A) { // O(1)
        TreeNode B = A.left;
        TreeNode BkaRight = B.right;

        B.right = A;
        A.left = BkaRight;

        B.right = getRotation(A);
        return getRotation(B);
    }

    public static TreeNode leftRotation(TreeNode A) { // O(1)
        TreeNode B = A.right;
        TreeNode BkaLeft = B.left;

        B.left = A;
        A.right = BkaLeft;

        B.left = getRotation(A);
        return getRotation(B);
    }

    public static TreeNode getRotation(TreeNode node) { // O(1)
        updateBalanceAndHeight(node);
        if (node.bal >= 2) { // ll, lr
            if (node.left.bal >= 1) { // ll
                return rightRotation(node);
            } else { // lr
                node.left = leftRotation(node.left);
                return rightRotation(node);
            }

        } else if (node.bal <= -2) { // rr, rl
            if (node.right.bal <= -1) { // rr
                return leftRotation(node);
            } else { // rl
                node.right = rightRotation(node.right);
                return leftRotation(node);
            }
        }

        return node;
    }

    // Basic_BST.======================================================================

    public static TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val > val)
            root.left = insertIntoBST(root.left, val);
        else
            root.right = insertIntoBST(root.right, val);

        // root = getRotation(root);
        updateBalanceAndHeight(root);
        return root;
    }

    public static int maximumEle(TreeNode node) {
        TreeNode curr = node;
        while (curr.right != null) {
            curr = curr.right;
        }

        return curr.val;
    }

    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;

        if (root.val > key)
            root.left = deleteNode(root.left, key);
        else if (root.val < key)
            root.right = deleteNode(root.right, key);
        else {
            if (root.left == null || root.right == null)
                return root.left != null ? root.left : root.right;

            int maxValue = maximumEle(root.left);
            root.val = maxValue;

            root.left = deleteNode(root.left, maxValue);
        }

        return getRotation(root);
    }

    // for created BST.
    public static TreeNode postOrder(TreeNode node) {
        if (node == null)
            return null;

        node.left = postOrder(node.left);
        node.right = postOrder(node.right);

        return getRotation(node);
    }

    public static void display(TreeNode node) {
        if (node == null)
            return;
        StringBuilder sb = new StringBuilder();
        sb.append(node.left == null ? "." : node.left.val);
        sb.append(" -> " + node.val + "(" + node.bal + ")" + " <- ");
        sb.append(node.right == null ? "." : node.right.val);
        System.out.println(sb.toString());

        display(node.left);
        display(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = null;
        Random rand = new Random();

        for (int i = 1; i <= 25; i++) {
            root = insertIntoBST(root, rand.nextInt(1000));
            // display(root);

            // System.out.println("===========================================================");
            // System.out.println();
        }

        display(root);
        System.out.println("===========================================================");
        System.out.println();

        root = postOrder(root);
        display(root);
    }

}