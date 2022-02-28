import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class morris {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void inOrder(TreeNode node) {
        if (node == null)
            return;

        inOrder(node.left);
        System.out.print(node.val + " ");
        inOrder(node.right);
    }

    public static void pushAllLeft(TreeNode node, LinkedList<TreeNode> stack) {
        while (node != null) {
            stack.addFirst(node);
            node = node.left;
        }
    }

    public static void inOrder_stack(TreeNode node) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        pushAllLeft(node, stack);

        while (stack.size() != 0) {
            TreeNode rn = stack.removeFirst();
            System.out.print(rn.val + " ");
            pushAllLeft(rn.right, stack);
        }
    }

    public static TreeNode rightMostNode(TreeNode node, TreeNode curr) {
        while (node.right != null && node.right != curr)
            node = node.right;

        return node;
    }

    public static List<Integer> inorder_Morris(TreeNode node) {
        List<Integer> ans = new ArrayList<>();
        TreeNode curr = node;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rmn = rightMostNode(left, curr);
                if (rmn.right == null) { // thread creation
                    rmn.right = curr;
                    curr = curr.left;
                } else { // destroy thread
                    rmn.right = null;
                    ans.add(curr.val);
                    curr = curr.right;
                }
            }
        }

        return ans;
    }

    public boolean isValidBST(TreeNode node) {
        TreeNode curr = node;
        TreeNode prev = null;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                if(prev != null && prev.val >= curr.val) return false;
                prev = curr;
                curr = curr.right;
            } else {
                TreeNode rmn = rightMostNode(left, curr);
                if (rmn.right == null) { // thread creation
                    rmn.right = curr;
                    curr = curr.left;
                } else { // destroy thread
                    rmn.right = null;
                    if(prev.val >= curr.val) return false;
                    prev = curr;
                    curr = curr.right;
                }
            }
        }

        return true;
    }

    public static List<Integer> preorder_Morris(TreeNode node) {
        List<Integer> ans = new ArrayList<>();

        return ans;
    }

    public static void main(String[] args) {

    }
}