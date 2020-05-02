import java.util.LinkedList;
import java.util.ArrayList;

public class HashMap_<K,V>{

    public class Node{
        K key;
        V value;

        public Node(K key,V value){
            this.key=key;
            this.value=value;
        }

        @Override
        public String toString(){
          return this.key + "=" + this.value;
        }
    }

    private int size=0;
    private LinkedList<Node>[] buckets=new LinkedList[10];  // int[] buckets=new int[10];

    public HashMap_(){
        reAssign();
    }

    private void reAssign(){
        for(int i=0;i<buckets.length;i++){
            buckets[i]=new LinkedList<>();
        }
        this.size=0;
    }

    @Override
    public String toString(){
        StringBuilder sb= new StringBuilder();
        sb.append("{");
       
        for(int i=0;i<buckets.length;i++){
            if(buckets[i].size()>0){
                
                LinkedList<Node> group=buckets[i];
                int size=group.size();

                while(size-->0){
                    Node tempNode=group.getFirst();
                    sb.append(tempNode + ",");
                    group.addLast(group.removeFirst());
                }

            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("}");
        return sb.toString();
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size==0;
    }


    public void put(K key,V value){
         int code=myGroup(key);
         LinkedList<Node> group=buckets[code];

        //  if(group==null) buckets[code]=new LinkedList<>();

         Node rn=foundInGroup(group,key);

         if(rn!=null){
             rn.value=value;
         }else{
             Node node=new Node(key,value);
             group.addLast(node);
             this.size++;

             double lambda=group.size()*1.0/buckets.length;
             if(lambda>=0.7){
                 System.out.println("Rehas: "+ group.size());
                 rehash();
             }
         }
    }

    public void rehash(){
        LinkedList<Node>[] oldBuckets=buckets;
        buckets=new LinkedList[oldBuckets.length*2];
        reAssign();
        
        for(int i=0;i<oldBuckets.length;i++){
            if(oldBuckets[i].size()>0){
                LinkedList<Node> group=oldBuckets[i];
                int size=group.size();

                while(size-->0){
                 Node tnode=group.removeFirst();
                 put(tnode.key,tnode.value);
                }
                 
            }

        }
    }

    public V get(K key){
         int code=myGroup(key);
         LinkedList<Node> group=buckets[code];
         Node rn=foundInGroup(group,key);

         return rn==null?null:rn.value;
    }

    public Node remove(K key){
     int code=myGroup(key);
     LinkedList<Node> group=buckets[code];
     Node rn=foundInGroup(group,key);

     if(rn!=null){
         this.size--;
         return group.removeFirst();
     }else{
         System.out.println("ElementNotFound: -1");
     }

     return null;
    }

    public boolean containsKey(K key){
     int code=myGroup(key);
     LinkedList<Node> group=buckets[code];
     Node rn=foundInGroup(group,key);

     return rn!=null? true:false;

    }

    private Node foundInGroup(LinkedList<Node> group,K key){
     if(group==null) return null;
     Node rn=null;
     int size=group.size();
     while(size--> 0){
         Node tempNode=group.getFirst();
         if(tempNode.key==key){
             rn=tempNode;
             break;
         }

         group.addLast(group.removeFirst());
     }

     return rn;
    }

    public ArrayList<K> keySet(){
       ArrayList<K> ans=new ArrayList<>();
        for(int i=0;i<buckets.length;i++){
            if(buckets[i].size()>0){
                
                LinkedList<Node> group=buckets[i];
                int size=group.size();

                while(size-->0){
                    Node tempNode=group.getFirst();
                    ans.add(tempNode.key);
                    group.addLast(group.removeFirst());
                }
            }
        }

        return ans;
    }

    public V getOrDefault(K key,V defaultValue){
        int code=myGroup(key);
        LinkedList<Node> group=buckets[code];
        Node rn=foundInGroup(group,key);

        return rn!=null?rn.value:defaultValue;
    }


    public int myGroup(K key){
        int val=key.hashCode();
        return Math.abs(val)%buckets.length;
    }

    // public int MyHashCode(Integer key){
    //     int val=key.hashCode();
    //     return Math.abs(val);
    // }





}