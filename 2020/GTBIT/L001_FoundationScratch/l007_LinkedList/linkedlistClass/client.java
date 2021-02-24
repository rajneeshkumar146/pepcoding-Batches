public class client{
    public static void main(String[] args) throws Exception{
        linkedlist ll1 = new linkedlist();
        for(int i=0;i<10;i++) ll1.addFirst(i * 10);

        linkedlist ll2 = new linkedlist();
        for(int i=0;i<10;i++) ll2.addFirst(i * 100);

        linkedlist ll3 = new linkedlist();
        for(int i=0;i<10;i++) ll3.addFirst(i * 1000);

        linkedlist ll4 = new linkedlist();
        for(int i=0;i<10;i++) ll4.addFirst(i * 20);

    }
}