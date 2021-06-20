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

    // actual prev.left is your real previous
    void bToDLL_(Node root, Node prev) {
        if (root == null)
            return;

        bToDLL_(root.left);

        prev.left.right = root;
        root.left = prev.left;

        prev.left = root;

        bToDLL_(root.right);
    }

    Node bToDLL(Node root) {
        Node dummy = new Node(-1);
        prev = dummy;
        bToDLL_(root);
        //bToDLL_(root, new Node(-1));
        

        Node head = dummy.right;
        head.left = dummy.right = null;

        return head;
    }

    public static void insertAllLeft(LinkedList<Node> st, Node node) {
        while (node != null) {
            st.addFirst(node);
            node = node.left;
        }
    }

    public static Node bToDLL_02(Node node) {
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

    public static Node mergeDLL(Node h1, Node h2) {
        if (h1 == null || h2 == null)
            return h1 != null ? h1 : h2;

        Node dummy = new Node(-1);
        Node prev = dummy;
        Node c1 = h1, c2 = h2;

        while (c1 != null && c2 != null) {
            if (c1.val <= c2.val) {
                prev.right = c1;
                c1.left = prev;

                c1 = c1.right;
            } else {
                prev.right = c2;
                c2.left = prev;

                c2 = c2.right;
            }

            prev = prev.right;
        }

        if (c1 != null) {
            prev.right = c1;
            c1.left = prev;
        } else {
            prev.right = c2;
            c2.left = prev;
        }

        Node head = dummy.right;
        dummy.right = head.left = null;
        return head;
    }

    public static Node getMidNode(Node node) {
        if (node == null || node.right == null)
            return node;

        Node slow = node, fast = node;
        while (fast.right != null && fast.right.right != null) {
            slow = slow.right;
            fast = fast.right.right;
        }

        return slow;
    }

    // left : prev, right : next
    public static Node sortDLL(Node head) {
        if (head == null || head.right == null)
            return head;

        Node midNode = getMidNode(head);
        Node nHead = midNode.right;
        nHead.left = midNode.right = null;

        return mergeDLL(sortDLL(head), sortDLL(nHead));
    }

    // left : prev, right : next
    public static Node SortedDLLToBST(Node head) {
        if (head == null || head.right == null)
            return head;

        Node root = getMidNode(head);
        Node leftDLLHead = head;
        Node rightDLLHead = root.right;

        root.left.right = root.right.left = null;
        root.left = root.right = null;

        root.left = SortedDLLToBST(leftDLLHead);
        root.right = SortedDLLToBST(rightDLLHead);

        return root;
    }

    public static Node BT_To_BST(Node root) {
        Node head = bToDLL_02(root);
        head = sortDLL(head);
        return SortedDLLToBST(head);
    }



}