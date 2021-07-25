import java.util.ArrayList;
import java.util.LinkedList;

public class l003TraversalSet {
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
        ArrayList<Integer> ans = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightMostNode = getRightMostNode(left, curr);
                if (rightMostNode.right == null) { // thread creation block
                    rightMostNode.right = curr; // thread is created
                    curr = curr.left;
                } else { // thread destroy block
                    rightMostNode.right = null; // thread is cut down

                    ans.add(curr.val);

                    curr = curr.right;
                }
            }
        }

        return ans;
    }

    public static ArrayList<Integer> morrisOrderTraversal(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightMostNode = getRightMostNode(left, curr);
                if (rightMostNode.right == null) { // thread creation block
                    rightMostNode.right = curr; // thread is created

                    ans.add(curr.val);
                    curr = curr.left;
                } else { // thread destroy block
                    rightMostNode.right = null; // thread is cut down
                    curr = curr.right;
                }
            }
        }

        return ans;
    }

    // Leetcode 98
    public boolean isValidBST(TreeNode root) {
        TreeNode curr = root;
        long prev = -(long) 1e13;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {

                if (prev >= curr.val)
                    return false;
                prev = curr.val;

                curr = curr.right;
            } else {
                TreeNode rightMostNode = getRightMostNode(left, curr);
                if (rightMostNode.right == null) { // thread creation block
                    rightMostNode.right = curr; // thread is created
                    curr = curr.left;
                } else { // thread destroy block
                    rightMostNode.right = null; // thread is cut down

                    if (prev >= curr.val)
                        return false;
                    prev = curr.val;

                    curr = curr.right;
                }
            }
        }

        return true;
    }

    public void insertAllLeft(TreeNode node, LinkedList<TreeNode> st) {
        while (node != null) {
            st.addFirst(node);
            node = node.left;
        }
    }

    public boolean isValidBST(TreeNode root) {
        LinkedList<TreeNode> st = new LinkedList<>();
        insertAllLeft(root, st);
        long prev = -(long) 1e13;
        while (st.size() != 0) {
            TreeNode rnode = st.removeFirst();

            if (prev >= rnode.val)
                return false;
            prev = rnode.val;

            insertAllLeft(rnode.right, st);
        }

        return true;
    }

    class BSTIterator {
        private LinkedList<TreeNode> st = new LinkedList<>();

        public BSTIterator(TreeNode root) {
            insertAllLeft(root, st);
        }

        private void insertAllLeft(TreeNode node, LinkedList<TreeNode> st) {
            while (node != null) {
                st.addFirst(node);
                node = node.left;
            }
        }

        public int next() {
            TreeNode rn = st.removeFirst();
            insertAllLeft(rn.right, st);
            return rn.val;
        }

        public boolean hasNext() {
            return st.size() != 0;

        }
    }

    class BSTIterator {
        private TreeNode curr = null;

        public BSTIterator(TreeNode root) {
            curr = root;
        }

        private TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
            while (node.right != null && node.right != curr) {
                node = node.right;
            }

            return node;
        }

        public int next() {
            int rv = -1;
            while (curr != null) {
                TreeNode left = curr.left;
                if (left == null) {

                    rv = curr.val;
                    curr = curr.right;
                    break;
                } else {
                    TreeNode rightMostNode = getRightMostNode(left, curr);
                    if (rightMostNode.right == null) { // thread creation block
                        rightMostNode.right = curr; // thread is created
                        curr = curr.left;
                    } else { // thread destroy block
                        rightMostNode.right = null; // thread is cut down

                        rv = curr.val;
                        curr = curr.right;
                        break;
                    }
                }
            }

            return rv;
        }

        public boolean hasNext() {
            return curr != null;

        }
    }

    public int kthSmallest(TreeNode root, int k) {
        int rv = -1;
        TreeNode curr = root;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                if (--k == 0)
                    rv = curr.val;
                curr = curr.right;
            } else {
                TreeNode rightMostNode = getRightMostNode(left, curr);
                if (rightMostNode.right == null) { // thread creation block
                    rightMostNode.right = curr; // thread is created

                    curr = curr.left;
                } else { // thread destroy block
                    rightMostNode.right = null; // thread is cut down

                    if (--k == 0)
                        rv = curr.val;

                    curr = curr.right;
                }
            }
        }

        return rv;
    }

    // Kth largest ele
    public TreeNode cdll(TreeNode root) {
        TreeNode dummy = new TreeNode(-1);
        TreeNode curr = root, prev = dummy;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                prev.right = curr;
                curr.left = prev;
                prev = prev.right;

                curr = curr.right;
            } else {
                TreeNode rightMostNode = getRightMostNode(left, curr);
                if (rightMostNode.right == null) {
                    rightMostNode.right = curr;
                    curr = curr.left;
                } else {

                    rightMostNode.right = null;

                    prev.right = curr;
                    curr.left = prev;
                    prev = prev.right;

                    curr = curr.right;
                }
            }
        }

        TreeNode head = dummy.right;
        dummy.right = head.left = null;

        // for circular doubly linkedlist
        head.left = prev;
        prev.right = head;

        return head;
    }

    // pred and successor for binary tree.
    public static void findPreSuc(TreeNode root, int key) {
        TreeNode curr = root, prev = null, pred = null, succ = null;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                if (curr.val == key) {
                    pred = prev;
                }

                if (prev != null && prev.val == key) {
                    succ = curr;
                }

                prev = curr;
                curr = curr.right;
            } else {
                TreeNode rmn = getRightMostNode(left, curr); // right most node
                if (rmn.right == null) {
                    rmn.right = curr;
                    curr = curr.left;
                } else {
                    rmn.right = null;

                    if (curr.val == key) {
                        pred = prev;
                    }

                    if (prev != null && prev.val == key) {
                        succ = curr;
                    }

                    prev = curr;
                    curr = curr.right;
                }
            }

        }
    }

    public void recoverTree(TreeNode root) {
        TreeNode curr = root;
        TreeNode prev = null, a = null, b = null;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                if (prev != null && prev.val > curr.val) {
                    if (a == null)
                        a = prev;
                    b = curr;
                }
                prev = curr;
                curr = curr.right;
            } else {
                TreeNode rightMostNode = getRightMostNode(left, curr);
                if (rightMostNode.right == null) { // thread creation block
                    rightMostNode.right = curr; // thread is created
                    curr = curr.left;
                } else { // thread destroy block
                    rightMostNode.right = null; // thread is cut down

                    if (prev != null && prev.val > curr.val) {
                        if (a == null)
                            a = prev;
                        b = curr;
                    }

                    prev = curr;
                    curr = curr.right;
                }
            }
        }

        if (a != null) {
            int temp = a.val;
            a.val = b.val;
            b.val = temp;
        }
    }
}