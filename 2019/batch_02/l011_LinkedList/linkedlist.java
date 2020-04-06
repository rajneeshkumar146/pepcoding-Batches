import java.util.NoSuchElementException;
public class linkedlist<K>{

    private class Node{
        K data=null;
        Node next=null;

        Node(K data){
            this.data=data;
        }

        @Override
        public String toString(){
             return this.data + "";
        }
    }

    private Node head=null;
    private Node tail=null;
    private int nodeCount=0;

    @Override
    public String toString(){
        if(this.nodeCount==0) return "[]";

        StringBuilder sb=new StringBuilder();
        Node curr=this.head;
       
        sb.append("[");

        while(curr.next!=null){
            sb.append(curr+ ", ");
            curr=curr.next;
        }
        
        sb.append(curr.data);
        sb.append("]");

        return sb.toString();
    }

    //basic funtions.========================================

    public int size(){
        return this.nodeCount;
    }

    public boolean isEmpty(){
        return this.nodeCount==0;
    }

    public Node getNodeAt(int idx){
        if(idx<0 || idx>=this.nodeCount){
            throw new NullPointerException();
        }

        Node curr=this.head;
        while(idx-->0){
            curr=curr.next;
        }        
         
        return curr;
    }
    

    // Add.==================================================

    private void addFirstNode(Node node){
          if(this.nodeCount==0){
              this.tail=node;
          }

          node.next=this.head;
          this.head=node;
          this.nodeCount++;
    }

    public void addFirst(K data){
        Node node=new Node(data);
        addFirstNode(node);
    }

    
    private void addLastNode(Node node){
        if(this.nodeCount==0){
            this.head=node;
            this.tail=node;
        }else{
            this.tail.next=node;
            this.tail=node;
        }
        
        this.nodeCount++;
    }

    public void addLast(K data){
       Node node=new Node(data);
       addLastNode(node);
    }

    private void addAtNode(Node node,int idx){
         if(idx==0) addFirstNode(node);
         else if(idx==this.nodeCount) addLastNode(node);
         else{
             Node prev=getNodeAt(idx-1);
             Node forw=prev.next;

             prev.next=node;
             node.next=forw;
         }
    }

    public void addAt(K data,int idx){
        if(idx<0 || idx > this.nodeCount)  throw new NullPointerException();
        Node node=new Node(data);
        addAtNode(node,idx);
    }

    //get.=================================================

    public K getFirst(){
        if(this.nodeCount==0) throw new NoSuchElementException();
        return this.head.data;
    }

    
    public K getLast(){
        if(this.nodeCount==0) throw new NoSuchElementException();
        return this.tail.data;
    }

    
    public K getAt(int idx){
        return getNodeAt(idx).data; 
    }

    //remove==================================================

    private Node removeFirstNode(){
        Node rn=this.head;

        if(this.nodeCount==1){
           this.head=null;
           this.tail=null;
       }else{
           this.head=this.head.next;
       }

       rn.next=null;
       this.nodeCount--;
       return rn;
    }

    public K removeFirst(){
        if(this.nodeCount==0) throw new NoSuchElementException();
        return removeFirstNode().data;
    }

    
    private Node removeLastNode(){
        Node rn=this.tail;

        if(this.nodeCount==1){
           this.head=null;
           this.tail=null;
       }else{
         Node secondLast=getNodeAt(this.nodeCount-2);
         this.tail=secondLast;
         tail.next=null;      
       }

       rn.next=null;
       this.nodeCount--;
       return rn;
    }

    public K removeLast(){
        if(this.nodeCount==0) throw new NoSuchElementException();
        return removeLastNode().data;
    }

    private Node removeAtNode(int idx){
        if(idx==0) return removeFirstNode();
        else if(idx==this.nodeCount-1) return removeLastNode();
        else{
            //backup
            Node prev=getNodeAt(idx-1);
            Node forw=prev.next.next;
            
            Node rn=prev.next;
            
            rn.next=null;
            prev.next=forw;

            return rn;
        }
    }
 
     public K removeAt(int idx){
        if(idx<0 || idx>=this.nodeCount) throw new NullPointerException();
        return removeAtNode(idx).data;
    }

    //other functions.=====================================================

    public void reverseDataItr(){  //O(n2)  
        int si=0;
        int ei=this.nodeCount-1;

        while(si<ei){
            Node first=getNodeAt(si);
            Node second=getNodeAt(ei);

            K temp=first.data;
            first.data=second.data;
            second.data=temp;
            
            si++;
            ei--;
        }
    }

    private void reverseList(Node node){
        Node prev=null;
        Node curr=node;
        Node forw=null;

        while(curr!=null){
            forw=curr.next;

            curr.next=prev;

            prev=curr;
            curr=forw;
        }

        return prev;
    }
    
    public void revserseList(){

        Node prev=reverseList(this.head);
        this.tail=this.head;
        this.head=prev;
    }

    


}