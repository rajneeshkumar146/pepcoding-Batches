import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

public class question {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public int size(TreeNode node) {
        return node == null ? 0 : size(node.left) + size(node.right) + 1;
    }

    // Edges, for Nodes : return node == null ? 0
    public int height(TreeNode node) {
        return node == null ? -1 : Math.max(height(node.left), height(node.right)) + 1;
    }

    public int maximum(TreeNode node) {
        if (node == null)
            return -(int) 1e9;
        int lmv = maximum(node.left); // left maximum value
        int rmv = maximum(node.right); // right maximum value

        return Math.max(Math.max(lmv, rmv), node.val);
    }

    public boolean find(TreeNode node, int data) {
        if (node == null)
            return false;
        if (node.val == data) {
            return true;
        }

        return find(node.left, data) || find(node.right, data);
    }

    // ArrayList<Node>
    public boolean rootToNodePath(TreeNode node, TreeNode data, ArrayList<TreeNode> ans) {
        if (node == null)
            return false;
        if (node == data) {
            ans.add(node);
            return true;
        }

        boolean res = rootToNodePath(node.left, data, ans) || rootToNodePath(node.right, data, ans);
        if (res)
            ans.add(node);

        return res;

    }

    public ArrayList<TreeNode> rootToNodePath(TreeNode node, TreeNode data) {
        if (node == null)
            return new ArrayList<>();

        if (node == data) {
            ArrayList<TreeNode> base = new ArrayList<>();
            base.add(node);
            return base;
        }

        ArrayList<TreeNode> left = rootToNodePath(node.left, data);
        if (left.size() > 0) {
            left.add(node);
            return left;
        }

        ArrayList<TreeNode> right = rootToNodePath(node.right, data);
        if (right.size() > 0) {
            right.add(node);
            return right;
        }

        return new ArrayList<>();
    }

    // 236
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> list1 = new ArrayList<>();
        ArrayList<TreeNode> list2 = new ArrayList<>();

        rootToNodePath(root, p, list1);
        rootToNodePath(root, q, list2);

        int i = list1.size() - 1;
        int j = list2.size() - 1;

        TreeNode LCA = null;

        while (i >= 0 && j >= 0) {
            if (list1.get(i) != list2.get(j)) // cpp : list1[i] == list2[j]
                break;

            LCA = list1.get(i);
            i--;
            j--;
        }

