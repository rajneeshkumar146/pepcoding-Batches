import java.util.LinkedList;

public class l002_TreeConversions {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Node {
        int val = 0;
        Node left = null;
        Node right = null;

        Node(int val) {
            this.val = val;
        }
    }

    // https://practice.geeksforgeeks.org/problems/binary-tree-to-dll/1
    static Node prev = null;

    void bToDLL_(Node root) {
        if (root == null)
            return;

        bToDLL_(root.left);

        prev.right = root;
        root.left = prev;

        prev = root;

        bToDLL_(root.right);
    }

    Node bToDLL(Node root) {
        Node dummy = new Node(-1);
        prev = dummy;
        bToDLL_(root);

        Node head = dummy.right;
        head.left = dummy.right = null;

        return head;
    }

    public void insertAllLeft(LinkedList<Node> st, Node node) {
        while (node != null) {
            st.addFirst(node);
            node = node.left;
        }
    }

    Node bToDLL_02(Node node) {
        Node dummy = new Node(-1);
        Node prev = dummy;

        LinkedList<Node> st = new LinkedList<>();
        insertAllLeft(st, node);

        while (st.size() != 0) {
            Node curr = st.removeFirst();
            prev.right = curr;
            curr.left = prev;
            prev = curr;

            insertAllLeft(st, curr.right);
        }

        Node head = dummy.right;

        head.left = dummy.right = null;
        return head;
    }

    // https://practice.geeksforgeeks.org/problems/binary-tree-to-cdll/1
    Node bTreeToClist(Node root) {
        Node dummy = new Node(-1);
        prev = dummy;
        bToDLL_(root);

        Node head = dummy.right;
        head.left = dummy.right = null;

        prev.right = head;
        head.left = prev;

        return head;
    }

    // left : prev, right : next
    public static void sortDLL(Node head) {

    }

    // left : prev, right : next
    public static void SortedDLLToBST(Node head) {

    }

}