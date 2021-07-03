public class l004_avl {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        int bal = 0;
        int height = 0;

        TreeNode(int val) {
            this.val = val;
            this.height = 0;
            this.bal = 0;
        }
    }

    // ========================================================================

    // O(1)
    public static void updateHeightBalance(TreeNode node) {
        int lh = node.left != null ? node.left.height : -1;
        int rh = node.right != null ? node.right.height : -1;

        node.height = Math.max(lh, rh) + 1;
        node.bal = lh - rh;
    }

    // O(1)
    public static TreeNode rightRotation(TreeNode A) {
        TreeNode B = A.left;
        TreeNode BKaRight = B.right;

        B.right = A;
        A.left = BKaRight;

        updateHeightBalance(A);
        updateHeightBalance(B);

        return B;
    }

    // O(1)
    public static TreeNode leftRotation(TreeNode A) {
        TreeNode B = A.right;
        TreeNode BKaLeft = B.left;

        B.left = A;
        A.right = BKaLeft;

        updateHeightBalance(A);
        updateHeightBalance(B);

        return B;
    }

    // O(1)
    public static TreeNode getRotation(TreeNode root) {
        updateHeightBalance(root);

        if (root.bal == 2) { // ll,lr
            if (root.left.bal == 1) { // ll
                return rightRotation(root);
            } else { // lr
                root.left = leftRotation(root.left);
                return rightRotation(root);
            }

        } else if (root.bal == -2) { // rr,rl
            if (root.right.bal == -1) { // rr
                return leftRotation(root);
            } else { // rl
                root.right = rightRotation(root.right);
                return leftRotation(root);
            }
        }

        return root;
    }

    // ========================================================================

    // O(Logn)
    public static int getMaximum(TreeNode root) {
        while (root.right != null)
            root = root.right;

        return root.val;
    }

    // O(Logn)
    public static TreeNode add(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (root.val < val)
            root.right = add(root.right, val);
        else
            root.left = add(root.left, val);

        root = getRotation(root);
        return root;
    }

    // O(Logn)
    public static TreeNode remove(TreeNode root, int data) {
        if (root == null)
            return null;

        if (root.val < data)
            root.right = remove(root.right, data);
        else if (root.val > data)
            root.left = remove(root.left, data);
        else {
            if (root.left == null || root.right == null)
                return root.left != null ? root.left : root.right;

            int maximumData = getMaximum(root.left);
            root.val = maximumData;

            root.left = remove(root.left, maximumData);
        }

        root = getRotation(root);
        return root;
    }

    // O(n)
    public static void display(TreeNode node) {
        if (node == null) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append((node.left != null ? node.left.val : "."));
        sb.append(" -> " + node.val + " <- ");
        sb.append((node.right != null ? node.right.val : "."));

        System.out.println(sb.toString());

        display(node.left);
        display(node.right);
    }

    // 1382====================================================================
    // O(1)
    public int getHeight(TreeNode node) {
        return node == null ? -1 : Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    public int getBal(TreeNode node) {
        int lh = getHeight(node.left);
        int rh = getHeight(node.right);

        return lh - rh;
    }

    // O(1)
    public TreeNode rightRotation(TreeNode A) {
        TreeNode B = A.left;
        TreeNode BKaRight = B.right;

        B.right = A;
        A.left = BKaRight;

        B.right = getRotation(A);
        return getRotation(B);
    }

    // O(1)
    public TreeNode leftRotation(TreeNode A) {
        TreeNode B = A.right;
        TreeNode BKaLeft = B.left;

        B.left = A;
        A.right = BKaLeft;

        B.left = getRotation(A);
        return getRotation(B);
    }

    // O(1)
    public TreeNode getRotation(TreeNode root) {
        if (getBal(root) >= 2) { // ll,lr
            if (getBal(root.left) >= 1) { // ll
                return rightRotation(root);
            } else { // lr
                root.left = leftRotation(root.left);
                return rightRotation(root);
            }

        } else if (getBal(root) <= -2) { // rr,rl
            if (getBal(root.right) <= -1) { // rr
                return leftRotation(root);
            } else { // rl
                root.right = rightRotation(root.right);
                return leftRotation(root);
            }
        }

        return root;
    }

    public TreeNode balanceBST(TreeNode root) {
        if (root == null)
            return null;

        root.left = balanceBST(root.left);
        root.right = balanceBST(root.right);

        return getRotation(root);
    }

    public static void main(String[] args) {
        TreeNode root = null;
        for (int i = 1; i <= 100; i++) {
            root = add(root, i * 10);
        }

        display(root);
    }

    // 1382

}