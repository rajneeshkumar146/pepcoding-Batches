import java.util.ArrayList;

public class l002BST {
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

    public static int Maximum(TreeNode root) {
        TreeNode curr = root;
        while (curr.right != null) // rightMost
            curr = curr.right;

        return curr.val;
    }

    public static int Minimum(TreeNode root) {
        TreeNode curr = root;
        while (curr.left != null) // leftMost
            curr = curr.left;

        return curr.val;
    }

    public static boolean find(TreeNode root, int data) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.val == data)
                return true;
            else if (curr.val < data)
                curr = curr.right;
            else
                curr = curr.left;
        }

        return false;
    }

    public static ArrayList<TreeNode> rootToNodePath(TreeNode root, int data) {
        ArrayList<TreeNode> ans = new ArrayList<>();
        TreeNode curr = root;
        boolean flag = false;
        while (curr != null) {
            ans.add(curr);
            if (curr.val == data) {
                flag = true;
                break;
            } else if (curr.val < data)
                curr = curr.right;
            else
                curr = curr.left;
        }

        if (!flag)
            ans.clear();

        return ans;
    }

    public TreeNode lowestCommonAncestor(TreeNode node, int p, int q) {
        TreeNode curr = node;
        while (curr != null) {
            if (curr.val < p && curr.val < q)
                curr = curr.right;
            else if (curr.val > p && curr.val > q)
                curr = curr.left;
            else
                return curr;
        }

        return null;
    }

}