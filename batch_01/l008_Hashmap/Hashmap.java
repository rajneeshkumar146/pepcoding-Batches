import java.util.*;

public class Hashmap<K, V> {

    public class Node {
        K key;
        V val;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return this.key + "=" + this.val;
        }
    }

    int size = 0;
    LinkedList<Node>[] buckets = new LinkedList[10];

    Hashmap() {
        for (Integer i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i].size() > 0) {
                int size = buckets[i].size();
                LinkedList<Node> group = buckets[i];
                while (size-- > 0) {
                    Node tnode = group.getFirst();
                    sb.append(tnode.toString() + ", ");
                    group.addLast(group.removeFirst());
                }
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

    public void put(K key, V val) {
        int code = MyhashCode(key);
        LinkedList<Node> group = buckets[code];

        Node rn = foundInGroup(group, key);
        if (rn != null) {
            rn.val = val;
        } else {

            Node node = new Node(key, val);
            group.addFirst(node);
            this.size++;

            double lambda = group.size() * 1.0 / buckets.length;
            if (lambda >= 1.0) {
                rehash();
            }
        }
    }

    public void rehash() {
        LinkedList<Node>[] oldBuckets = buckets;
        buckets = new LinkedList[buckets.length * 2];

        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }

        for (int i = 0; i < oldBuckets.length; i++) {
            if (oldBuckets[i].size() > 0) {
                int size = oldBuckets[i].size();
                LinkedList<Node> group = oldBuckets[i];
                while (size-- > 0) {
                    Node tnode = group.removeFirst();
                    put(tnode.key, tnode.val);
                }
            }
        }
    }

    public Node get(K key) {
        int code = MyhashCode(key);
        LinkedList<Node> group = buckets[code];

        Node rn = foundInGroup(group, key);
        return rn;
    }

    public Node remove(K key) throws Exception {
        int code = MyhashCode(key);
        LinkedList<Node> group = buckets[code];

        Node rn = foundInGroup(group, key);
        if (rn != null) {
            this.size--;
            return group.removeFirst();
        } else {
            throw new Exception("ElementNotFound: -1");
        }

    }

    public boolean containsKey(K key) {
        int code = MyhashCode(key);
        LinkedList<Node> group = buckets[code];

        Node rn = foundInGroup(group, key);
        return rn != null ? true : false;

    }

    public ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList<>();

        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i].size() > 0) {
                int size = buckets[i].size();
                LinkedList<Node> group = buckets[i];
                while (size-- > 0) {
                    Node tnode = group.getFirst();
                    keys.add(tnode.key);
                    group.addLast(group.removeFirst());
                }
            }
        }
        return keys;
    }

    public Node foundInGroup(LinkedList<Node> bucket, K key) {
        Node rn = null;
        int size = bucket.size();
        while (size-- > 0) {
            Node tnode = bucket.getFirst();
            if (tnode.key == key) {
                rn = tnode;
                break;
            }

            bucket.addLast(bucket.removeFirst());
        }
        return rn;
    }

    public int MyhashCode(K key) {
        int code = key.hashCode();
        return Math.abs(code) % buckets.length;
    }

}