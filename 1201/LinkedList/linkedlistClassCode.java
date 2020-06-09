public class linkedlistClassCode{

    public static class linkedlist{
         
        public class Node{
            int data=0;
            Node next=null;

            public Node(int data){
                this.data=data;  
            }
        }

        Node head=null;
        Node tail=null;
        int size=0;

        //general Util.========================================

        public int size(){
            return size;            
        }

        public boolean isEmpty(){
            return size==0?true:false;
        }

        public void display(){
            Node curr=head;
            while(curr!=null){
                System.out.print(curr.data+ " -> ");
                curr=curr.next;
            }
            System.out.println();
        }

        public Node getNodeAt(int idx){
            if(idx<0 || idx>=size) return null;
            
            if(idx==0) return head;
            else if(idx==size-1) return tail;
            else{
                Node curr=head;
                while(idx-- > 0){
                    curr=curr.next;
                }
                return curr;
            }
        }

        //Add.===================================================

        public void addFirst(int data){
           Node node=new Node(data);
           if(size==0){
                head=node;
                tail=node;
            }else{
                node.next=head;
                head=node;// head update
            }
            size++; 
        }
        
        public void addLast(int data){
            Node node=new Node(data);
            if(size==0){
                 head=node;
                 tail=node;
             }else{
                // phele tail point krega node ko and then tail, node bnjayega.
                tail.next=node;
                tail=node;
             }
             size++; 
        }

        public void addAt(int data,int idx){
            if(idx<0 || idx>size) return;

            if(idx==0) addFirst(data);
            else if(idx==size) addLast(data);
            else{
                Node prevNode=getNodeAt(idx-1);
                Node forwNode=prevNode.next;

                Node node=new Node(data);
                size++;

                prevNode.next=node;  
                node.next=forwNode;

            }

        }

        //remove.================================================
        
        public int removeFirst(){
            if(size==0) {
                System.out.println("NullPointerException: ");
                return -1;
            }

            int rv=head.data;;
            if(size==1){
               head=null;
               tail=null;
            }else{
                Node temp=head;
                head=head.next;
                temp.next=null;
            }

            size--;
            return rv;

        }

        public int removeLast(){
            if(size==0) {
                System.out.println("NullPointerException: ");
                return -1;
            }

            int rv=tail.data;
            if(size==1){
               head=null;
               tail=null;
            }else{
               Node secondLast=getNodeAt(size-2);
               secondLast.next=null;
               tail=secondLast;
            }

            size--;
            return rv;
        }

        public int removeAt(int idx){
            if(idx<0 || idx>=size) {
            System.out.println("NullPointerException: ");
            return -1;
        }

            if(idx==0) return removeFirst();
            else if(idx==size-1) return removeLast();
            else{
                Node prevNode=getNodeAt(idx-1);
                Node node=prevNode.next;

                prevNode.next=node.next; //link.

               node.next=null; // jiss node ko remove krna tha uske next ko null krdo.

                size--;

                return node.data;
            }
        }

        //get.===================================================

        public int getFirst(){
            if(size==0) {
                System.out.println("NoSuchElementException: ");
                return -1;
            }
            return head.data;
        }

        public int getLast(){
            if(size==0) {
                System.out.println("NoSuchElementException: ");
                return -1;
            }
            return tail.data;
        }

        public int getAt(int idx){
            if(idx<0 || idx>=size) {
                System.out.println("NullPointerException: ");
                return -1;
            }
            return getNodeAt(idx).data;
        }

    // question set.======================================================

    public void reverseData(){
        int i=0;
        int j=size-1;
        while(i<j){
          
            Node a=getNodeAt(i);
            Node b=getNodeAt(j);

            int temp=a.data;
            a.data=b.data;
            b.data=temp;

            i++;
            j--;
        }
    }

    //leetcode 206
    private Node reverseList_(Node node){
        Node curr=node;
        Node prev=null;
        Node forw=null;

        while(curr!=null){
            forw=curr.next;
            curr.next=prev;

            prev=curr;
            curr=forw;
        }

        return prev;
    }

    public void reverseList(){
        Node prev=reverseList_(head);
        tail=head;
        head=prev;
    }

    //leetcode 876
    private Node midNode(){
        Node slow=head;
        Node fast=head;

        while(fast!=null && fast.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }

        return slow;
    }

    public int midEle(){
        return midNode().data;
    }

    //leetcode 243 - O(n)
    public boolean isPalindrome(){
        if(head==null || head.next==null) return true;
       
       Node midNode=midNode();
       Node nhead=midNode.next;
       midNode.next=null;

       nhead=reverseList_(nhead);

       Node curr1=head;
       Node curr2=nhead;
       while(curr1!=null && curr2!=null)
       {
           if(curr1.data!=curr2.data) return false;

           curr1=curr1.next;
           curr2=curr2.next;
       }

       nhead=reverseList_(nhead);
       midNode.next=nhead;

       return true;

    }
 
    //Leetcode 143
    public void rotateList(){
        if(head==null || head.next==null) return;
       
       Node midNode=midNode();
       Node nhead=midNode.next;
       midNode.next=null;

       nhead=reverseList_(nhead);

       Node curr1=head;
       Node curr2=nhead;
       while(curr1!=null && curr2!=null)
       {
           // backup.
           Node forw1=curr1.next;
           Node forw2=curr2.next;

           //link connection.
           curr1.next=curr2;
           curr2.next=forw1;

           //move forward
           curr1=forw1;
           curr2=forw2;
       }
    }

    }





    public static void main(String[] args){
        linkedlist ll=new linkedlist();
        for(int i=1;i<=10;i++){
            ll.addLast(i*10);
        }

        for(int i=10;i>0;i--){
            ll.addLast(i*10);
        }

        ll.display();
         
        // System.out.println(ll.removeLast());
        // System.out.println(ll.removeLast());
        // System.out.println(ll.removeFirst());
        // System.out.println(ll.removeFirst());

        // ll.reverseList();
        // ll.display();

        // System.out.println(ll.midNode());
        // System.out.println(ll.isPalindrome());
        ll.rotateList();

        ll.display();
      

    }


}