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
        return root == null ? -1: Math.max(height(root.left), height(root.right)) + 1;
    }

    public static int maximum(BinaryTree root) {
       
    }

    public static int miminum(BinaryTree root) {
       
    }

    public static boolean find(BinaryTree root,int data) {
       
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
