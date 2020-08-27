public class questionStack{
    class LRUCache
    {
    
        class Node
        {
            int key = 0;
            int value = 0;
    
            Node next = null;
            Node prev = null;
    
            Node(int key, int value)
            {
                this.key = key;
                this.value = value;
            }
        }
    
        Node head = null;
        Node tail = null;
        int size = 0;
        int maxSize = 0;
    
        void addLast(Node node)
        {
            if (this.size == 0)
            {
                this.head = node;
                this.tail = node;
            }
            else
            {
                this.tail.next = node;
                node.prev = this.tail;
                this.tail = node;
            }
            this.size++;
        }
    
        void removeNode(Node node)
        {
    
            if (this.size == 1)
            {
                this.head = node;
                this.tail = node;
            }
            else if (node.prev == null)
            {
                this.head = node.next;
    
                this.head.prev = null;
                node.next = null;
            }
            else if (node.next == null)
            {
                this.tail = node.prev;
    
                this.tail.next = null;
                node.prev = null;
            }
            else
            {
                Node prev = node.prev;
                Node next = node.next;
    
                prev.next = next;
                next.prev = prev;
    
                node.next = null;
                node.prev = null;
            }
            this.size--;
        }
    
        HashMap<Integer, Node> map=new HashMap<>(); // key, node
        LRUCache(int capacity)
        {
            this.maxSize = capacity;
        }
    
        int get(int key)
        {
            if (!map.containsKey(key))
                return -1;
    
            Node node = map.get(key);
            int rv = node.value;
    
            removeNode(node);
            addLast(node);
    
            return rv;
        }
    
        void put(int key, int value)
        {
            if (!map.containsKey(key)){
                Node node = new Node(key, value);
                map.put(key , node);
                addLast(node);
                if(this.size > this.maxSize){
                   node = this.head;
                    
                   map.remove(node.key);
                   removeNode(node);  
                }
            }
            else
            {
                int val = get(key);
                if (val != value)
                    map.get(key).value = value;
            }
        }
    }
}