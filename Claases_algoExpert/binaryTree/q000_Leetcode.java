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

    //236
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

    //863

    public void kDown(TreeNode root,TreeNode blockNode, int level, List<Integer> ans){
        if(level < 0 || root == null || root == blockNode) return;

        if(level == 0){
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
        for(int i = 0; i < list.size();i++){
           kDown(list.get(i), blockNode, k - i, ans);
           blockNode = list.get(i);
        }

        return ans;
    }
}