public class l003AVL {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        int bal = 0;
        int height = -1;

        TreeNode(int val) {
            this.val = val;
        }
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

        updateBalanceAndHeight(A);
        updateBalanceAndHeight(B);

        return B;
    }

    public static TreeNode leftRotation(TreeNode A) { // O(1)
        TreeNode B = A.right;
        TreeNode BkaLeft = B.left;

        B.left = A;
        A.right = BkaLeft;

        updateBalanceAndHeight(A);
        updateBalanceAndHeight(B);

        return B;
    }

    public static TreeNode getRotation(TreeNode node) {
        updateBalanceAndHeight(node);
        if (node.bal == 2) { // ll, lr
            if (node.left.bal == 1) { // ll
                return rightRotation(node);
            } else { // lr

            }

        } else if (node.bal == -2) { // rr, rl
            if (node.right.bal == -1) { // rr
                return leftRotation(node);
            } else { // rl

            }
        }

        return node;
    }

    // Basic_BST.======================================================================

    public static int maximumEle(TreeNode node) {
        TreeNode curr = node;
        while (curr.right != null) {
            curr = curr.right;
        }

        return curr.val;
    }

    public static TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val > val)
            root.left = insertIntoBST(root.left, val);
        else
            root.right = insertIntoBST(root.right, val);

        return getRotation(root);
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

}