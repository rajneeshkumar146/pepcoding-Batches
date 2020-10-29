import java.util.LinkedList;
import java.util.ArrayList;
public class hashmap<K,V>{
    private class Node{
        K key = null;
        V val = null;

        Node(K key,V val){
            this.key = key;
            this.val = val;
        }
    }

    private LinkedList<Node>[] groupArray;
    private int size = 0;
    private int capacity = 0;

    public void assign(int capacity){
        
        this.capacity = capacity;
        this.size = 0;

        groupArray = new LinkedList[capacity];
        for(int i = 0;i < capacity;i++){
            groupArray[i] = new LinkedList<>();
        }
    }

    public hashmap(){
        assign(15);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<capacity;i++){
            LinkedList<Node> gr = groupArray[i];
            int gsize = groupArray[i].size();
            while(gsize-- > 0){
                Node node = groupArray[i].getFirst();
                sb.append("(" + node.key + " = " + node.val + "),");
                gr.addLast(gr.removeFirst());
            }
        }
        String str = sb.toString();
        if(str.length() !=0 )str = str.substring(0,str.length()-1);
        return "[" + str +"]";
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public void put(K key, V val){
        Node node = fondIngroup(key);
        if(node != null){
            node.val = val;
        }else{
            LinkedList<Node> gr = group(key);
            gr.addLast(new Node(key,val));
            this.size++;

            double lambda = gr.size() / (groupArray.length * 1.0);
            if(lambda >= 0.4) rehash();
        }
    }

    public V get(K key){
        Node node = fondIngroup(key);
        return node == null ? null : node.val;
    }

    public boolean containsKey(K key){
        Node node = fondIngroup(key);
        return node != null;
    }

    public V getOrDefault(K key,V defaultVal){
        Node node = fondIngroup(key);
        return node == null ? defaultVal : node.val;
    }

    public V remove(K key){
        Node node = fondIngroup(key);
        if(node == null) return null;
        LinkedList<Node> gr = group(key);
        this.size--;
        return gr.removeFirst().val;
    }

    public ArrayList<K> keySet(){
        ArrayList<K> list = new ArrayList<>();
        for(int i=0;i<capacity;i++){
            LinkedList<Node> gr = groupArray[i];
            int gsize = gr.size();
            while(gsize-- > 0){
                list.add(gr.getFirst().key);
                gr.addLast(gr.removeFirst());
            }
        }
        return list;
    }

    //========================================================================

    private void rehash(){
        LinkedList<Node>[] oldArray = groupArray;
        assign(capacity * 2);

        for(int i=0;i<capacity;i++){
            LinkedList<Node> gr = oldArray[i];
            while(gr.size()!=0){
                Node node = gr.removeFirst();
                put(node.key,node.val);
            }
        }
    }

    private Node fondIngroup(K key){
        LinkedList<Node> gr = group(key);
        int gsize = gr.size();
        while(gsize-- > 0){
            if(gr.getFirst().key == key){
                return gr.getFirst();
            }
            gr.addLast(gr.removeFirst());
        }

        return null;
    }

    private LinkedList<Node> group(K key){
        int gidx = hasing(key);
        return groupArray[gidx];
    }

    private int hasing(K key){
        int val = key.hashCode();
        val = Math.abs(val);
        return val % groupArray.length;
    }
}