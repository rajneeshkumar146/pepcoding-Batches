import java.util.HashMap;

public class LRUCache {

    // data Members

    private class ListNode {
        Integer key, value;
        ListNode next = null;
        ListNode prev = null;

        ListNode(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    private HashMap<Integer, ListNode> map;
    private int capacity;
    private int size;
    private ListNode head = null;
    private ListNode tail = null;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.map = new HashMap<>();
    }

    private void addLast(ListNode node) {

    }

    private void remove(ListNode node) {

    }

    private void makeRecent(ListNode node) {
        remove(node);
        addLast(node);
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;

        ListNode node = map.get(key);
        makeRecent(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            node.value = value;
            makeRecent(node);
        } else {
            ListNode node = new ListNode(key, value);
            if (this.size == this.capacity) {
                remove(this.head);
            }

            addLast(node);
        }
    }
}