import java.util.HashMap;

public class lru {
    class LRUCache {

        private class Node {
            int key, value;
            Node prev, next;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
                this.prev = null;
                this.next = null;
            }
        }

        private HashMap<Integer, Node> map = new HashMap<>();
        private int maxSize = 0;
        private int size = 0;
        private Node head = null, tail = null;

        public LRUCache(int capacity) {
            this.maxSize = capacity;
        }

        public void addLast(Node node){

        }

        public void remove(Node node){

        }

        public void makeRecent(Node node) {

        }

        public int get(int appName) {
            if (!map.containsKey(appName))
                return -1;

            Node node = map.get(appName);
            makeRecent(node);
            return node.value;
        }

        public void put(int appName, int state) {
            if (map.containsKey(appName)) {
                Node node = map.get(appName);
                node.value = state;
                makeRecent(node);
            } else {
                Node node = new Node(appName, state);
                if (this.size == this.maxSize) {
                    remove(this.head);
                }
                addLast(node);
            }

        }
    }
}