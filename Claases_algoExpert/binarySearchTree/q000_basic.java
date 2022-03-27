import java.util.List;

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
        if(si > ei) return null;

        int mid = (si + ei) / 2;
        BST root = new BST(array.get(mid));

        root.left = minHeightBst(array, si , mid - 1);
        root.right = minHeightBst(array, mid + 1 , ei);
        
        return root;
    }

    public static BST minHeightBst(List<Integer> array) {
        return minHeightBst(array, 0, array.size() - 1);
    }

    



}