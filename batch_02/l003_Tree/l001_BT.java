import java.util.ArrayList;
import java.util.Stack;

public class l001_BT {

    public static class Node {

        int data = 0;
        Node left = null;
        Node right = null;

        public Node() {

        }

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {

            String str = "";
            str += ((left != null ? left.data : "."));
            str += (" -> " + data + " <- ");
            str += ((right != null ? right.data : "."));
            str += "\n";

            if (left != null)
                str += left.toString();
            if (right != null)
                str += right.toString();

            return str;
        }

    }

    static int idx = 0;

    public static Node createTree(int[] arr) {
        if (idx == arr.length || arr[idx] == -1) {
            idx++;
            return null;
        }

        Node node = new Node(arr[idx]);
        idx++;

        node.left = createTree(arr);
        node.right = createTree(arr);

        return node;
    }

    public static void display(Node node) {
        if (node == null)
            return;

        System.out.print((node.left != null ? node.left.data : "."));
        System.out.print(" -> " + node.data + " <- ");
        System.out.println((node.right != null ? node.right.data : "."));

        display(node.left); // display(2*idx+1);
        display(node.right); // display(2*idx+2);

    }

    // basic.============================

    public static int maximumInTree(Node node) {
        if (node == null)
            return (int) -1e8;

        int lmax = maximumInTree(node.left);
        int rmax = maximumInTree(node.right);
        int oMax = Math.max(lmax, rmax);

        return Math.max(node.data, oMax);
    }

    public static boolean find(Node node, int data) {
        if (node == null)
            return false;

        if (node.data == data)
            return true;
        boolean res = false;
        res = res || find(node.left, data);
        res = res || find(node.right, data);
        return res;

    }

    public static int size(Node node) {
        if (node == null)
            return 0;
        return size(node.left) + size(node.right) + 1;

    }

    public static int height(Node node) {
        if (node == null)
            return -1;
        return Math.max(height(node.left), height(node.right)) + 1;

    }

    // set1.==================================

    public static ArrayList<Node> rootToNodePath(Node node, int data) {
        if (node == null) {
            ArrayList<Node> base = new ArrayList<>();
            return base;
        }

        if (node.data == data) {
            ArrayList<Node> base = new ArrayList<>();
            base.add(node);
            return base;
        }

        ArrayList<Node> left = rootToNodePath(node.left, data);
        if (left.size() != 0) {
            left.add(node);
            return left;
        }
        ArrayList<Node> right = rootToNodePath(node.right, data);
        if (right.size() != 0) {
            right.add(node);
            return right;
        }

        return new ArrayList<>();
    }

    public static int LCA_01(Node node, int data1, int data2) {
        ArrayList<Node> list1 = rootToNodePath(node, data1);
        ArrayList<Node> list2 = rootToNodePath(node, data2);

        int ans = -1;
        int i = list1.size() - 1;
        int j = list2.size() - 1;
        while (i >= 0 && j >= 0) {
            if (list1.get(i) != list2.get(j)) {
                break;
            }

            ans = list1.get(i).data;
            i--;
            j--;
        }

        return ans;

    }

    public static void kDown(Node node, Node pnode, int level) {
        if (node == null || node == pnode) {
            return;
        }

        if (level == 0) {
            System.out.print(node.data + " ");
            return;
        }

        kDown(node.left, pnode, level - 1);
        kDown(node.right, pnode, level - 1);
    }

    public static void KAway_01(Node node, int data, int k) {
        ArrayList<Node> list1 = rootToNodePath(node, data);
        Node pnode = null;

        for (int i = 0; i < list1.size(); i++) {
            kDown(list1.get(i), pnode, k - i);
            pnode = list1.get(i);
        }
    }

    public static int kAway_02(Node node, int data, int k) {
        if (node == null)
            return -1;

        if (node.data == data) {
            kDown(node, null, k);
            return 1;
        }
        int ld = kAway_02(node.left, data, k);
        if (ld != -1) {
            // if(k-ld==0) System.out.print(node.data + " ");
            // else if(node.right!=null){
            // kDown(node.right, node.left, k - ld-1);
            // }

            kDown(node, node.left, k - ld);
            return ld + 1;
        }
        int rd = kAway_02(node.right, data, k);
        if (rd != -1) {
            kDown(node, node.right, k - rd);
            return rd + 1;
        }

        return -1;
    }

    int diameter_01(Node node) {
        if (node == null)
            return 0;

        int ld = diameter_01(node.left);
        int rd = diameter_01(node.right);

        int lh = height(node.left);
        int rh = height(node.right);

        return Math.max(Math.max(ld, rd), lh + rh + 2);
    }

    int[] diameter_02(Node node) {
        if (node == null)
            return new int[] { 0, -1 };

        int[] ld = diameter_02(node.left);
        int[] rd = diameter_02(node.right);

        int[] myAns = new int[2];
        myAns[0] = Math.max(Math.max(ld[0], rd[0]), ld[1] + rd[1] + 2);
        myAns[1] = Math.max(ld[1], rd[1]) + 1;

        return myAns;
    }

