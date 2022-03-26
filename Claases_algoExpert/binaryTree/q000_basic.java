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
        return root == null ? Integer.MAX_VALUE
                : Math.min(root.value, Math.min(minimum(root.left), minimum(root.right)));
    }

    public static boolean find(BinaryTree root, int data) {
        if (root == null)
            return false;

        if (root.value == data)
            return true;

        return find(root.left, data) || find(root.right, data);
    }

    public static boolean nodeToRootPath_(BinaryTree root, int data, ArrayList<BinaryTree> ans) {
        if (root == null)
            return false;

        if (root.value == data) {
            ans.add(root);
            return true;
        }

        boolean res = nodeToRootPath_(root.left, data, ans) || nodeToRootPath_(root.right, data, ans);
        if (res)
            ans.add(root);
        return res;
    }

    public static ArrayList<BinaryTree> nodeToRootPath(BinaryTree root, int data) {
        ArrayList<BinaryTree> ans = new ArrayList<>();
        nodeToRootPath_(root, data, ans);
        return ans;
    }

    // ===========================================================

    public static class diaPair {
        int dia = 0;
        int height = -1;

        diaPair(int dia, int height) {
            this.dia = dia;
            this.height = height;
        }
    }

    public static diaPair diameter(BinaryTree root) {
        if (root == null) {
            diaPair base = new diaPair(0, -1);
            return base;
        }

        diaPair lp = diameter(root.left);
        diaPair rp = diameter(root.right);

        int dia = Math.max(Math.max(lp.dia, rp.dia), lp.height + rp.height + 2);
        int h = Math.max(lp.height, rp.height) + 1;
        diaPair myPair = new diaPair(dia, h);

        return myPair;
    }

    public int binaryTreeDiameter(BinaryTree tree) {
        return diameter(tree).dia;
    }

    public static void branchSums(BinaryTree root, List<Integer> ans, int ssf) { // sum so far
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            ans.add(ssf + root.value);
            return;
        }

        branchSums(root.left, ans, ssf + root.value);
        branchSums(root.right, ans, ssf + root.value);
    }

    public static List<Integer> branchSums(BinaryTree root) {
        List<Integer> ans = new ArrayList<>();
        branchSums(root, ans, 0);
        return ans;
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
