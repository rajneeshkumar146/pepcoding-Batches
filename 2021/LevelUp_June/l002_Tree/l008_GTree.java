import java.util.ArrayList;

public class l008_GTree {

    public class Node {

        int data = 0;
        ArrayList<Node> children;

        Node(int data) {
            this.data = data;
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

    //428
    class Codec {
        // Encodes a tree to a single string.
        public String serialize(Node root) {

        }

        // Decodes your encoded data to tree.
        public Node deserialize(String data) {

        }
    }

}