import java.util.ArrayList;
import java.util.List;

class q000_Leetcode {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean nodeToRootPath_(TreeNode root, int data, ArrayList<TreeNode> ans) {
        if (root == null)
            return false;

        if (root.val == data) {
            ans.add(root);
            return true;
        }

        boolean res = nodeToRootPath_(root.left, data, ans) || nodeToRootPath_(root.right, data, ans);
        if (res)
            ans.add(root);
        return res;
    }

    // 236
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> l1 = new ArrayList<>();
        nodeToRootPath_(root, p.val, l1);

        ArrayList<TreeNode> l2 = new ArrayList<>();
        nodeToRootPath_(root, q.val, l2);

        int i = l1.size() - 1, j = l2.size() - 1;

        TreeNode ans = null;
        while (i >= 0 && j >= 0) {
            if (l1.get(i--) != l2.get(j--))
                break;

            ans = l1.get(i + 1);
        }

        return ans;
    }

    // 863

    public void kDown(TreeNode root, TreeNode blockNode, int level, List<Integer> ans) {
        if (level < 0 || root == null || root == blockNode)
            return;

        if (level == 0) {
            ans.add(root.val);
            return;
        }

        kDown(root.left, blockNode, level - 1, ans);
        kDown(root.right, blockNode, level - 1, ans);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ArrayList<TreeNode> list = new ArrayList<>();
        nodeToRootPath_(root, target.val, list);

        List<Integer> ans = new ArrayList<>();
        TreeNode blockNode = null;
        for (int i = 0; i < list.size(); i++) {
            kDown(list.get(i), blockNode, k - i, ans);
            blockNode = list.get(i);
        }

        return ans;
    }

    // 112
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null && targetSum - root.val == 0)
            return true;

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // 113
    public void pathSum(TreeNode root, int targetSum, List<List<Integer>> ans, List<Integer> smallAns) {
        if (root == null)
            return;
        if (root.left == null && root.right == null && targetSum - root.val == 0) {
            List<Integer> base = new ArrayList<>(smallAns);
            base.add(root.val);
            ans.add(base);
            return;
        }

        smallAns.add(root.val);

        pathSum(root.left, targetSum - root.val, ans, smallAns);
        pathSum(root.right, targetSum - root.val, ans, smallAns);

        smallAns.remove(smallAns.size() - 1);
    }

    public class isBSTPair {
        boolean isBST = true;
        TreeNode prev = null;
    }

    public void isValidBST(TreeNode root, isBSTPair pair) {
        if (root == null)
            return;

        isValidBST(root.left, pair);

        if (pair.prev != null && pair.prev.val >= root.val)
            pair.isBST = false;
        pair.prev = root;
        
        isValidBST(root.right, pair);
    }

    public boolean isValidBST(TreeNode root) {
        isBSTPair pair = new isBSTPair();
        isValidBST(root, pair);

        return pair.isBST;
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        pathSum(root, targetSum, ans, new ArrayList<>());

        return ans;
    }
}