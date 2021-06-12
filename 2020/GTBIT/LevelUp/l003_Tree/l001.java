import java.util.ArrayList;
import java.util.List;

public class l001 {

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
        return root == null ? -(int) 1e9 : Math.max(Math.max(Maximum(root.left), Maximum(root.right)), root.val);
    }

    public static boolean find(TreeNode root, int data) {
        if (root == null)
            return false;
        if (root.val == data)
            return true;

        return find(root.left, data) || find(root.right, data);
    }

    public static ArrayList<TreeNode> NodeToRootPath(TreeNode root, int data) {
        if (root == null) {
            return new ArrayList<>();
        }

        if (root.val == data) {
            ArrayList<TreeNode> base = new ArrayList<>();
            base.add(root);
            return base;
        }

        ArrayList<TreeNode> left = NodeToRootPath(root.left, data);
        if (left.size() != 0) {
            left.add(root);
            return left;
        }

        ArrayList<TreeNode> right = NodeToRootPath(root.right, data);
        if (right.size() != 0) {
            right.add(root);
            return right;
        }

        return new ArrayList<>();
    }

    public static boolean NodeToRootPath(TreeNode root, int data, ArrayList<TreeNode> ans) {
        if (root == null)
            return false;
        if (root.val == data) {
            ans.add(root);
            return true;
        }

        boolean res = NodeToRootPath(root.left, data, ans) || NodeToRootPath(root.right, data, ans);
        if (res)
            ans.add(root);
        return res;
    }

    public static void rootToAllLeafPath(TreeNode root, ArrayList<Integer> smallAns,
            ArrayList<ArrayList<Integer>> ans) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            ArrayList<Integer> base = new ArrayList<>(smallAns);
            base.add(root.val);
            ans.add(base);
            return;
        }

        smallAns.add(root.val);

        rootToAllLeafPath(root.left, smallAns, ans);
        rootToAllLeafPath(root.right, smallAns, ans);

        smallAns.remove(smallAns.size() - 1);
    }

    public static ArrayList<ArrayList<Integer>> rootToAllLeafPath(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> smallAns = new ArrayList<Integer>();

        rootToAllLeafPath(root, smallAns, ans);
        return ans;
    }

    public static void singleChildNodes(TreeNode node, ArrayList<Integer> ans) {
        if (node == null || (node.left == null && node.right == null))
            return;

        if (node.left == null || node.right == null) {
            ans.add(node.val);
        }

        singleChildNodes(node.left, ans);
        singleChildNodes(node.right, ans);
    }

    public static int countSingleChildNodes(TreeNode node) {
        if (node == null || (node.left == null && node.right == null))
            return 0;

        int left = countSingleChildNodes(node.left);
        int right = countSingleChildNodes(node.right);

        int ans = left + right;
        if (node.left == null || node.right == null)
            ans++;

        return ans;
    }

    public void kDown(TreeNode root, TreeNode blockNode, int K, List<Integer> ans) {
        if (root == null || root == blockNode || K < 0)
            return;

        if (K == 0) {
            ans.add(root.val);
            return;
        }

        kDown(root.left, blockNode, K - 1, ans);
        kDown(root.right, blockNode, K - 1, ans);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        ArrayList<TreeNode> path = new ArrayList<>();
        NodeToRootPath(root, target.val, path);

        List<Integer> ans = new ArrayList<>();
        TreeNode blockNode = null;
        for (int i = 0; i < path.size(); i++) {
            if (K - i < 0)
                break;
            kDown(path.get(i), blockNode, K - i, ans);
            blockNode = path.get(i);
        }

        return ans;
    }

    public int distanceK_01(TreeNode root, TreeNode target, int k, ArrayList<Integer> ans) {
        if (root == null)
            return -1;
        if (root == target) {
            kDown(root, null, k, ans);
            return 1;
        }

        int ld = distanceK_01(root.left, target, k, ans);
        if (ld != -1) {
            kDown(root, root.left, k - ld, ans);
            return ld + 1;
        }

        int rd = distanceK_01(root.right, target, k, ans);
        if (rd != -1) {
            kDown(root, root.right, k - rd, ans);
            return rd + 1;
        }

        return -1;
    }

    public static void kdown(TreeNode root, int time, TreeNode blockNode, ArrayList<ArrayList<Integer>> ans) {
        if (root == null || root == blockNode)
            return;

        if (time == ans.size())
            ans.add(new ArrayList<>());
        ans.get(time).add(root.val);

        kdown(root.left, time + 1, blockNode, ans);
        kdown(root.right, time + 1, blockNode, ans);
    }

    public static int burningTree(TreeNode root, int target, ArrayList<ArrayList<Integer>> ans) {
        if (root == null)
            return -1;
        if (root.val == target) {
            kdown(root, 0, null, ans);
            return 1;
        }

        int ld = burningTree(root.left, target, ans);
        if (ld != -1) {
            kdown(root, ld, root.left, ans);
            return ld + 1;
        }

        int rd = burningTree(root.right, target, ans);
        if (rd != -1) {
            kdown(root, rd, root.right, ans);
            return rd + 1;
        }

        return -1;
    }

    public static void burningTree(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        burningTree(root, target, ans);
    }

}