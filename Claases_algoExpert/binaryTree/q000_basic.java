import java.util.*;

class Program {

    public static int nodeDepths(BinaryTree root) {
        if (root == null)
            return -1;

        int lh = nodeDepths(root.left);
        int rh = nodeDepths(root.right);

        return Math.max(lh, rh) + 1;
    }

    public static int height(BinaryTree root) {
        return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1;
    }

    public static int maximum(BinaryTree root) {
        if (root == null)
            return Integer.MIN_VALUE;

        int lmax = maximum(root.left);
        int rmax = maximum(root.right);

        return Math.max(Math.max(lmax, rmax), root.value);
    }

    public static int minimum(BinaryTree root) {
        return root == null ? Integer.MAX_VALUE : Math.min(root.val, Math.min(minimum(root.left), minimum(root.right)));
    }

    public static boolean find(BinaryTree root, int data) {
        if (root == null)
            return false;

        if (root.val == data)
            return true;

        return find(root.left, data) || find(root.right, data);
    }

    public static ArrayList<BinaryTree> nodeToRootPath(BinaryTree root, int data){


    }

    static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }
}
