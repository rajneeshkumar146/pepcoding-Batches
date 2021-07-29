import java.util.ArrayList;
import java.util.LinkedList;

public class l008_GTree {

    public class Node {

        int val = 0;
        ArrayList<Node> children;

        Node(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }
    }

    public static int height(Node node) {
        int maxheight = -1;
        for (Node child : node.children) {
            maxheight = Math.max(maxheight, height(child));
        }

        return maxheight + 1;
    }

    // 428
    class Codec {
        public void serialize(Node root, StringBuilder sb) {
            sb.append(root.val + " ");
            for (Node child : root.children) {
                serialize(child, sb);
            }
            sb.append("null ");
        }

        public String serialize(Node root) {
            if (root == null)
                return "";
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);
            return sb.toString();

        }

        // Decodes your encoded data to tree.
        public Node deserialize(String data) {
            if (data.length() == 0)
                return null;
            String[] arr = data.split(" ");
            LinkedList<Node> st = new LinkedList<>();

            for (int i = 0; i < arr.length - 1; i++) {
                String s = arr[i];
                if (!s.equals("null")) {
                    // st.addFirst(new Node(Integer.parseInt(s), new ArrayList<>())); // for
                    // leetcode
                    st.addFirst(new Node(Integer.parseInt(s))); // cpp: stoi(s)
                } else {
                    Node node = st.removeFirst();
                    st.getFirst().children.add(node);
                }
            }

            return st.removeFirst();
        }
    }

    public static ArrayList<Integer> zigZag(Node root) {
        LinkedList<Node> que = new LinkedList<>(); // push_back, pop_front
        LinkedList<Node> st = new LinkedList<>();// push_front, pop_front

        que.addLast(root);
        ArrayList<Integer> ans = new ArrayList<>();

        int level = 0;
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                Node node = que.removeFirst();
                ans.add(node.val);
                if (level % 2 == 0) {
                    for (int i = 0; i < node.children.size(); i++)
                        st.addFirst(node.children.get(i));
                } else {
                    for (int i = node.children.size() - 1; i >= 0; i--)
                        st.addFirst(node.children.get(i));
                }
            }

            LinkedList<Node> temp = que;
            que = st;
            st = temp;
            level++;
        }
        return ans;
    }

}