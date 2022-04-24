import java.util.*;

class AllOne {

    private class Node {
        Node prev, next;
        int count = 0;
        List<String> list;

        Node(String key, int count) {
            this.count = count;
            list = new ArrayList<>();
            this.list.add(key);
        }
    }

    private HashMap<String, Node> map;
    private Node head = null, tail = null;

    public AllOne() {
        this.map = new HashMap<>();
    }

    private void addAtHead(Node node) {
        if (this.head == null)
            this.head = this.tail = node;
        else {
            this.head.next = node;
            node.prev = this.head;
            this.head = node;
        }
    }

    private void addAtTail(Node node) {
        if (this.head == null)
            this.head = this.tail = node;
        else {
            node.next = this.tail;
            this.tail.prev = node;
            this.tail = node;
        }
    }

    private void addNext(Node currNode, Node node) {
        if (currNode == this.head)
            addAtHead(node);
        else {
            Node nextNode = currNode.next;
            currNode.next = node;
            node.prev = currNode;

            node.next = nextNode;
            nextNode.prev = node;
        }
    }

    public void inc(String key) {
        if (map.size() == 0) {
            Node node = new Node(key, 1);
            map.put(key, node);
            this.head = this.tail = node;
        } else if (map.containsKey(key)) {
            Node node = map.get(key);
            Node nextNode = node.next;
            if (nextNode == null) {
                Node newNode = new Node(key, node.count + 1);
                addAtHead(node);
                map.put(key, newNode);
            } else if (nextNode.count == node.count + 1) {
                map.put(key, nextNode);
                List<String> list = nextNode.list;
                list.add(key);
            } else {
                Node newNode = new Node(key, node.count + 1);
                addNext(node, newNode);
                map.put(key, newNode);
            }

            List<String> list = node.list;
            list.remove(key);
        } else {
            if (this.tail.count == 1) {
                map.put(key, this.tail);
                List<String> list = this.tail.list;
                list.add(key);
            } else {
                Node newNode = new Node(key, 1);
                addAtTail(newNode);
                map.put(key, newNode);
            }
        }
    }

    public void dec(String key) {

    }

    public String getMinKey() {
        if (this.tail == null)
            return "";
        List<String> list = this.tail.list;
        return list.get(list.size() - 1);
    }

    public String getMaxKey() {
        if (this.head == null)
            return "";
        List<String> list = this.head.list;
        return list.get(0);
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */