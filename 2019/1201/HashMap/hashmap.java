import java.util.LinkedList;
import java.util.ArrayList;
public class hashmap<K,V>{
  
    public class Node{
       K key=null;
       V value=null;
       
       Node(K key,V value){
           this.key=key;
           this.value=value;
       }
    }

    LinkedList<Node>[] groupArray=new LinkedList[10];
    int nodeCount=0;

    public hashmap(){
        reAssign(10);
    }

    public void reAssign(int size){
        groupArray=new LinkedList[size];
        nodeCount=0;

        for(int i=0;i<size;i++){
           groupArray[i]=new LinkedList<>();
        }
    }

    public int size(){
      return nodeCount;
    }

  public boolean isEmpty(){
      return nodeCount==0;
  }

  public void display(){
    String ans="[";
    for(int i=0;i<groupArray.length;i++){
        LinkedList<Node> group=groupArray[i];

        int size=group.size();
        while(size--> 0){
           Node node=group.getFirst();
           ans+=node.key + "="+ node.value+", ";
           group.addLast(group.removeFirst());
        }
    }
    ans+="]";
    System.out.println(ans);
  }

   public void put(K key,V value){
         Node node=foundNodeInGroup(key);
         if(node!=null){
             node.value=value;  //update
         }else{
            int myGroupIdx=getHashCode(key);
            LinkedList<Node> group=groupArray[myGroupIdx];
            
            Node  nnode=new Node(key,value);
            group.addLast(nnode);
            nodeCount++;

            double lambda=(group.size()*1.0) / nodeCount;
            if(lambda>0.6){
                rehash();
            }

         }
   }

   public void rehash(){
    LinkedList<Node>[] oldData=groupArray;
    reAssign(oldData.length*2);

    for(int i=0;i<oldData.length;i++){
        LinkedList<Node> group=oldData[i];

        int size=group.size();
        while(size--> 0){
           put(group.removeFirst());
        }
    }

   }

    public V get(K key){
      Node node=foundNodeInGroup(key);
      return node!=null?node.value:null;
    }

    public V remove(K key){
        Node node=foundNodeInGroup(key);
        if(node!=null){
            int myGroupIdx=getHashCode(key);
            LinkedList<Node> group=groupArray[myGroupIdx];

            group.removeFirst(); 
            nodeCount--;
        }

        return node!=null?node.value:null;
    }

    public boolean containsKey(K key){
        Node node=foundNodeInGroup(key);
        return node!=null?true:false;
    }


    public ArrayList<K> keySet(){
         ArrayList<K> ans=new ArrayList<>();
         for(int i=0;i<groupArray.length;i++){
             LinkedList<Node> group=groupArray[i];

             int size=group.size();
             while(size--> 0){
                Node node=group.getFirst();
                ans.add(node.key);
                group.addLast(group.removeFirst());
             }
         }

         return ans;
    }

    public Node foundNodeInGroup(K key){
        int myGroupIdx=getHashCode(key);
        LinkedList<Node> group=groupArray[myGroupIdx];

        if(group==null) return null;
        
        Node rn=null;
        int size=group.size();
        while(size--> 0){   //O(lambda) === O(1)
            Node node=group.getFirst();
            if(node.key==key) {
                rn=node;
                break;
            }

            group.addLast(group.removeFirst());
        }
        return rn;
    }


    public int getHashCode(K key){
        int hc=key.hashCode();
        return Math.abs(hc)%groupArray.length;
    } 

}