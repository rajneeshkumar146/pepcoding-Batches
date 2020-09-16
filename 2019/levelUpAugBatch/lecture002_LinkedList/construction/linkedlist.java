public class linkedlist{
        protected class Node{
            int data = 0;
            Node next = null;

            Node(int data){
                this.data = data;
            }
        }

        private Node head = null;
        private Node tail = null;
        private int elementCount = 0;

        //basic.============================================

        public int size(){
            return this.elementCount;
        }

        public boolean isEmpty(){
            return this.elementCount == 0;
        }

        @Override
        public String toString(){
            Node curr = this.head;
            StringBuilder sb = new StringBuilder();
            sb.append("[");

            while(curr!=null){
                sb.append(curr.data);
                if(curr.next!=null) sb.append(",");
                curr=curr.next;
            }
            
            sb.append("]");
            return sb.toString();
        }

        //add.===============================================

        protected void addFirstNode(Node node){
            if(this.head==null){
                this.head = node;
                this.tail = node;
            }else{
                node.next = this.head;
                this.head = node;
            }

            this.elementCount++;
        }

        public void addFirst(int val){
           Node node = new Node(val);
           addFirstNode(node);
        }

        protected void addLastNode(Node node){
            if(this.head==null){
                this.head = node;
                this.tail = node;
            }else{
                this.tail.next = node;
                this.tail = node;
            }

            this.elementCount++;
        }

        public void addLast(int val){
            Node node = new Node(val);
            addLastNode(node);
        }

        protected void addNodeAt(Node node,int idx){
            if(idx==0) addFirstNode(node);
            else if(idx==this.elementCount) addLastNode(node);
            else{
                Node prev = getNodeAt(idx - 1);
                Node curr = prev.next;

                prev.next = node;
                node.next = curr;

                this.elementCount++;
            }
        }

        public void addAt(int data,int idx) throws Exception{
            if(idx < 0 || idx > this.elementCount){
                throw new Exception("invalidLocation: " + idx);
            }

            Node node = new Node(data);
            addNodeAt(node,idx);
        }

        //remove.============================================
        protected Node removeFirstNode(){
            Node node = this.head;
            if(this.elementCount == 1){
                this.head = null;
                this.tail = null;
            }else{
                this.head = this.head.next;
                node.next = null;
            }

            this.elementCount--;
            return node;
        }

        public int removeFirst() throws Exception{
            if(this.elementCount == 0){
                throw new Exception("NullPointerException: -1");
            }

            Node node = removeFirstNode();
            int rv = node.data;
            return rv;
        }

        protected Node removeLastNode(){
            Node node = this.tail;
            if(this.elementCount == 1){
                this.head = null;
                this.tail = null;
            }else{
                Node prev = getNodeAt(this.elementCount - 2);
                this.tail = prev;
                prev.next = null;
            }

            this.elementCount--;
            return node;
        }

        public void removeLast()throws Exception{
            if(this.elementCount == 0){
                throw new Exception("NullPointerException: -1");
            }

            Node node = new Node(val);
            int rv = node.data;
            return rv;
        }

        protected Node removeNodeAt(int idx){
            if(idx==0) return removeFirstNode();
            else if(idx==this.elementCount - 1) return removeLastNode();
            else{
                
                Node prev = getNodeAt(idx - 1);
                Node curr = prev.next;

                prev.next = curr.next;
                curr.next = null;

                this.elementCount--;
                return curr;
            }
        }

        public int removeAt(int idx) throws Exception{
            if(idx < 0 || idx >= this.elementCount){
                throw new Exception("invalidLocation: " + idx);
            }

            Node node = removeNodeAt(idx);
            int rv = node.data;

            return rv;
        }


        //get.================================================
        protected Node getFirstNode(){
            return this.head;
        }

        public int getFirst() throws Exception{
            if(this.elementCount == 0){
                throw new Exception("NullPointerException: -1");
            }

            Node node = getFirstNode();
            return node.data;
        }

        protected Node getLastNode(){
            return this.tail;
        }

        public int getLast() throws Exception{
            if(this.elementCount == 0){
                throw new Exception("NullPointerException");
            }

            Node node = getLastNode();
            return node.data;
        }

        protected Node getNodeAt(int idx){
            Node curr = this.head;
            
            while(idx-- > 0){
                curr= curr.next;
            }

            return curr;
        }

        public int getAt(int idx)  throws Exception{
            if(idx  < 0 || idx >= this.elementCount){
                throw new Exception("invalidLocation: " + idx);
            }

            Node node = getNodeAt(idx);
            return node.data;
        }
}