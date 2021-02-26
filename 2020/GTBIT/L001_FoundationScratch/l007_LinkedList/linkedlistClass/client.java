public class client {
    public static void main(String[] args) throws Exception {
        linkedlist ll = new linkedlist();

        for (int i = 1; i <= 10; i++) {
            ll.addLast(i * 10);
        }

        ll.display();

        // System.out.println(ll.getLast());

    }
}

