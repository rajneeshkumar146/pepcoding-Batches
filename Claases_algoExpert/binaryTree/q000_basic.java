import java.util.*;
import java.util.function.Function;

import javax.swing.tree.TreeNode;

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

    public class balPair {
        int height = -1;
        boolean isBal = true;
    }

    public balPair heightBalancedBinaryTree_(BinaryTree root) {
        if (root == null) {
            return new balPair();
        }

        balPair lp = heightBalancedBinaryTree_(root.left);
        balPair rp = heightBalancedBinaryTree_(root.right);

        balPair myBal = new balPair();
        myBal.isBal = false;
        myBal.height = Math.max(lp.height, rp.height) + 1;

        if (lp.isBal && rp.isBal && Math.abs(lp.height - rp.height) <= 1) {
            myBal.isBal = true;
        }

        return myBal;
    }

    public boolean heightBalancedBinaryTree(BinaryTree root) {
        return heightBalancedBinaryTree_(root).isBal;
    }

    public class allSolPair {
        int height = -1;
        int size = 0;
        int max = -(int) 1e9;

        int ceil = (int) 1e9;
        int floor = -(int) 1e9;

        BinaryTree succ = null;
        BinaryTree pred = null;
        BinaryTree prev = null;
    }

    public void allSol(BinaryTree root, BinaryTree node, int level, allSolPair pair) {
        if (root == null)
            return;

        pair.size++;
        pair.max = Math.max(pair.max, root.value);
        pair.height = Math.max(pair.height, level);

        if (root.value > node.value)
            pair.ceil = Math.min(pair.ceil, root.value);
        if (root.value < node.value)
            pair.floor = Math.max(pair.floor, root.value);

        allSol(root.left, node, level + 1, pair);

        if (pair.prev != null && pair.prev == node)
            pair.succ = root;

        if (root == node)
            pair.pred = pair.prev;

        pair.prev = root;

        allSol(root.right, node, level + 1, pair);
    }

    public BinaryTree findSuccessor(BinaryTree tree, BinaryTree node) {
        allSolPair pair = new allSolPair();
        allSol(tree, node, 0, pair);
        return pair.succ;
    }

    public static class flatternPair {
        BinaryTree head = null, prev = null;
    }

    public static void flattenBinaryTree(BinaryTree root, flatternPair pair) {
        if (root == null)
            return;

        flattenBinaryTree(root.left, pair);

        if (pair.prev == null)
            pair.head = root;
        else {
            root.left = pair.prev;
            pair.prev.right = root;
        }

        pair.prev = root;

        flattenBinaryTree(root.right, pair);
    }

    public static BinaryTree flattenBinaryTree(BinaryTree root) {
        flatternPair pair = new flatternPair();
        flattenBinaryTree(root, pair);
        return pair.head;
    }

    // max Path Sum
    private static int NTN_maxPathSum = -(int) 1e9;

    private static int max(int... arr) {
        int max = arr[0];
        for (int ele : arr)
            max = Math.max(max, ele);

        return max;
    }

    private static int maxPathSum_(BinaryTree root) {
        if (root == null)
            return 0;

        int lrtn = maxPathSum_(root.left); // left root to node
        int rrtn = maxPathSum_(root.right); // right root to node

        int rootToNode = Math.max(lrtn, rrtn) + root.value;

        NTN_maxPathSum = max(NTN_maxPathSum, rootToNode, root.value, lrtn + root.value + rrtn);

        return max(rootToNode, root.value);
    }

    public static int maxPathSum(BinaryTree tree) {
        NTN_maxPathSum = -(int) 1e9;
        maxPathSum_(tree);
        return NTN_maxPathSum;
    }

    // https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/

    // right Sibling Tree
    public static BinaryTree rightSiblingTree(BinaryTree root) {
        if (root == null)
            return root;

        // addFirst, removeFirst
        LinkedList<BinaryTree> que = new LinkedList<>();
        que.addLast(root);
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                BinaryTree node = que.removeFirst();
                if (node == null)
                    continue;
                que.addLast(node.left != null ? node.left : null);
                que.addLast(node.right != null ? node.right : null);

                node.right = size != 0 ? que.getFirst() : null;
            }

        }
        return root;
    }

    // Leaf Traversal

    private void getAllLeafs(BinaryTree root, ArrayList<Integer> list) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            list.add(root.value);
            return;
        }

        getAllLeafs(root.left, list);
        getAllLeafs(root.right, list);
    }

    public boolean compareLeafTraversal(BinaryTree tree1, BinaryTree tree2) {
        ArrayList<Integer> list1 = new ArrayList<>();
        getAllLeafs(tree1, list1);

        ArrayList<Integer> list2 = new ArrayList<>();
        getAllLeafs(tree2, list2);

        if (list1.size() != list2.size())
            return false;
        else {
            int i = 0;
            while (i < list1.size()) {
                if (list1.get(i) != list2.get(i))
                    return false;
                i++;
            }

        }

        return true;
    }

    // Node Depth

    // private static int depthSum = 0;

    // private static void nodeDepths_(BinaryTree root, int level) {
    // if (root == null)
    // return;
    // depthSum += level;
    // nodeDepths_(root.left, level + 1);
    // nodeDepths_(root.right, level + 1);
    // }

    // public static int nodeDepths(BinaryTree root) {
    // depthSum = 0;
    // nodeDepths_(root, 0);
    // return depthSum;
    // }

    private static class depthPair1 {
        int depth = 0;
        int size = 0;

    }

    private static depthPair1 nodeDepths_(BinaryTree root) {
        if (root == null)
            return new depthPair1();

        depthPair1 lp = nodeDepths_(root.left);
        depthPair1 rp = nodeDepths_(root.right);

        depthPair1 mypair = new depthPair1();
        mypair.depth = (lp.depth + lp.size) + (rp.depth + rp.size);
        mypair.size = lp.size + rp.size + 1;

        return mypair;
    }

    public static int nodeDepths(BinaryTree root) {
        return nodeDepths_(root).depth;
    }

    // all Kind of depth
    private static class depthPair2 {
        int depth = 0;
        int size = 0;
        int totalDepthSum = 0;
    }

    private static depthPair2 allKindsOfNodeDepths_(BinaryTree root) {
        if (root == null)
            return new depthPair2();

        depthPair2 lp = allKindsOfNodeDepths_(root.left);
        depthPair2 rp = allKindsOfNodeDepths_(root.right);

        depthPair2 mypair = new depthPair2();
        mypair.depth = (lp.depth + lp.size) + (rp.depth + rp.size);
        mypair.size = lp.size + rp.size + 1;
        mypair.totalDepthSum = lp.totalDepthSum + rp.totalDepthSum + mypair.depth;

        return mypair;
    }

    public static int allKindsOfNodeDepths(BinaryTree root) {
        return allKindsOfNodeDepths_(root).totalDepthSum;
    }

    //iterative Inorder Traversal

    // for stack -> addFirst, removeFirst
    private static void insertAllLeft(BinaryTree node, LinkedList<BinaryTree> st) {
        BinaryTree curr = node;
        while (curr != null) {
            st.addFirst(curr);
            curr = curr.left;
        }
    }

    public static List<BinaryTree> inorderTraversal(BinaryTree root) {
        LinkedList<BinaryTree> st = new LinkedList<>();
        insertAllLeft(root, st);

        List<BinaryTree> ans = new ArrayList<>();
        while (st.size() != 0) {
            BinaryTree node = st.removeFirst();
            ans.add(node);
            insertAllLeft(node.right, st);
        }

        return ans;
    }

    public static void iterativeInOrderTraversal(
            BinaryTree tree, Function<BinaryTree, Void> callback) {

        List<BinaryTree> ans = inorderTraversal(tree);
        for (BinaryTree ele : ans)
            callback.apply(ele);

    }

    public static void iterativeInOrderTraversal_2(
            BinaryTree tree, Function<BinaryTree, Void> callback) {

        BinaryTree previousNode = null, currNode = tree;
        while (currNode != null) {
            BinaryTree nextNode = null;
            if (previousNode == null || currNode.parent == previousNode) {
                if (currNode.left != null) {
                    nextNode = currNode.left;
                } else {
                    callback.apply(currNode);
                    nextNode = currNode.right != null ? currNode.right : currNode.parent;
                }
            } else if (currNode.left == previousNode) { // we already traversed left subtree
                callback.apply(currNode);
                nextNode = currNode.right != null ? currNode.right : currNode.parent;
            } else {
                nextNode = currNode.parent;
            }

            previousNode = currNode;
            currNode = nextNode;
        }
    }

    // find nodes distance k
    public void kDown(BinaryTree root, BinaryTree blockNode, int level, ArrayList<Integer> ans) {
        if (level < 0 || root == null || root == blockNode)
            return;

        if (level == 0) {
            ans.add(root.value);
            return;
        }

        kDown(root.left, blockNode, level - 1, ans);
        kDown(root.right, blockNode, level - 1, ans);
    }

    public boolean nodeToRootPath_(BinaryTree root, int data, ArrayList<BinaryTree> ans) {
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

    public ArrayList<Integer> findNodesDistanceK(BinaryTree root, int target, int k) {
        ArrayList<BinaryTree> list = new ArrayList<>();
        nodeToRootPath_(root, target, list);

        ArrayList<Integer> ans = new ArrayList<>();
        BinaryTree blockNode = null;
        for (int i = 0; i < list.size(); i++) {
            kDown(list.get(i), blockNode, k - i, ans);
            blockNode = list.get(i);
        }

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
