import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.LinkedList;

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

    public static void kdown(TreeNode root, int time, TreeNode blockNode, ArrayList<ArrayList<Integer>> ans,
            HashSet<Integer> water) {
        if (root == null || root == blockNode || water.contains(root.val))
            return;

        if (time == ans.size())
            ans.add(new ArrayList<>());
        ans.get(time).add(root.val);

        kdown(root.left, time + 1, blockNode, ans);
        kdown(root.right, time + 1, blockNode, ans);
    }

    // -1 : did we gett the target node, -2 : fire will not reach that node, t > 0 :
    // fire will reach with time t.
    public static int burningTreeWithWater(TreeNode root, int target, ArrayList<ArrayList<Integer>> ans,
            HashSet<Integer> water) {
        if (root == null)
            return -1;
        if (root.val == target) {
            if (!water.contains(root.val)) {
                kdown(root, 0, null, ans);
                return 1;
            } else
                return -2;
        }

        int ld = burningTreeWithWater(root.left, target, ans, water);
        if (ld > 0) {
            if (!water.contains(root.val)) {
                kdown(root, ld, root.left, ans);
                return ld + 1;
            }
            return -2;
        }
        if (ld == -2)
            return -2;

        int rd = burningTreeWithWater(root.right, target, ans, water);
        if (rd > 0) {
            if (!water.contains(root.val)) {
                kdown(root, rd, root.right, ans);
                return rd + 1;
            }
            return -2;
        }
        if (rd == -2)
            return -2;

        return -1;
    }

    public static void burningTree(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        burningTree(root, target, ans);
    }

    // LCA===================================================================

    TreeNode LCA = null;

    public boolean lowestCommonAncestor_(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null)
            return false;

        boolean self = false;
        if (node == p || node == q)
            self = true;

        boolean left = lowestCommonAncestor_(node.left, p, q);
        boolean right = lowestCommonAncestor_(node.right, p, q);

        if ((left && right) || (left && self) || (right && self))
            LCA = node;

        return left || right || self;
    }

    public TreeNode lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
        lowestCommonAncestor_(node, p, q);
        return LCA;
    }

    public static void levelOrder(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int level = 0;
        while (que.size() != 0) {
            int size = que.size();
            ArrayList<Integer> smallAns = new ArrayList<>();
            System.out.println(level);
            while (size-- > 0) {
                TreeNode rnode = que.removeFirst();
                smallAns.add(rnode.val);

                if (rnode.left != null)
                    que.addLast(rnode.left);
                if (rnode.right != null)
                    que.addLast(rnode.right);
            }
            ans.add(smallAns);
            level++;
        }

        int count = 0;
        for (var list : ans) {
            System.out.println(count++ + " -> " + list);
        }
    }

    public static List<Integer> leftView(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        List<Integer> ans = new ArrayList<>();
        while (que.size() != 0) {

            int size = que.size();
            ans.add(que.getFirst().val);
            while (size-- > 0) {
                TreeNode rnode = que.removeFirst();

                if (rnode.left != null)
                    que.addLast(rnode.left);
                if (rnode.right != null)
                    que.addLast(rnode.right);
            }
        }

        return ans;
    }

    public static List<Integer> rightView(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        List<Integer> ans = new ArrayList<>();
        while (que.size() != 0) {

            int size = que.size();
            ans.add(que.getFirst().val);
            while (size-- > 0) {
                TreeNode rnode = que.removeFirst();

                if (rnode.right != null)
                    que.addLast(rnode.right);
                if (rnode.left != null)
                    que.addLast(rnode.left);
            }
        }

        return ans;
    }

    public static void widthOfShadow(TreeNode node, int vl, int[] minMax) {
        if (node == null)
            return;
        minMax[0] = Math.min(minMax[0], vl);
        minMax[1] = Math.max(minMax[1], vl);
        widthOfShadow(node.left, vl - 1, minMax);
        widthOfShadow(node.right, vl + 1, minMax);
    }

    public static class vPair {
        TreeNode node = null;
        int vl = 0;
        int level = 0;

        vPair(TreeNode node, int vl) {
            this(node, vl, 0);
        }

        vPair(TreeNode node, int vl, int level) {
            this.vl = vl;
            this.node = node;
            this.level = level;
        }
    }

    public static ArrayList<ArrayList<Integer>> verticalOrder(TreeNode root) {
        LinkedList<vPair> que = new LinkedList<>();
        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int len = minMax[1] - minMax[0] + 1;

        que.addLast(new vPair(root, Math.abs(minMax[0])));

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < len; i++)
            ans.add(new ArrayList<>());

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vPair rp = que.removeFirst();
                int vl = rp.vl;
                TreeNode node = rp.node;

                ans.get(vl).add(node.val);

                if (node.left != null)
                    que.addLast(new vPair(node.left, vl - 1));
                if (node.right != null)
                    que.addLast(new vPair(node.right, vl + 1));
            }
        }

        return ans;
    }

    public static ArrayList<Integer> bottomView(TreeNode root) {
        LinkedList<vPair> que = new LinkedList<>();
        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int len = minMax[1] - minMax[0] + 1;

        que.addLast(new vPair(root, Math.abs(minMax[0])));

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < len; i++)
            ans.add(null);

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vPair rp = que.removeFirst();
                int vl = rp.vl;
                TreeNode node = rp.node;

                ans.set(vl, node.val);

                if (node.left != null)
                    que.addLast(new vPair(node.left, vl - 1));
                if (node.right != null)
                    que.addLast(new vPair(node.right, vl + 1));
            }
        }

        return ans;
    }

    public static ArrayList<Integer> topView(TreeNode root) {
        LinkedList<vPair> que = new LinkedList<>();
        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int len = minMax[1] - minMax[0] + 1;

        que.addLast(new vPair(root, Math.abs(minMax[0])));

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < len; i++)
            ans.add(null);

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vPair rp = que.removeFirst();
                int vl = rp.vl;
                TreeNode node = rp.node;

                if (ans.get(vl) == null)
                    ans.set(vl, node.val);

                if (node.left != null)
                    que.addLast(new vPair(node.left, vl - 1));
                if (node.right != null)
                    que.addLast(new vPair(node.right, vl + 1));
            }
        }

        return ans;
    }

    public static ArrayList<ArrayList<Integer>> diagonalOrder(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        que.addLast(root);

        while (que.size() != 0) {
            int size = que.size();
            ArrayList<Integer> smallAns = new ArrayList<>();
            while (size-- > 0) { // diagonal
                TreeNode node = que.removeFirst();
                while (node != null) { // clusters of diagonal
                    smallAns.add(node.val);
                    if (node.left != null)
                        que.addLast(node.left);
                    node = node.right;
                }
            }

            ans.add(smallAns);
        }

        return ans;
    }

    public static ArrayList<ArrayList<Integer>> antiDiagonalOrder(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        que.addLast(root);

        while (que.size() != 0) {
            int size = que.size();
            ArrayList<Integer> smallAns = new ArrayList<>();
            while (size-- > 0) { // diagonal
                TreeNode node = que.removeFirst();
                while (node != null) { // clusters of diagonal
                    smallAns.add(node.val);
                    if (node.right != null)
                        que.addLast(node.right);
                    node = node.left;
                }
            }

            ans.add(smallAns);
        }

        return ans;
    }

    public static ArrayList<Integer> verticalOrderSum(TreeNode root) {
        LinkedList<vPair> que = new LinkedList<>();
        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int len = minMax[1] - minMax[0] + 1;

        que.addLast(new vPair(root, Math.abs(minMax[0])));

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < len; i++)
            ans.add(null);

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vPair rp = que.removeFirst();
                int vl = rp.vl;
                TreeNode node = rp.node;

                ans.set(vl, ans.get(vl) + node.val);

                if (node.left != null)
                    que.addLast(new vPair(node.left, vl - 1));
                if (node.right != null)
                    que.addLast(new vPair(node.right, vl + 1));
            }
        }

        return ans;
    }

    public static ArrayList<Integer> diagonalOrderSum(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();

        ArrayList<Integer> ans = new ArrayList<>();
        que.addLast(root);

        while (que.size() != 0) {
            int size = que.size();
            int sum = 0;
            while (size-- > 0) { // diagonal
                TreeNode node = que.removeFirst();
                while (node != null) { // clusters of diagonal
                    sum += node.val;
                    if (node.left != null)
                        que.addLast(node.left);
                    node = node.right;
                }
            }

            ans.add(sum);
        }

        return ans;
    }

    public static class ListNode {
        int data = 0;
        ListNode prev = null;
        ListNode next = null;

        ListNode(int data) {
            this.data = data;
        }
    }

    public static void verticalOrderSum(TreeNode root, ListNode node) {
        node.data += root.val;
        if (root.left != null) {
            if (node.prev == null) {
                ListNode nnode = new ListNode(0);
                nnode.next = node;
                node.prev = nnode;
            }
            verticalOrderSum(root.left, node.prev);
        }

        if (root.right != null) {
            if (node.next == null) {
                ListNode nnode = new ListNode(0);
                nnode.prev = node;
                node.next = nnode;
            }
            verticalOrderSum(root.right, node.next);
        }
    }

    public static void verticalOrderSum(TreeNode node) {
        ListNode curr = new ListNode(0);
        verticalOrderSum(node, curr);
    }

    public static void diagonalOrderSum(TreeNode root, ListNode node) {
        node.data += root.val;
        if (root.left != null) {
            if (node.prev == null) {
                ListNode nnode = new ListNode(0);
                nnode.next = node;
                node.prev = nnode;
            }
            diagonalOrderSum(root.left, node.prev);
        }

        if (root.right != null) {
            diagonalOrderSum(root.right, node);
        }
    }

    public static void diagonalOrderSum(TreeNode node) {
        ListNode curr = new ListNode(0);
        diagonalOrderSum(node, curr);
    }

    // 987
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        PriorityQueue<vPair> que = new PriorityQueue<>((a, b) -> {
            if (a.vl != b.vl)
                return a.vl - b.vl;

            return a.node.val - b.node.val;
        });

        PriorityQueue<vPair> chilque = new PriorityQueue<>((a, b) -> {
            if (a.vl != b.vl)
                return a.vl - b.vl;

            return a.node.val - b.node.val;
        });

        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int len = minMax[1] - minMax[0] + 1;

        que.add(new vPair(root, Math.abs(minMax[0])));

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < len; i++)
            ans.add(new ArrayList<>());

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vPair rp = que.remove();
                int vl = rp.vl;
                TreeNode node = rp.node;

                ans.get(vl).add(node.val);

                if (node.left != null)
                    chilque.add(new vPair(node.left, vl - 1));
                if (node.right != null)
                    chilque.add(new vPair(node.right, vl + 1));
            }

            PriorityQueue<vPair> temp = que;
            que = chilque;
            chilque = temp;
        }

        return ans;
    }

    public List<List<Integer>> verticalTraversal_02(TreeNode root) {
        PriorityQueue<vPair> que = new PriorityQueue<>((a, b) -> {
            if (a.level != b.level) {
                return a.level - b.level;
            } else if (a.vl != b.vl)
                return a.vl - b.vl;

            return a.node.val - b.node.val;
        });

        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int len = minMax[1] - minMax[0] + 1;

        que.add(new vPair(root, Math.abs(minMax[0]), 0));

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < len; i++)
            ans.add(new ArrayList<>());

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vPair rp = que.remove();
                int vl = rp.vl;
                int level = rp.level;
                TreeNode node = rp.node;

                ans.get(vl).add(node.val);

                if (node.left != null)
                    que.add(new vPair(node.left, vl - 1, level + 1));
                if (node.right != null)
                    que.add(new vPair(node.right, vl + 1, level + 1));
            }
        }

        return ans;
    }

    public static class allSoluPair {
        TreeNode pred = null;
        TreeNode succ = null;

        int ceil = (int) 1e9;
        int floor = -(int) 1e9;

        TreeNode prev = null;
    }

    public static void allSolution(TreeNode node, int data, allSoluPair pair) {
        if (node == null)
            return;

        if (node.val < data)
            pair.floor = Math.max(pair.floor, node.val);

        if (node.val > data)
            pair.ceil = Math.min(pair.ceil, node.val);

        allSolution(node.left, data, pair);

        if (node.val == data)
            pair.pred = pair.prev;

        if (pair.prev != null && pair.prev.val == data)
            pair.succ = node;

        pair.prev = node;

        allSolution(node.right, data, pair);
    }
}