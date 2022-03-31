import java.util.*;

class q000_basic {
    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }

    public static int minEle(BST root) {
        BST curr = root;
        while (curr.left != null)
            curr = curr.left;

        return curr.value;
    }

    public static int maxEle(BST root) {
        BST curr = root;
        while (curr.right != null)
            curr = curr.right;

        return curr.value;
    }

    public static boolean find(BST root, int data) {
        BST curr = root;
        while (curr != null) {
            if (curr.value == data)
                return true;
            else if (curr.value < data)
                curr = curr.right;
            else
                curr = curr.left;
        }

        return false;
    }

    public static BST minHeightBst(List<Integer> array, int si, int ei) {
        if (si > ei)
            return null;

        int mid = (si + ei) / 2;
        BST root = new BST(array.get(mid));

        root.left = minHeightBst(array, si, mid - 1);
        root.right = minHeightBst(array, mid + 1, ei);

        return root;
    }

    public static BST minHeightBst(List<Integer> array) {
        return minHeightBst(array, 0, array.size() - 1);
    }

    class Program {
        static class BST {
            public int value;
            public BST left;
            public BST right;

            public BST(int value) {
                this.value = value;
            }

            public int minEle(BST root) {
                BST curr = root;
                while (curr.left != null)
                    curr = curr.left;

                return curr.value;
            }

            public BST insertIntoBST(BST root, int val) {
                if (root == null)
                    return new BST(val);

                if (root.value > val)
                    root.left = insertIntoBST(root.left, val);
                else
                    root.right = insertIntoBST(root.right, val);

                return root;
            }

            public BST insert(int val) {
                return insertIntoBST(this, val);
            }

            public boolean contains(int data) {
                BST curr = this;
                while (curr != null) {
                    if (curr.value == data)
                        return true;
                    else if (curr.value <= data)
                        curr = curr.right;
                    else
                        curr = curr.left;
                }

                return false;
            }

            private BST deleteNodeIfRoot(BST root) {
                if (root.left == null && root.right == null)
                    return null;
                else if (root.left == null) {
                    root.value = root.right.value;
                    root.right = deleteNode(root.right, root.value);
                } else if (root.right == null) {
                    root.value = root.left.value;
                    root.left = deleteNode(root.left, root.value);
                } else {
                    root = deleteNode(root, root.value);
                }
                return root;
            }

            public BST deleteNode(BST root, int key) {
                if (root == null)
                    return null;

                if (root.value > key)
                    root.left = deleteNode(root.left, key);
                else if (root.value < key)
                    root.right = deleteNode(root.right, key);
                else {
                    if (root.left == null || root.right == null)
                        return root.left != null ? root.left : root.right;

                    int minele = minEle(root.right);
                    root.value = minele;
                    root.right = deleteNode(root.right, minele);
                }
                return root;
            }

            public BST remove(int value) {
                if (this.value == value)
                    return deleteNodeIfRoot(this);
                return deleteNode(this, value);
            }
        }
    }

    // Kth Largest Value In BST

    public class kthLargestPair {
        int k = 0;
        BST node = null;
    }

    public boolean kthLargest(BST root, kthLargestPair pair) {
        if (root == null)
            return false;

        if (kthLargest(root.right, pair))
            return true;

        pair.k--;
        if (pair.k == 0) {
            pair.node = root;
            return true;
        }

        if (kthLargest(root.left, pair))
            return true;

        return false;
    }

    public int findKthLargestValueInBst(BST root, int k) {
        kthLargestPair pair = new kthLargestPair();
        pair.k = k;
        kthLargest(root, pair);
        return pair.node.value;
    }

    // Reconstruct BST
    // IDX[0] -> index for traversal in array
    public BST reconstructBst(ArrayList<Integer> pre, int[] IDX, int lr, int rr) {
        if (IDX[0] >= pre.size() || pre.get(IDX[0]) < lr || pre.get(IDX[0]) >= rr)
            return null;

        // int idx = IDX[0];
        // BST root = new BST(pre.get(idx));
        // IDX[0]++;

        BST root = new BST(pre.get(IDX[0]++));
        root.left = reconstructBst(pre, IDX, lr, root.value);
        root.right = reconstructBst(pre, IDX, root.value, rr);

        return root;
    }

    public BST reconstructBst(ArrayList<Integer> pre) {
        return reconstructBst(pre, new int[1], Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // BST Traversal
    public static List<Integer> inOrderTraverse(BST tree, List<Integer> array) {
        if (tree == null)
            return array;
        inOrderTraverse(tree.left, array);
        array.add(tree.value);
        inOrderTraverse(tree.right, array);
        return array;
    }

    public static List<Integer> preOrderTraverse(BST tree, List<Integer> array) {
        if (tree == null)
            return array;
        array.add(tree.value);
        preOrderTraverse(tree.left, array);
        preOrderTraverse(tree.right, array);
        return array;
    }

    public static List<Integer> postOrderTraverse(BST tree, List<Integer> array) {
        if (tree == null)
            return array;
        postOrderTraverse(tree.left, array);
        postOrderTraverse(tree.right, array);
        array.add(tree.value);
        return array;
    }

    // find closest Value
    private static BST leftMost(BST node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private static BST rightMost(BST node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public static int findClosestValueInBst(BST root, int data) {
        BST succ = null, pred = null;

        while (root != null) {
            if (root.value < data) {
                pred = root;
                root = root.right;
            } else if (root.value > data) {
                succ = root;
                root = root.left;
            } else {
                if (root.value == data)
                    return data;

                if (root.right != null)
                    succ = leftMost(root.right);
                if (root.left != null)
                    pred = rightMost(root.left);
            }
        }

        if (succ == null || pred == null)
            return succ != null ? succ.value : pred.value;

        return data - pred.value <= succ.value - data ? pred.value : succ.value;
    }

}