    static int maxDia = 0;

    int diameter_03(Node node) {
        if (node == null)
            return -1;

        int lh = diameter_03(node.left);
        int rh = diameter_03(node.right);

        maxDia = Math.max(maxDia, lh + rh + 2);
        return Math.max(lh, rh) + 1;

    }

    static int MaxSum = Integer.MIN_VALUE;

    public static int leafToLeafSum(Node node) {
        if (node == null)
            return Integer.MIN_VALUE;

        if (node.left == null && node.right == null) { // leaf
            return node.data;
        }

        int lmaxSum = leafToLeafSum(node.left);
        int rmaxSum = leafToLeafSum(node.right);

        if (node.left != null && node.right != null)
            MaxSum = Math.max(MaxSum, lmaxSum + rmaxSum + node.data);

        return Math.max(lmaxSum, rmaxSum) + node.data;
    }

    static int MaxSum1 = Integer.MIN_VALUE;

    public static int nodeToNodeSum(Node node) {
        if (node == null)
            return 0;

        int lmaxSum = nodeToNodeSum(node.left);
        int rmaxSum = nodeToNodeSum(node.right);

        int maxbranch = Math.max(lmaxSum, rmaxSum);

        MaxSum1 = Math.max(Math.max(MaxSum1, node.data),
                Math.max(maxbranch + node.data, lmaxSum + rmaxSum + node.data));

        return Math.max(maxbranch + node.data, node.data);
    }

    // -1 : i need a camera.
    // 0 : i already covered.
    // 1 : im a camera
    static int cameras = 0;

    public static int minCameras_(Node node) {
        if (node == null)
            return 0;

        int left = minCameras_(node.left);
        int right = minCameras_(node.right);

        if (left == -1 || right == -1) {
            cameras++;
            return 1;
        }

        if (left == 1 || right == 1) {
            return 0;
        }

        return -1;
    }

    public static int minCameras(Node node) {
        int val = minCameras_(node);
        if (val == -1)
            cameras++;
        return cameras;
    }

    public static class allSol {
        int height = -1;
        int size = 0;
        boolean find = false;
        // int diameter = 0;

        Node pred = null;
        Node succ = null;
        Node prev = null;

        int ceil = Integer.MAX_VALUE;
        int floor = Integer.MIN_VALUE;
    }

    public static void allSolution(Node node, int data, int level, allSol pair) {
        if (node == null)
            return;

        pair.height = Math.max(pair.height, level);
        pair.size++;
        pair.find = pair.find || node.data == data;

        if (node.data > data) {
            pair.ceil = Math.min(pair.ceil, node.data);
        }

        if (node.data < data) {
            pair.floor = Math.max(pair.floor, node.data);
        }

        if (node.data == data && pair.pred == null) {
            pair.pred = pair.prev;
        } else if (pair.prev != null && pair.succ == null && pair.prev.data == data) {
            pair.succ = node;
        }

        pair.prev = node;

        allSolution(node.left, data, level + 1, pair);
        allSolution(node.right, data, level + 1, pair);

    }

    public static void set1(Node node) {
        // System.out.println(LCA_01(node, 40, 70));
        // KAway_01(node, 60, 2);
        leafToLeafSum(node);
        System.out.println(MaxSum);
    }

    public static Node BSTCreate(int[] arr, int si, int ei) {
        if (si > ei)
            return null;
        int mid = (si + ei) >> 1; // si + (ei-si)/2;
        Node node = new Node(arr[mid]);

        node.left = BSTCreate(arr, si, mid - 1);
        node.right = BSTCreate(arr, mid + 1, ei);

        return node;
    }

    public static boolean find_01(Node node, int data) {
        if (node == null)
            return false;

        if (node.data == data)
            return true;
        else if (data < node.data)
            return find_01(node.left, data);
        else
            return find_01(node.right, data);
    }

    public static Node addData(Node node, int data) {
        if (node == null)
            return new Node(data);

        if (data < node.data)
            node.left = addData(node.left, data);
        else
            node.right = addData(node.right, data);

        return node;
    }

    public static boolean find_02(Node node, int data) {

        while (node != null) {
            if (node.data == data)
                return true;
            else if (data < node.data)
                node = node.left;
            else
                node = node.right;

        }
        return false;
    }

    public static void predSuccForBSt(Node node, int data) {
        Node succ = null;
        Node pred = null;
        while (node != null) {
            if (node.data == data) {

                if (node.right != null) {
                    succ = node.right;
                    while (succ.left != null) {
                        succ = succ.left;
                    }
                }

                if (node.left != null) {
                    pred = node.left;
                    while (pred.right != null) {
                        pred = pred.right;
                    }
                }

                System.out.println("Succ: " + (succ != null ? succ.data : -1));
                System.out.println("Pred: " + (pred != null ? pred.data : -1));

                return;

            } else if (data < node.data) {
                succ = node;
                node = node.left;
            } else {
                pred = node;
                node = node.right;
            }
        }

    }

