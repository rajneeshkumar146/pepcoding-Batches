import java.util.ArrayList;

public class l001 {
    public class Node {
        int data = 0;
        ArrayList<Node> childs = new ArrayList<>();
    }

    public void display() {
        Node node = new Node();
        node.data = 10;
        System.out.println(node.data);
    }

    public static void main(String[] args) {
        l001 levelup = new l001();
        // levelup.Node node = new Node();
        levelup.display();
    }
}