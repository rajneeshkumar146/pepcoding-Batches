import java.util.ArrayList;
import java.util.LinkedList;

public class HashMap<K, V> {

    private class Node {
        K key = null;
        V value = null;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    private LinkedList<Node>[] buckets;
    private int NoOFElements = 0;
    private int maxSizeOfBucket = 0;

    private void intialize(int size) {
        buckets = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new LinkedList<>();
        }

        this.maxSizeOfBucket = size;
        this.NoOFElements = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int sizeOfMap = this.NoOFElements;
        for (int i = 0; i < this.maxSizeOfBucket; i++) {
            LinkedList<Node> group = this.buckets[i];
            int size = group.size();
            while (size-- > 0) {
                sb.append(group.getFirst());
                if (sizeOfMap > 1)
                    sb.append(",");

                group.addLast(group.removeFirst());
                sizeOfMap--;
            }
        }

        sb.append("]");

        return sb.toString();
    }

    public HashMap() {
        intialize(10);
    }

    public Integer size() {
        return this.NoOFElements;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    private void rehash() {
        LinkedList<Node>[] temp = this.buckets;
        intialize(2 * this.maxSizeOfBucket);
        for (int i = 0; i < temp.length; i++) {
            LinkedList<Node> group = temp[i];
            int size = group.size();
            while (size-- > 0) {
                Node node = group.removeFirst();
                put(node.key, node.value);
            }
        }
    }

    public void put(K key, V value) {
        LinkedList<Node> group = group(key);
        boolean res = containsKey(key);
        if (res) {
            group.getFirst().value = value;
        } else {
            Node node = new Node(key, value);
            group.addLast(node);
            this.NoOFElements++;

            double lambda = (0.4 * this.maxSizeOfBucket);
            if (group.size() >= lambda)
                rehash();
        }
    }

    // if you found return value if not return null.
    public V get(K key) {
        LinkedList<Node> group = group(key);
        boolean res = containsKey(key);
        if (res)
            return group.getFirst().value;

        return null;
    }

    public V remove(K key) {
        LinkedList<Node> group = group(key);
        boolean res = containsKey(key);
        if (res) {
            this.NoOFElements--;
            return group.removeFirst().value;
        }
        return null;
    }

    public boolean containsKey(K key) {
        LinkedList<Node> group = group(key);
        int size = group.size();
        while (size-- > 0) {
            if (group.getFirst().key == key)
                return true;
            group.addLast(group.removeFirst());
        }
        return false;
    }

    private void allkeysOfGroup(LinkedList<Node> group, ArrayList<K> ans) {
        int size = group.size();
        while (size-- > 0) {
            ans.add(group.getFirst().key);
            group.addLast(group.removeFirst());
        }
    }

    public ArrayList<K> keySet() {
        ArrayList<K> ans = new ArrayList<>();
        for (int i = 0; i < this.maxSizeOfBucket; i++) {
            allkeysOfGroup(this.buckets[i], ans);
        }

        return ans;
    }

    public V getOrDefault(K key, V defaultValue) {
        V value = get(key);
        if (value == null)
            return defaultValue;
        return value;
    }

    public void putIfAbsent(K key, V value) {
        LinkedList<Node> group = group(key);
        boolean res = containsKey(key);
        if (!res)
            put(key, value);
    }

    private LinkedList<Node> group(K key) {
        int groupNo = groupNo(key);
        return this.buckets[groupNo];
    }

    private Integer groupNo(K key) {
        Integer hc = Math.abs(key.hashCode());
        return hc % maxSizeOfBucket;
    }

}