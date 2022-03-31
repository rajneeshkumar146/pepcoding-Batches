class q000_leetcode {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 235
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.val > p.val && curr.val > q.val)
                curr = curr.left;
            else if (curr.val < p.val && curr.val < q.val)
                curr = curr.right;
            else
                return curr;
        }

        return null;
    }

    // 701
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);

        if (root.val < val)
            root.right = insertIntoBST(root.right, val);
        else
            root.left = insertIntoBST(root.left, val);

        return root;
    }

    public int minElement(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.val;
    }

    // 450
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;

        if (root.val < key)
            root.right = deleteNode(root.right, key);
        else if (root.val > key)
            root.left = deleteNode(root.left, key);
        else {
            if (root.left == null || root.right == null)
                return root.left != null ? root.left : root.right;

            int minele = minElement(root.right);
            root.val = minele;
            root.right = deleteNode(root.right, minele);
        }
        return root;
    }

    // 230

    public class kthSmallestPair {
        int k = 0;
        TreeNode node = null;
    }

    public boolean kthSmallest(TreeNode root, kthSmallestPair pair) {
        if (root == null)
            return false;

        if (kthSmallest(root.left, pair))
            return true;

        pair.k--;
        if (pair.k == 0) {
            pair.node = root;
            return true;
        }

        if (kthSmallest(root.right, pair))
            return true;
        return false;
    }

    public int kthSmallest(TreeNode root, int k) {
        kthSmallestPair pair = new kthSmallestPair();
        pair.k = k;
        kthSmallest(root, pair);
        return pair.node.val;
    }

    // 94
    // for stack -> addFirst, removeFirst
    private void insertAllLeft(TreeNode node, LinkedList<TreeNode> st) {
        TreeNode curr = node;
        while (curr != null) {
            st.addFirst(curr);
            curr = curr.left;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<TreeNode> st = new LinkedList<>();
        insertAllLeft(root, st);

        List<Integer> ans = new ArrayList<>();
        while (st.size() != 0) {
            TreeNode node = st.removeFirst();
            ans.add(node.val);
            insertAllLeft(node.right, st);
        }

        return ans;
    }

    // 102
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;

        // addFirst, removeFirst
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        while (que.size() != 0) {
            int size = que.size();
            List<Integer> smallAns = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = que.removeFirst();
                smallAns.add(node.val);
                if (node.left != null)
                    que.addLast(node.left);
                if (node.right != null)
                    que.addLast(node.right);
            }
            ans.add(smallAns);
        }
        return ans;
    }

    private TreeNode leftMost(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private TreeNode rightMost(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public void predSuccOfBST(TreeNode root, int data) {
        TreeNode succ = null, pred = null;

        while (root != null) {
            if (root.val < data) {
                pred = root;
                root = root.right;
            } else if (root.val > data) {
                succ = root;
                root = root.left;
            } else {
                if (root.right != null)
                    succ = leftMost(root.right);
                if (root.left != null)
                    pred = rightMost(root.left);
            }
        }

    }

}