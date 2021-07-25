import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class l007_diaSet {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static int size(TreeNode root) {
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

    public static int height(TreeNode root) {
        return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1;
    }

    public static int diameter_01(TreeNode root) {
        if (root == null)
            return 0;

        int ld = diameter_01(root.left);
        int rd = diameter_01(root.right);

        int lh = height(root.left);
        int rh = height(root.right);

        return Math.max(Math.max(ld, rd), lh + rh + 2);
    }

    // {d,h}
    public static int[] diameter_02(TreeNode root) {
        if (root == null)
            return new int[] { 0, -1 };

        int[] lr = diameter_02(root.left);
        int[] rr = diameter_02(root.right);

        int[] myRes = new int[2];
        myRes[0] = Math.max(Math.max(lr[0], rr[0]), lr[1] + rr[1] + 2);
        myRes[1] = Math.max(lr[1], rr[1]) + 1;
        return myRes;
    }

    // {LeafToLeafMaxPathSum, NodeToLeafMaxPathSum}
    public static int[] maxPathSum_01(TreeNode root) {
        if (root == null) {
            return new int[] { -(int) 1e9, -(int) 1e9 };
        }

        if (root.left == null && root.right == null) {
            return new int[] { -(int) 1e9, root.val };
        }

        int[] lr = maxPathSum_01(root.left);
        int[] rr = maxPathSum_01(root.right);

        int[] myRes = new int[2];
        myRes[0] = Math.max(lr[0], rr[0]);
        if (root.left != null && root.right != null) {
            myRes[0] = Math.max(myRes[0], lr[1] + rr[1] + root.val);
        }

        myRes[1] = Math.max(lr[1], rr[1]) + root.val;
        return myRes;
    }

    static int LeafToLeafMaxPathSum = -(int) 1e9;

    public static int maxPathSum_02(TreeNode root) {
        if (root == null) {
            return -(int) 1e9;
        }

        if (root.left == null && root.right == null) {
            return root.val;
        }

        int lnl = maxPathSum_02(root.left); // left node to leaf
        int rnl = maxPathSum_02(root.right);// right node to leaf

        if (root.left != null && root.right != null) {
            LeafToLeafMaxPathSum = Math.max(LeafToLeafMaxPathSum, lnl + root.val + rnl);
        }

        return Math.max(lnl, rnl) + root.val;
    }

    public static int maxPathSum(TreeNode root) {
        maxPathSum_02(root);
        return LeafToLeafMaxPathSum;
    }

    // Node to Node max path sum

    public static int max(int... arr) {
        int max = arr[0];
        for (int ele : arr)
            max = Math.max(max, ele);

        return max;
    }

    static int NodeToNodeMaxPathSum = -(int) 1e9;

    public static int maxPathSum_(TreeNode root) {
        if (root == null)
            return 0;

        int lrtn = maxPathSum_(root.left); // left root To Node
        int rrtn = maxPathSum_(root.right); // right root To Node

        int rootToNode = Math.max(lrtn, rrtn) + root.val;
        NodeToNodeMaxPathSum = max(NodeToNodeMaxPathSum, rootToNode, root.val, lrtn + root.val + rrtn);

        return max(rootToNode,root.val);
    }

    public static int maxPathSum(TreeNode root) {
        maxPathSum_(root);
        return NodeToNodeMaxPathSum;
    }

}