        return LCA;
    }

    public void printKDown(TreeNode node, TreeNode block, int depth, List<Integer> ans) {
        if (node == null || depth < 0 || node == block)
            return;

        if (depth == 0) {
            ans.add(node.val);
            return;
        }

        printKDown(node.left, block, depth - 1, ans);
        printKDown(node.right, block, depth - 1, ans);
    }

    // 863
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        ArrayList<TreeNode> list = new ArrayList<>();
        rootToNodePath(root, target, list);

        List<Integer> ans = new ArrayList<>();
        TreeNode blockNode = null;

        for (int i = 0; i < list.size(); i++) {
            printKDown(list.get(i), blockNode, K - i, ans);
            blockNode = list.get(i);
        }
        return ans;
    }

    // better solution for k far
    public int distanceK2(TreeNode node, TreeNode target, int K, List<Integer> ans) {
        if (node == null)
            return -1;

        if (node == target) {
            printKDown(node, null, K, ans);
            return 1;
        }

        int lans = distanceK2(node.left, target, K, ans);
        if (lans != -1) {
            printKDown(node, node.left, K - lans, ans);
            return lans + 1;
        }

        int rans = distanceK2(node.right, target, K, ans);
        if (rans != -1) {
            printKDown(node, node.right, K - rans, ans);
            return rans + 1;
        }

        return -1;
    }

    public List<Integer> distanceK2(TreeNode root, TreeNode target, int K) {
        List<Integer> ans = new ArrayList<>();
        distanceK2(root, target, K, ans);
        return ans;
    }

    public static int rootToNodeDistance(TreeNode node, TreeNode data) {
        if (node == null)
            return -1;
        if (node == data)
            return 0;

        int lans = rootToNodeDistance(node.left, data);
        if (lans != -1)
            return lans + 1;

        int rans = rootToNodeDistance(node.right, data);
        if (rans != -1)
            return rans + 1;

        return -1;
    }

    // https://www.geeksforgeeks.org/burn-the-binary-tree-starting-from-the-target-node/

    public int diameterOfBinaryTree_01(TreeNode root) {
        if (root == null)
            return -1;
        int leftTreeDia = diameterOfBinaryTree_01(root.left);
        int rightTreeDia = diameterOfBinaryTree_01(root.right);

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        return Math.max(Math.max(leftTreeDia, rightTreeDia), leftHeight + rightHeight + 2);
    }

    // {dia,height}
    public int[] diameterOfBinaryTree_02(TreeNode root) {
        if (root == null)
            return new int[] { -1, -1 };
        int[] leftAns = diameterOfBinaryTree_02(root.left);
        int[] rightAns = diameterOfBinaryTree_02(root.right);

        int[] ans = new int[2];
        ans[0] = Math.max(Math.max(leftAns[0], rightAns[0]), leftAns[1] + rightAns[1] + 2);
        ans[1] = Math.max(leftAns[1], rightAns[1]) + 1;

        return ans;
    }

    int maxDia = 0;

    public int diameterOfBinaryTree_03(TreeNode root) {
        if (root == null)
            return -1;
        int lh = diameterOfBinaryTree_03(root.left);
        int rh = diameterOfBinaryTree_03(root.right);

        maxDia = Math.max(maxDia, lh + rh + 2);

        return Math.max(lh, rh) + 1;
    }

    public int diameterOfBinaryTree_03(TreeNode root, int[] ans) {
        if (root == null)
            return -1;
        int lh = diameterOfBinaryTree_03(root.left);
        int rh = diameterOfBinaryTree_03(root.right);

        ans[0] = Math.max(ans[0], lh + rh + 2);

        return Math.max(lh, rh) + 1;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        // return diameterOfBinaryTree_01(root);
        // return diameterOfBinaryTree_02(root)[0];
        int[] ans = new int[1];
        diameterOfBinaryTree_03(root, ans);
        return ans[0];
    }

    // 112
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null)
            return (targetSum - root.val == 0);

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // 113
    public void pathSum(TreeNode root, int tar, List<List<Integer>> res, List<Integer> smallAns) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            if (tar - root.val == 0) {
                ArrayList<Integer> base = new ArrayList<>(smallAns);
                base.add(root.val);
                res.add(base);
            }

            return;
        }

        smallAns.add(root.val);

        pathSum(root.left, tar - root.val, res, smallAns);
        // print("hello");
        pathSum(root.right, tar - root.val, res, smallAns);

        smallAns.remove(smallAns.size() - 1);
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        pathSum(root, targetSum, res, new ArrayList<>());
        return res;

    }

    // https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/
    int maxLeafToLeaf = -(int) 1e9;

    int maxPathSum_(Node root) {
        if (root == null)
            return -(int) 1e9;
        if (root.left == null && root.right == null)
            return root.data;

        int leftNodeToLeafMaxSum = maxPathSum_(root.left);
        int rightNodeToLeafMaxSum = maxPathSum_(root.right);

        if (root.left != null && root.right != null)
            maxLeafToLeaf = Math.max(maxLeafToLeaf, leftNodeToLeafMaxSum + root.data + rightNodeToLeafMaxSum);

        return Math.max(leftNodeToLeafMaxSum, rightNodeToLeafMaxSum) + root.data;
    }

    int maxPathSum(Node root) {
        if (root == null)
            return Integer.MIN_VALUE;

        maxPathSum_(root);
        return maxLeafToLeaf;

    }

    int maxNTN = -(int) 1e9;

    public int maxPathSum_(TreeNode node) {
        if (node == null)
            return 0;

        int leftMaxPathSum = maxPathSum_(node.left);
        int rightMaxPathSum = maxPathSum_(node.right);

        int maxSumTillRoot = Math.max(leftMaxPathSum, rightMaxPathSum) + node.val;
        maxNTN = Math.max(Math.max(maxNTN, maxSumTillRoot),
                Math.max(node.val, leftMaxPathSum + node.val + rightMaxPathSum));

        return Math.max(maxSumTillRoot, node.val);

    }

    public int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;

        maxPathSum(root);
        return maxNTN;
    }

    public class maxNodeToNodePair {
        int NTN_sum = -(int) 1e9; // Node to Node sum
        int NTR_sum = 0; // Node to root sum

        maxNodeToNodePair(int NTN_sum, int NTR_sum) {
            this.NTN_sum = NTN_sum;
            this.NTR_sum = NTR_sum;
        }

        maxNodeToNodePair() {

        }
    }

    public int maxValue(int... arr) {
        int max = arr[0];
        for (int ele : arr) {
            max = Math.max(max, ele);
        }

        return max;
    }

    // {nodeToNode, rootToNode}
    public maxNodeToNodePair maxPathSum2_(TreeNode node) {
        if (node == null)
            return new maxNodeToNodePair();

        maxNodeToNodePair lpair = maxPathSum2_(node.left);
        maxNodeToNodePair rpair = maxPathSum2_(node.right);

        maxNodeToNodePair myAns = new maxNodeToNodePair();

        myAns.NTN_sum = maxValue(lpair.NTN_sum, rpair.NTN_sum, node.val, lpair.NTR_sum + node.val,
                rpair.NTR_sum + node.val, lpair.NTR_sum + node.val + rpair.NTR_sum);
        myAns.NTR_sum = maxValue(node.val, lpair.NTR_sum + node.val, rpair.NTR_sum + node.val);

        return myAns;
    }

    public int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;

        return maxPathSum2_(root).NTN_sum;
    }

    // 98
    public class BSTpair {
        boolean isBST = true;
        long max = -(long) 1e13;
        long min = (long) 1e13;

        BSTpair(long max, long min, boolean isBST) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
        }

        BSTpair() {

        }

    }

    public BSTpair isValidBST_(TreeNode root) {
        if (root == null)
            return new BSTpair();

        BSTpair lres = isValidBST_(root.left);
        BSTpair rres = isValidBST_(root.right);

        BSTpair myRes = new BSTpair();
        myRes.isBST = lres.isBST && rres.isBST && lres.max < root.val && root.val < rres.min;
        if (!myRes.isBST)
            return myRes;

        myRes.max = Math.max(rres.max, root.val);
        myRes.min = Math.min(lres.min, root.val);

        return myRes;
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST_(root).isBST;
    }

    long prevBSTNode = -(long) 1e13;

    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        if (!isValidBST(root.left))
            return false;

        if (root.val <= prevBSTNode) {
            return false;
        }

        prevBSTNode = root.val;
        if (!isValidBST(root.right))
            return false;

        return true;
    }

    // 99
    TreeNode a = null, b = null, prev = null;

    public boolean recoverTree_(TreeNode root) {

        if (root == null)
            return true;

        if (!recoverTree_(root.left))
            return false;

        if (prev != null && prev.val > root.val) {
            b = root;
            if (a == null)
                a = prev;
            else
                return false;
        }

        prev = root;
        if (!recoverTree_(root.right))
            return false;

        return true;

    }

    public void recoverTree(TreeNode root) {
        recoverTree_(root);
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }

    public static class allSolPair {
        TreeNode prev = null;
        TreeNode pred = null;
        TreeNode succ = null;

        int ceil = (int) 1e8;
        int floor = -(int) 1e8;
    }

    public static void allSolution(TreeNode node, int data, allSolPair pair) {
        if (node == null)
            return;

        if (node.val < data)
            pair.floor = Math.max(pair.floor, node.val);

        if (node.val > data)
            pair.ceil = Math.min(pair.ceil, node.val);

        allSolution(node.left, data, pair);

        if (node.val == data && pair.pred == null)
            pair.pred = pair.prev;
        if (pair.prev != null && pair.prev.val == data && pair.succ == null)
            pair.succ = node;

        pair.prev = node;

        allSolution(node.right, data, pair);
    }

    // 173
    class BSTIterator {
        LinkedList<TreeNode> st = new LinkedList<>(); // addFirst(), removeFirst();

        public BSTIterator(TreeNode root) {
            addAllLeft(root);
        }

        public void addAllLeft(TreeNode node) {
            if (node == null)
                return;

            TreeNode curr = node;
            while (curr != null) {
                st.addFirst(curr);
                curr = curr.left;
            }

        }

        public int next() {
            TreeNode rn = st.removeFirst();
            addAllLeft(rn.right);
            return rn.val;
        }

        public boolean hasNext() {
            return st.size() != 0;
        }
    }

    // 510
    public Node inorderSuccessor(Node node) {

        if (node.right != null) {
            node = node.right;
            while (node.left != null)
                node = node.left;

            return node;
        } else {
            while (node != null) {
                if (node.parent != null && node.parent.left == node) {
                    return node.parent;
                }
                node = node.parent;
            }

            return null;
        }
    }

    // T -> avg : O(nlogn), worst : O(n^2).
    // psi = pre starting index, isi = in-order starting index.
    public TreeNode preInTree(int[] preorder, int psi, int pei, int[] inorder, int isi, int iei) {
        if (psi > pei)
            return null;

        TreeNode node = new TreeNode(preorder[psi]);

        int idx = isi;
        while (inorder[idx] != preorder[psi])
            idx++;

        int tnel = idx - isi; // total no of elements.

        node.left = preInTree(preorder, psi + 1, psi + tnel, inorder, isi, idx - 1);
        node.right = preInTree(preorder, psi + tnel + 1, pei, inorder, idx + 1, iei);

        return node;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return preInTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    // 106
    public TreeNode postInTree(int[] post, int psi, int pei, int[] in, int isi, int iei) {
        if (psi > pei)
            return null;

        TreeNode node = new TreeNode(post[pei]);
        int idx = isi;
        while (in[idx] != post[pei])
            idx++;

        int tnel = idx - isi;

        node.left = postInTree(post, psi, psi + tnel - 1, in, isi, idx - 1);
        node.right = postInTree(post, psi + tnel, pei - 1, in, idx + 1, iei);

        return node;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = postorder.length;
        return postInTree(postorder, 0, n - 1, inorder, 0, n - 1);
    }

    // 889
    public TreeNode postPreTree(int[] post, int ppsi, int ppei, int[] pre, int psi, int pei) {
        if (psi > pei)
            return null;

        TreeNode node = new TreeNode(pre[psi]);

        if (psi == pei)
            return node;

        int idx = ppsi;
        while (post[idx] != pre[psi + 1])
            idx++;

        int tnel = idx - ppsi + 1;
        node.left = postPreTree(post, ppsi, idx, pre, psi + 1, psi + tnel);
        node.right = postPreTree(post, idx + 1, ppei - 1, pre, psi + tnel + 1, pei);

        return node;
    }

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        int n = post.length;

        return postPreTree(post, 0, n - 1, pre, 0, n - 1);
    }

    // 114, T -> O(n^2)
    public TreeNode getTail(TreeNode root) {
        if (root == null)
            return null;
        TreeNode curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }

        return curr;

    }

    public void flatten(TreeNode root) {
        if (root == null)
            return;

        flatten(root.left);
        flatten(root.right);

        TreeNode tail = getTail(root.left);
        if (tail != null) {
            tail.right = root.right;
            root.right = root.left;
            root.left = null;
        }
    }

    // T -> O(n)
    public TreeNode flattern_(TreeNode node) {
        if (node == null || (node.left == null && node.right == null))
            return node;

        TreeNode leftTail = flattern_(node.left);
        TreeNode rightTail = flattern_(node.right);

        if (leftTail != null) {
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
        }

        return rightTail != null ? rightTail : leftTail;
    }

    public void flatten(TreeNode root) {
        if (root == null)
            return;
        flattern_(root);
    }

    Node dummy = new Node(-1);
    Node prev = dummy;

    public void treeToDoublyList_(Node root) {
        if (root == null)
            return;

        treeToDoublyList_(root.left);

        prev.right = root;
        root.left = prev;

        prev = root;

        treeToDoublyList_(root.right);

    }

    public Node treeToDoublyList(Node root) {

        if (root == null)
            return root;
        treeToDoublyList_(root);

        Node head = dummy.right;
        head.left = null;
        dummy.right = null;

        prev.right = head;
        head.left = prev;
        return head;

    }

    int idx = 0;

    public TreeNode createTree(int[] arr) {
        if (idx == arr.length || arr[idx] == -1) {
            idx++;
            return null;
        }
        TreeNode node = new TreeNode(arr[idx++]);
        node.left = createTree(arr);
        node.right = createTree(arr);

        return node;
    }

    public void serializeTree(TreeNode node, ArrayList<Integer> arr) {
        if (node == null) {
            arr.add(-1);
            return;
        }

        arr.add(node.val);
        serializeTree(node.left, arr);
        serializeTree(node.right, arr);
    }

    public static TreeNode rightMostNode(TreeNode next, TreeNode curr) {
        while (next.right != null && next.right != curr)
            next = next.right;

        return next;
    }

    public static void morrisInOrderTraversal(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            TreeNode next = curr.left;
            if (next == null) {
                System.out.print(curr.val + " ");
                curr = curr.right;
            } else {
                TreeNode rightMost = rightMostNode(next, curr);
                if (rightMost.right == null) { // thread create
                    rightMost.right = curr;
                    curr = curr.left;
                } else { // thread break
                    rightMost.right = null;
                    System.out.print(curr.val + " ");
                    curr = curr.right;
                }
            }
        }
    }

    public static void morrisPreOrderTraversal(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            TreeNode next = curr.left;
            if (next == null) {
                System.out.print(curr.val + " ");
                curr = curr.right;
            } else {
                TreeNode rightMost = rightMostNode(next, curr);
                if (rightMost.right == null) { // thread create
                    rightMost.right = curr;
                    System.out.print(curr.val + " ");
                    curr = curr.left;
                } else { // thread break
                    rightMost.right = null;
                    curr = curr.right;
                }
            }
        }
    }

    public static class tPair {
        TreeNode node = null;
        boolean selfDone = false;
        boolean leftDone = false;
        boolean rightDone = false;

        tPair(TreeNode node, boolean selfDone, boolean leftDone, boolean rightDone) {
            this.node = node;
            this.selfDone = selfDone;
            this.leftDone = leftDone;
            this.rightDone = rightDone;
        }
    }

    public static void IterTraversal(TreeNode root) {
        LinkedList<tPair> ll = new LinkedList<>();
        ll.addFirst(new tPair(root, false, false, false));
        while (ll.size() != 0) {
            tPair pair = ll.getFirst();
            if (!pair.leftDone) {
                pair.leftDone = true;
                if (pair.node.left != null)
                    ll.addFirst(new tPair(pair.node.left, false, false, false));

            } else if (!pair.selfDone) {
                pair.selfDone = true;
                System.out.print(pair.node.val + " ");
            } else if (!pair.rightDone) {
                pair.rightDone = true;
                if (pair.node.right != null)
                    ll.addFirst(new tPair(pair.node.right, false, false, false));
            } else {
                ll.removeFirst();
            }
        }
    }

    // 1008
    // lr -> left range, rr = right range
    int bst_idx = 0;

    public TreeNode constructBSTFromPreOrder(int[] arr, int lr, int rr) {
        if (bst_idx == arr.length || arr[bst_idx] < lr || arr[bst_idx] > rr)
            return null;

        TreeNode node = new TreeNode(arr[bst_idx++]);
        node.left = constructBSTFromPreOrder(arr, lr, node.val);
        node.right = constructBSTFromPreOrder(arr, node.val, rr);

        return node;
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        return constructBSTFromPreOrder(preorder, -(int) 1e8, (int) 1e8);
    }

    public class levelPair {
        TreeNode par = null;
        int lb = -(int) 1e8;
        int rb = (int) 1e8;

        levelPair() {

        }

        levelPair(TreeNode par, int lb, int rb) {
            this.par = par;
            this.lb = lb;
            this.rb = rb;
        }
    }

    public TreeNode constructBSTFromLevel(int[] arr) {
        int idx = 0;
        LinkedList<levelPair> que = new LinkedList<>();
        que.add(new levelPair());
        TreeNode root = null;

        while (que.size() != 0 && idx < arr.length) {
            levelPair pair = que.removeFirst();

            if (arr[idx] < pair.lb || arr[idx] > pair.rb)
                continue;

            TreeNode node = new TreeNode(arr[idx++]);
            if (pair.par == null)
                root = node;
            else {
                if (node.val < pair.par.val)
                    pair.par.left = node;
                else
                    pair.par.right = node;
            }

            que.addFirst(new levelPair(node, pair.lb, node.val));
            que.addFirst(new levelPair(node, node.val, pair.rb));
        }

    }

    // 230
    public int kthSmallest(TreeNode root, int k) {
        TreeNode curr = root;
        while (curr != null) {
            TreeNode next = curr.left;

            if (next == null) {
                if (k == 1)
                    return curr.val;
                k--;
                curr = curr.right;

            } else {
                TreeNode rightMost = rightMostNode(next, curr);
                if (rightMost.right == null) { // thread create
                    rightMost.right = curr;
                    curr = curr.left;
                } else { // thread break
                    rightMost.right = null;
                    if (k == 1)
                        return curr.val;
                    k--;
                    curr = curr.right;

                }
            }
        }

        return -1;
    }

    // 437
    int ans = 0;

    public void pathSumIII(TreeNode node, HashMap<Integer, Integer> map, int tar, int prefixSum) {
        if (node == null)
            return;

        prefixSum += node.val;
        ans += map.getOrDefault(prefixSum - tar, 0);

        map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);

        pathSumIII(node.left, map, tar, prefixSum);
        pathSumIII(node.right, map, tar, prefixSum);

        map.put(prefixSum, map.get(prefixSum) - 1);
        if (map.get(prefixSum) == 0)
            map.remove(prefixSum);
    }

    public int pathSum(TreeNode root, int K) {
        // prefix sum , Frequency
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        pathSumIII(root, map, K, 0);
        return ans;
    }

    public class pair {
        TreeNode node = null;
        long w = 0;

        pair(TreeNode node, long w) {
            this.node = node;
            this.w = w;
        }
    }

    // 662
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<pair> que = new LinkedList<>();
        que.addLast(new pair(root, 1));
        int ans = 0;

        while (que.size() != 0) {
            int size = que.size();
            long fi = que.getFirst().w;
            long li = que.getFirst().w;

            while (size-- > 0) {
                pair p = que.removeFirst();

                TreeNode node = p.node;
                long w = p.w;
                li = w;

                if (node.left != null)
                    que.addLast(new pair(node.left, 2 * w));
                if (node.right != null)
                    que.addLast(new pair(node.right, 2 * w + 1));

            }

            ans = Math.max(ans, (int) (li - fi + 1));
        }

        return ans;
    }
}
