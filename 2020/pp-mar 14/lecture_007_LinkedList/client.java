import java.io.*;
import java.util.*;

public class client {
    public static void main(String[] args) throws Exception {
        linkedlist ll = new linkedlist();
        ll.addFirst(10);
        System.out.println(ll.getFirst());
        System.out.println(ll.size());
        ll.addLast(40);
        System.out.println(ll.removeAt(2));
        ll.addAt(1, 20);
        System.out.println(ll.getLast());
        System.out.println(ll);
        System.out.println(ll.removeAt(2));
        ll.addLast(50);
        System.out.println(ll);
        ll.addAt(2, 30);
        System.out.println(ll.getFirst());
        System.out.println(ll.getAt(3));
        System.out.println(ll);


        ll.oddEven();
        System.out.println(ll);  // first_ODD,Even

    }
}