import java.util.ArrayList;

public class BinaryTree {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        Node(int data) {
            this(data, null, null);
        }
    }

    public static void preOrder(Node root, ArrayList<Integer> ans) {
        if (root == null)
            return;

        ans.add(root.data);
        preOrder(root.left, ans);
        preOrder(root.right, ans);
    }

    public static void inOrder(Node root, ArrayList<Integer> ans) {
        if (root == null)
            return;

        inOrder(root.left, ans);
        ans.add(root.data);
        inOrder(root.right, ans);
    }

    public static void postOrder(Node root, ArrayList<Integer> ans) {
        if (root == null)
            return;

        postOrder(root.left, ans);
        postOrder(root.right, ans);
        ans.add(root.data);
    }

    public static int size(Node node) {
        if (node == null)
            return 0;

        int leftSize = size(node.left);
        int rightSize = size(node.right);

        return leftSize + rightSize + 1;

        // return node == null ? 0 : size(node.left) + size(node.right) + 1;
    }

    public static int sum(Node node) {
        if (node == null)
            return 0;

        int leftSum = sum(node.left);
        int rightSum = sum(node.right);

        return leftSum + rightSum + node.data;
        // return node == null ? 0 : sum(node.left) + sum(node.right) + node.data;
    }

    public static int max(Node node) {
        if (node == null)
            return -(int) 1e9;

        int leftMax = max(node.left);
        int rightMax = max(node.right);

        return Math.max(Math.max(leftMax, rightMax), node.data);

        // return node == null ? -(int) 1e9 : Math.max(node.data,
        // Math.max(max(node.left), max(node.right)));
    }

    public static int max2(Node node) {
        if (node == null)
            return -(int) 1e9;

        int maxEle = node.data;
        maxEle = Math.max(maxEle, max2(node.left));
        maxEle = Math.max(maxEle, max2(node.left));

        return maxEle;

        // return node == null ? -(int) 1e9 : Math.max(node.data,
        // Math.max(max(node.left), max(node.right)));
    }

    public static int min(Node node) {
        if (node == null)
            return -(int) 1e9;

        int leftMin = min(node.left);
        int rightMin = min(node.right);

        return Math.min(Math.min(leftMin, rightMin), node.data);

        // return node == null ? -(int) 1e9 : Math.max(node.data,
        // Math.min(min(node.left), min(node.right)));
    }

    // Height in Terms of Edges return -1 at null, Height in terms of Nodes return 0
    // at null
    public static int height(Node node) {
        return node == null ? -1 : Math.max(height(node.left), height(node.right)) + 1;
    }

    // https://practice.geeksforgeeks.org/problems/count-leaves-in-binary-tree/1
    public static int countLeaves(Node node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 1;

        return countLeaves(node.left) + countLeaves(node.right);
    }

    // Print the nodes having exactly one child in a Binary tree
    public static void exactlyOneChild(Node node, ArrayList<Integer> ans) {
        if (node == null || (node.left == null && node.right == null))
            return;

        if (node.left == null || node.right == null)
            ans.add(node.data);

        exactlyOneChild(node.left, ans);
        exactlyOneChild(node.right, ans);

    }

    public static int countExactlyOneChild(Node node) {
        if (node == null || (node.left == null && node.right == null))
            return 0;

        int left = countExactlyOneChild(node.left);
        int right = countExactlyOneChild(node.right);
        int sum = left + right;
        if (node.left == null || node.right == null)
            sum += 1;
        return sum;
    }

    public static boolean findData(Node node, int data) {
        if (node == null)
            return false;

        if (node.data == data)
            return true;

        return findData(node.left, data) || findData(node.right, data);
    }

    public static boolean nodeToRootPath(Node node, int data, ArrayList<Node> ans) {
        if (node == null)
            return false;

        if (node.data == data) {
            ans.add(node);
            return true;
        }
        boolean res = nodeToRootPath(node.left, data, ans) || nodeToRootPath(node.right, data, ans);
        if (res)
            ans.add(node);

        return res;
    }

    public static ArrayList<Node> nodeToRootPath(Node root, int data) {
        ArrayList<Node> ans = new ArrayList<>();
        nodeToRootPath(root, data, ans);
        return ans;
    }

    public static ArrayList<Node> nodeToRootPath02_(Node node, int data) {
        if (node == null)
            return null;

        if (node.data == data) {
            ArrayList<Node> list = new ArrayList<>();
            list.add(node);
            return list;
        }

        ArrayList<Node> left = nodeToRootPath02_(node.left, data);
        if (left != null) {
            left.add(node);
            return left;
        }

        ArrayList<Node> right = nodeToRootPath02_(node.right, data);
        if (right != null) {
            right.add(node);
            return right;
        }

        return null;
    }

    public static ArrayList<Node> nodeToRootPath02(Node node, int data) {
        ArrayList<Node> ans = nodeToRootPath02_(node, data);
        return ans != null ? ans : new ArrayList<>();
    }

    public static void KLevelsDown(Node node, int k, Node block, ArrayList<Integer> ans) {
        if (node == null || k < 0 || node == block)
            return;

        if (k == 0) {
            ans.add(node.data);
            return;
        }

        KLevelsDown(node.left, k - 1, block, ans);
        KLevelsDown(node.right, k - 1, block, ans);
    }

    public static ArrayList<Integer> kaway(Node node, int data, int k) {
        ArrayList<Node> list = new ArrayList<>();
        nodeToRootPath(node, data, list);

        ArrayList<Integer> ans = new ArrayList<>();
        Node block = null;
        for (int i = 0; i < list.size(); i++) {
            KLevelsDown(list.get(i), k - i, block, ans);
            block = list.get(i);
        }

        return ans;
    }

    public static int kaway2(Node node, int data, int k, ArrayList<Integer> ans) {
        if (node == null)
            return -1;

        if (node.data == data) {
            KLevelsDown(node, k, null, ans);
            return 1;
        }

        int ld = kaway2(node.left, data, k, ans);
        if (ld != -1) {
            KLevelsDown(node, k - ld, node.left, ans);
            return ld + 1;
        }

        int rd = kaway2(node.right, data, k, ans);
        if (rd != -1) {
            KLevelsDown(node, k - rd, node.right, ans);
            return rd + 1;
        }

        return -1;
    }

    static Node prev = null;

    public static boolean isBST(Node node) {
        if (node == null)
            return true;

        if (!isBST(node.left))
            return false;

        if (prev != null && prev.data > node.data)
            return false;
        prev = node;

        if (!isBST(node.right))
            return false;
        return true;
    }

    public static class isBSTPair {
        boolean isBST = true;
        int maxEle = -(int) 1e9;
        int minEle = (int) 1e9;
    }

    public static isBSTPair isBST_02(Node node) {
        if (node == null)
            return new isBSTPair();

        isBSTPair left = isBST_02(node.left);
        if (!left.isBST)
            return left;

        isBSTPair right = isBST_02(node.right);
        if (!right.isBST)
            return right;

        isBSTPair self = new isBSTPair();
        self.isBST = false;

        if (left.maxEle < node.data && right.minEle > node.data) {
            self.maxEle = Math.max(right.maxEle, node.data);
            self.minEle = Math.min(left.minEle, node.data);
            self.isBST = true;
        }

        return self;
    }

    public static boolean isBal(Node node) {
        if (node == null)
            return true;

        if (!isBal(node.left))
            return false;
        if (!isBal(node.right))
            return false;

        int lh = height(node.left);
        int rh = height(node.right);

        int diff = Math.abs(lh - rh);

        if (diff > 1)
            return false;

        return true;
    }

    public static class balPair {
        int height = -1;
        boolean isBal = true;
    }

    public static balPair isBal2(Node node) {
        if (node == null)
            return new balPair();

        balPair lPair = isBal2(node.left);
        if (!lPair.isBal)
            return lPair;

        balPair rPair = isBal2(node.right);
        if (!rPair.isBal)
            return rPair;

        balPair myAns = new balPair();
        if (Math.abs(lPair.height - rPair.height) > 1) {
            myAns.isBal = false;
            return myAns;
        }

        myAns.height = Math.max(lPair.height, rPair.height) + 1;
        return myAns;
    }

    public int isBal3(Node node) {
        if (node == null)
            return -1;

        int lh = isBal3(node.left);
        if (lh == -2)
            return lh;

        int rh = isBal3(node.right);
        if (rh == -2)
            return rh;

        if (Math.abs(lh - rh) > 1) {
            return -2;
        }

        return Math.max(lh, rh) + 1;
    }

    public boolean isBalanced(Node root) {

        int ans = isBal3(root);
        return ans != -2 ? true : false;
    }

    public static class tiltPair {
        int tiltSF = 0;
        int sum = 0;
    }

    public tiltPair findTilt(TreeNode root) {
        if (root == null)
            return new tiltPair();

        tiltPair left = findTilt(root.left);
        tiltPair right = findTilt(root.right);

        tiltPair myAns = new tiltPair();

        myAns.tiltSF = left.tiltSF + right.tiltSF + Math.abs(left.sum - right.sum);
        myAns.sum = left.sum + right.sum + root.val;
        return myAns;
    }

    // {tiltSF,sum}
    public int[] findTilt2(TreeNode root) {
        if (root == null)
            return new int[2];

        int[] left = findTilt2(root.left);
        int[] right = findTilt2(root.right);

        int[] myAns = new int[2];
        myAns[0] = left[0] + right[0] + Math.abs(left[1] - right[1]);
        myAns[1] = left[1] + right[1] + root.val;
        return myAns;
    }

    public int diameterOfBinaryTree_(TreeNode root) {
        if (root == null)
            return 0;

        int ld = diameterOfBinaryTree_(root.left);
        int rd = diameterOfBinaryTree_(root.right);

        int lh = height(root.left);
        int rh = height(root.right);

        return Math.max(Math.max(ld, rd), lh + rh + 2);
    }

    // {diameter, height}
    public int[] diameterOfBinaryTree_02(TreeNode root) {
        if (root == null)
            return new int[] { 0, -1 };

        int[] ld = diameterOfBinaryTree_02(root.left);
        int[] rd = diameterOfBinaryTree_02(root.right);

        int[] myAns = new int[2];
        myAns[0] = Math.max(Math.max(ld[0], rd[0]), ld[1] + rd[1] + 2);
        myAns[1] = Math.max(ld[1], rd[1]) + 1;

        return myAns;
    }

    int diameter = 0;

    public int diameterOfBinaryTree_03(TreeNode root) {
        if (root == null)
            return -1;

        int ld = diameterOfBinaryTree_03(root.left);
        int rd = diameterOfBinaryTree_03(root.right);

        diameter = Math.max(diameter, ld + rd + 2);
        return Math.max(ld, rd) + 1;
    }

    public static class lBSTPair {
        boolean isBST = true;
        int max = -(int) 1e9;
        int min = (int) 1e9;

        int MaxSize = 0;
        Node MaxBSTNode = null;
    }

    public static lBSTPair largestBST(Node node) {
        if (node == null)
            return new lBSTPair();

        lBSTPair left = largestBST(node.left);
        lBSTPair right = largestBST(node.right);

        lBSTPair myAns = new lBSTPair();
        if (left.isBST && right.isBST && left.max < node.data && node.data < right.min) {
            myAns.isBST = true;
            myAns.min = Math.min(left.min, node.data);
            myAns.max = Math.max(right.max, node.data);

            myAns.MaxSize = left.MaxSize + right.MaxSize + 1;
            myAns.MaxBSTNode = node;
        } else {
            myAns.isBST = false;
            myAns.MaxSize = Math.max(left.MaxSize, right.MaxSize);
            myAns.MaxBSTNode = left.MaxSize > right.MaxSize ? left.MaxBSTNode : right.MaxBSTNode;
        }

        return myAns;
    }

    public Node lowestCommonAncestor(Node root, int p, int q) {
        ArrayList<Node> list1 = nodeToRootPath(root, p);
        ArrayList<Node> list2 = nodeToRootPath(root, q);

        int i = list1.size() - 1;
        int j = list2.size() - 1;

        Node LCA = null;
        while (i >= 0 && j >= 0) {
            if (list1.get(i) != list2.get(j))
                break;
            LCA = list2.get(j);
            i--;
            j--;

        }

        return LCA;
    }

}