    public static int minInTree(Node root) {
        if (root == null)
            return Integer.MAX_VALUE;

        Node rnode = root;
        while (rnode.left != null) {
            rnode = rnode.left;
        }

        return rnode.data;
    }

    public static int maxInTree(Node root) {
        if (root == null)
            return Integer.MIN_VALUE;

        Node rnode = root;
        while (rnode.right != null) {
            rnode = rnode.right;
        }

        return rnode.data;
    }

    public static Node removeNode(Node node, int data) {
        if (node == null)
            return null;

        if (node.data == data) {
            if (node.left == null || node.right == null) {
                return node.left == null ? node.right : node.left;
            }

            int maxdata = maxInTree(node.left);
            node.data = maxdata;

            node.left = removeNode(node.left, maxdata);
        } else if (data < node.data)
            node.left = removeNode(node.left, data);
        else
            node.right = removeNode(node.right, data);

        return node;
    }

    public static Node rightMostOfNextleft(Node leftNode, Node curr) {
        while (leftNode.right != null && leftNode.right != curr) {
            leftNode = leftNode.right;
        }
        return leftNode;
    }

    public static void morrisInOder(Node node) {
        Node curr = node;
        while (curr != null) {
            Node nextLeft = curr.left;
            if (nextLeft == null) {
                System.out.print(curr.data + " ");
                curr = curr.right;
            } else {

                Node rightMost = rightMostOfNextleft(nextLeft, curr);
                if (rightMost.right == null) {
                    rightMost.right = curr; // create thread.
                    curr = curr.left;
                } else {
                    System.out.print(curr.data + " ");
                    rightMost.right = null; // break thread.
                    curr = curr.right;
                }
            }

        }

    }

    public static class Tpair {
        Node node = null;
        boolean sd = false;
        boolean ld = false;
        boolean rd = false;

        Tpair(Node node) {
            this.node = node;
        }
    }

    public static void preOrder(Node node) {
        Stack<Tpair> st = new Stack<>();
        st.add(new Tpair(node));

        while (st.size() != 0) {
            Tpair tnode = st.peek();

            if (!tnode.ld) {
                tnode.ld = true;
                if (tnode.node.left != null) {
                    st.add(new Tpair(tnode.node.left));
                }
            } else if (!tnode.sd) {
                tnode.sd = true;
                System.out.print(tnode.node.data + " ");
            } else if (!tnode.rd) {
                tnode.rd = true;
                if (tnode.node.right != null) {
                    st.add(new Tpair(tnode.node.right));
                }
            } else {
                st.pop();
            }
        }

    }

    static int leftMost = -1;
    static int rightMost = -1;

    public static void width(Node node, int level) {
        if (node == null)
            return;
        leftMost = Math.min(leftMost, level);
        rightMost = Math.max(rightMost, level);

        width(node.left, level - 1);
        width(node.right, level + 1);
    }

    public static void VerticalOder(Node node) {
        LinkedList<Node> que1 = new LinkedList<>();
        LinkedList<Integer> que2 = new LinkedList<>();

        width(node, 0);
        ArrayList<Integer>[] list = new ArrayList[rightMost - leftMost];
        int[] sumList = new int[rightMost - leftMost];

        que1.addLast(node);
        que2.addLast(-leftMost);

        while (que1.size() != 0) {
            int size = que1.size();
            while (size-- > 0) {
                Node rn = que1.removeFirst();
                int rd = que2.removeFirst();

                list[rd].add(rn.data);
                sumList[rd] += rn.data;

                if (node.left != null) {
                    que1.addLast(rn.left);
                    que2.addLast(rd - 1);
                }

                if (node.right != null) {
                    que1.addLast(rn.right);
                    que2.addLast(rd + 1);
                }
            }
        }

    }

    public static void BST() {
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = (i + 1) * 10;
        }

        Node root = BSTCreate(arr, 0, arr.length - 1);
        // root = addData(root, 200);
        // root = addData(root, 210);
        // root = addData(root, 220);
        // root = addData(root, 230);
        // root = addData(root, 240);

        // root = removeNode(root, 200);
        // root = removeNode(root, 50);
        // root = removeNode(root, 40);

        System.out.println(root);
        // morrisInOder(root);
        preOrder(root);
        // predSuccForBSt(root, 100);
        // System.out.println(find_02(root,130));

    }

    public static void solve() {
        // int[] arr = { 10, 20, 30, 40, -1, -1, 50, -1, -1, 60, -1, 70, -1, -1, 80, 90,
        // 100, 120, -1, -1, 130, -1, -1,
        // 110, -1, -1, 140, -1, -1 };

        // Node root = createTree(arr);
        // System.out.println(root);
        // set1(root);
        BST();
    }

    public static void main(String[] args) {
        solve();
    }

}