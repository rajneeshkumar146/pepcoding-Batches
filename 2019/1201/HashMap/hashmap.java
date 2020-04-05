import java.util.LinkedList;
import java.util.ArrayList;
public class hashmap{
  
    public class Node{
       Integer key=null;
       Integer value=null;
       
       Node(Integer key,Integer value){
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

   public void put(Integer key,Integer value){
         Node node=foundNodeInGroup(key);
         if(node!=null){
             node.value=value;  //update
         }else{
            int myGroupIdx=getHashCode(key);
            LinkedList<Node> group=groupArray[myGroupIdx];
            
            Node  nnode=new Node(key,value);
            group.addLast(nnode);
            nodeCount++;
         }
   }

    public Integer get(Integer key){
      Node node=foundNodeInGroup(key);
      return node!=null?node.value:null;
    }

    public Integer remove(int key){
        Node node=foundNodeInGroup(key);
        if(node!=null){
            int myGroupIdx=getHashCode(key);
            LinkedList<Node> group=groupArray[myGroupIdx];

            group.removeFirst(); 
            nodeCount--;
        }

        return node!=null?node.value:null;
    }

    public boolean containsKey(Integer key){
        Node node=foundNodeInGroup(key);
        return node!=null?true:false;
    }


    public ArrayList<Integer> keySet(){
         ArrayList<Integer> ans=new ArrayList<>();
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

    public Node foundNodeInGroup(Integer key){
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


    public int getHashCode(Integer key){
        int hc=key.hashCode();
        return Math.abs(hc)%groupArray.length;
    } 

}