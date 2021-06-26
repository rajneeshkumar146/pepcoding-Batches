import java.util.ArrayList;

public class l005_traversal {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
        while (node.right != null && node.right != curr) {
            node = node.right;
        }

        return node;
    }

    public static ArrayList<Integer> morrisInOrderTraversal(TreeNode root) {
        TreeNode curr = root;
        ArrayList<Integer> ans = new ArrayList<>();
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightMostNode = getRightMostNode(left, curr);
                if (rightMostNode.right == null) { // thread creation area
                    rightMostNode.right = curr; // thread is created
                    curr = curr.left;
                } else { // thread destroy area
                    rightMostNode.right = null; // thread break
                    ans.add(curr.val);

                    curr = curr.right;
                }
            }
        }

        return ans;

    }

}