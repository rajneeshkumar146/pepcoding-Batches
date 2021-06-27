import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class l001_findSet {

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

    public static int maximum(TreeNode root) {
        return root == null ? -(int) 1e9 : Math.max(root.val, Math.max(maximum(root.left), maximum(root.right)));
    }

    public static int minimum(TreeNode root) {
        return root == null ? (int) 1e9 : Math.min(root.val, Math.min(minimum(root.left), minimum(root.right)));
    }

    public static boolean find(TreeNode root, int data) {
        if (root == null)
            return false;

        if (root.val == data)
            return true;

        return find(root.left, data) || find(root.right, data);
    }

    boolean nodeToRootPath_(TreeNode root, int data, ArrayList<TreeNode> ans) {

        if (root == null)
            return false;

        if (root.val == data) {
            ans.add(root);
            return true;
        }

        // if (nodeToRootPath_(root.left, data, ans))
        // {
        // ans.add(root);
        // return true;
        // }

        // if (nodeToRootPath_(root.right, data, ans))
        // {
        // ans.add(root);
        // return true;
        // }

        // return false;

        boolean res = nodeToRootPath_(root.left, data, ans) || nodeToRootPath_(root.right, data, ans);

        if (res)
            ans.add(root);
        return res;
    }

    ArrayList<TreeNode> nodeToRootPath_(TreeNode root, int data) {
        if (root == null) {
            return new ArrayList<>();
        }

        if (root.val == data) {
            ArrayList<TreeNode> base = new ArrayList<>();
            base.add(root);
            return base;
        }

        ArrayList<TreeNode> left = nodeToRootPath_(root.left, data);
        if (left.size() != 0) {
            left.add(root);
            return left;
        }

        ArrayList<TreeNode> right = nodeToRootPath_(root.right, data);
        if (right.size() != 0) {
            right.add(root);
            return right;
        }

        return new ArrayList<>();
    }

    public static void rootToAllLeafPath(TreeNode root, ArrayList<ArrayList<Integer>> ans,
            ArrayList<Integer> smallAns) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            ArrayList<Integer> base = new ArrayList<>(smallAns);
            base.add(root.val);
            ans.add(base);
            return;
        }

        smallAns.add(root.val);
        rootToAllLeafPath(root.left, ans, smallAns);
        rootToAllLeafPath(root.right, ans, smallAns);
        smallAns.remove(smallAns.size() - 1);
    }

    public static ArrayList<ArrayList<Integer>> rootToAllLeafPath(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> smallAns = new ArrayList<>();

        rootToAllLeafPath(root, ans, smallAns);
        return ans;
    }

    public static void exactlyOneChild(TreeNode root, ArrayList<Integer> ans) {
        if (root == null || (root.left == null && root.right == null))
            return;

        if (root.left == null || root.right == null) {
            ans.add(root.val);
        }

        exactlyOneChild(root.left, ans);
        exactlyOneChild(root.right, ans);
    }

    public static ArrayList<Integer> exactlyOneChild(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        exactlyOneChild(root);
        return ans;
    }

    public static int countExactlyOneChild(TreeNode node) {
        if (node == null || (node.left == null && node.right == null))
            return 0;

        int leftSinglechildCount = countExactlyOneChild(node.left);
        int rightSinglechildCount = countExactlyOneChild(node.right);

        int ans = leftSinglechildCount + rightSinglechildCount;
        if (node.left == null || node.right == null)
            ans++;

        return ans;
    }

    public void kdown(TreeNode root, int k, TreeNode block, List<Integer> ans) {
        if (root == null || k < 0 || root == block)
            return;

        if (k == 0) {
            ans.add(root.val);
            return;
        }

        kdown(root.left, k - 1, block, ans);
        kdown(root.right, k - 1, block, ans);
    }

    public int distanceK(TreeNode root, TreeNode target, int k, List<Integer> ans) {
        if (root == null)
            return -1;

        if (root == target) {
            kdown(root, k, null, ans);
            return 1;
        }

        int ld = distanceK(root.left, target, k, ans);
        if (ld != -1) {
            kdown(root, k - ld, root.left, ans);
            return ld + 1;
        }

        int rd = distanceK(root.right, target, k, ans);
        if (rd != -1) {
            kdown(root, k - rd, root.right, ans);
            return rd + 1;
        }

        return -1;
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        ArrayList<TreeNode> path = new ArrayList<>();
        nodeToRootPath_(root, target.val, path);

        List<Integer> ans = new ArrayList<>();
        TreeNode block = null;
        for (int i = 0; i < path.size(); i++) {
            kdown(path.get(i), k - i, block, ans);
            block = path.get(i);
        }
    }

    public static void burningTreeNode(TreeNode root, int time, TreeNode blockNode, ArrayList<ArrayList<Integer>> ans) {
        if (root == null || root == blockNode)
            return;
        if (time == ans.size()) // if(time == ans.size()) ans.push_back({});
            ans.add(new ArrayList<>());
        ans.get(time).add(root.val);

        burningTreeNode(root.left, time + 1, blockNode, ans);
        burningTreeNode(root.right, time + 1, blockNode, ans);

    }

    public static int burningTree(TreeNode root, int fireNode, ArrayList<ArrayList<Integer>> ans) {
        if (root == null)
            return -1;
        if (root.val == fireNode) {
            burningTreeNode(root, 0, null, ans);
            return 1;
        }

        int lt = burningTree(root.left, fireNode, ans);
        if (lt != -1) {
            burningTreeNode(root, lt, root.left, ans);
            return lt + 1;
        }

        int rt = burningTree(root.right, fireNode, ans);
        if (rt != -1) {
            burningTreeNode(root, lt, root.right, ans);
            return rt + 1;
        }

        return -1;
    }

    public static void burningTree(TreeNode root, int data) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        burningTree(root, data, ans);
    }

    // Node with water and fire.

    public static void burningTreeNodeWithWater(TreeNode root, int time, TreeNode blockNode, HashSet<Integer> waterSet,
            ArrayList<ArrayList<Integer>> ans) {
        if (root == null || root == blockNode || waterSet.contains(root.val))
            return;
        if (time == ans.size()) // if(time == ans.size()) ans.push_back({});
            ans.add(new ArrayList<>());
        ans.get(time).add(root.val);

        burningTreeNodeWithWater(root.left, time + 1, blockNode, waterSet, ans);
        burningTreeNodeWithWater(root.right, time + 1, blockNode, waterSet, ans);

    }

    public static int burningTreeWithWater(TreeNode root, int fireNode, HashSet<Integer> waterSet,
            ArrayList<ArrayList<Integer>> ans) {
        if (root == null)
            return -1;
        if (root.val == fireNode) {
            if (!waterSet.contains(root.val)) { // foor cpp : map.find(root->val) != map.end();
                burningTreeNodeWithWater(root, 0, null, waterSet, ans);
                return 1;
            }
            return -2; // fire node is present but it have water.
        }

        int lt = burningTreeWithWater(root.left, fireNode, waterSet, ans);
        if (lt > 0) {
            if (!waterSet.contains(root.val)) {
                burningTreeNodeWithWater(root, lt, root.left, waterSet, ans);
                return lt + 1;
            }
            return -2; // fire node is present but it have water.
        }

        if (lt == -2)
            return -2;

        int rt = burningTreeWithWater(root.right, fireNode, waterSet, ans);
        if (rt > 0) {
            if (!waterSet.contains(root.val)) {
                burningTreeNodeWithWater(root, rt, root.right, waterSet, ans);
                return rt + 1;
            }
            return -2; // fire node is present but it have water.
        }
        if (rt == -2)
            return -2;

        return -1;
    }

    public static void burningTreeWithWater(TreeNode root, int data) {
        HashSet<Integer> waterSet = new HashSet<>(); // unordered_set<int> map;
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        burningTreeWithWater(root, data, waterSet, ans);
        System.out.println(ans);
    }

}