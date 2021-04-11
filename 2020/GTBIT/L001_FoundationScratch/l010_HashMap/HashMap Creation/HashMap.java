import java.util.LinkedList;

public class HashMap {

    private class Node {
        int key = 0;
        int value = 0;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
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

    public void put(Integer key, Integer value) {

    }

    // if you found return value if not return null.
    public Integer get(Integer key) {

    }

    public Integer remove(Integer key) {

    }

    public boolean containsKey(Integer key) {
        LinkedList<Node> group = group(key);
        int size = group.size();
        while (size-- > 0) {
            if (group.getFirst().key == key)
                return true;
            group.addLast(group.removeFirst());
        }

        return false;
    }

    public ArrayList<Integereger> keySet() {

    }

    public Integer getOrDefault(Integer key, Integer defaultValue) {

    }

    public void putIfAbsent(Integer key, Integer value) {

    }

    private LinkedList<Node> group(int key) {
        int groupNo = groupNo(key);
        return this.buckets[groupNo];
    }

    private Integer groupNo(Integer key) {
        Integer hc = Math.abs(key.hashCode());
        return hc % maxSizeOfBucket;
    }

}