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

    public static ArrayList<Integer> morrisPreOrderTraversal(TreeNode root) {
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
                    ans.add(curr.val);

                    curr = curr.left;
                } else { // thread destroy area
                    rightMostNode.right = null; // thread break

                    curr = curr.right;
                }
            }
        }

        return ans;
    }

    // validate BST==============================================

    public static boolean isValidBST(TreeNode root, TreeNode[] prev) {
        if (root == null)
            return true;

        if (!isValidBST(root.left, prev))
            return false;

        if (prev[0] != null && prev[0].val >= root.val)
            return false;
        prev[0] = root;

        if (!isValidBST(root.right, prev))
            return false;

        return true;
    }

    public static boolean isValidBST_02(TreeNode root) {
        long prev = -(long) 1e13;
        TreeNode curr = root;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                if (prev >= curr.val)
                    return false;

                prev = curr.val;
                curr = curr.right;
            } else {
                TreeNode rightMostNode = getRightMostNode(left, curr);
                if (rightMostNode.right == null) {
                    rightMostNode.right = curr;
                    curr = curr.left;
                } else {
                    rightMostNode.right = null;
                    if (prev >= curr.val)
                        return false;
                    prev = curr.val;

                    curr = curr.right;
                }
            }
        }

        return true;
    }

    public static int kthSmallest(TreeNode root, int k) {
        TreeNode curr = root;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                if (--k == 0)
                    return curr.val;
                curr = curr.right;
            } else {
                TreeNode rightMostNode = getRightMostNode(left, curr);
                if (rightMostNode.right == null) {
                    rightMostNode.right = curr;
                    curr = curr.left;
                } else {
                    rightMostNode.right = null;
                    if (--k == 0)
                        return curr.val;
                    curr = curr.right;
                }
            }
        }

        return -1;
    }

    public TreeNode getLeftMostNode(TreeNode node, TreeNode curr) {
        while (node.left != null && node.left != curr) {
            node = node.left;
        }

        return node;
    }

    public int kthLargest(TreeNode root, int k) {
        TreeNode curr = root;
        while (curr != null) {
            TreeNode right = curr.right;
            if (right == null) {
                if (--k == 0)
                    return curr.val;
                curr = curr.left;
            } else {
                TreeNode leftMostNode = getLeftMostNode(right, curr);
                if (leftMostNode.left == null) {
                    leftMostNode.left = curr;
                    curr = curr.right;
                } else {
                    leftMostNode.left = null;
                    if (--k == 0)
                        return curr.val;
                    curr = curr.left;
                }
            }
        }

        return -1;
    }

}