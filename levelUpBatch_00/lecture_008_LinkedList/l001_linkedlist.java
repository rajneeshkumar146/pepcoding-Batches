import java.util.NoSuchElementException;
public class l001_linkedlist {

	public static class linkedlist {

		private class Node {
			int data = 0;
			Node next = null;

			public Node(int data) {
				this.data = data;
			}

            @Override
			public String toString() {
				return this.data + "";
			}
		}

		private Node head = null;
		private Node tail = null;
		private int size = 0;

		//generalUtil.======================================

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			Node node = this.head;

			while (node.next != null) {
				sb.append(node + " -> ");
				node = node.next;
			}

			sb.append(node);
			return sb.toString();
		}

		public int size() {
			return this.size;
		}

		public boolean isEmpty() {
			return this.size == 0;
		}

		private Node getNodeAt(int idx) {
			if (idx<0 || idx >= this.size) throw new NullPointerException();

			if (idx == 0) return this.head;
			else if (idx == this.size - 1) return this.tail;

			Node node = this.head;
			while (idx--> 0) {
				node = node.next;
			}

			return node;
        }
        
        private Node getMidNode(){
            Node slow=this.head;
            Node fast=this.head;

            while(fast!=null && fast.next!=null){
                slow=slow.next;
                fast=fast.next.next;
            }
            return slow;
        }

        private void swapData(Node a,Node b){
            int temp=a.data;
            a.data=b.data;
            b.data=temp;
        }

		//add.============================================

		private void addFirstNode(Node node) {
			if (this.size == 0) {
				this.tail = node;
			} else
				node.next = this.head;

			this.head = node;
			this.size++;
		}

		public void addFirst(int data) {
			Node node = new Node(data);
			addFirstNode(node);
		}

		private void addLastNode(Node node) {
			if (this.size == 0) {
				this.head = node;
			} else {
				this.tail.next = node;
			}

			this.tail = node;
			this.size++;
		}

		public void addLast(int data) {
			Node node = new Node(data);
			addLastNode(node);
		}

		private void addAtNode(Node node, int idx) {
			if (idx == 0) addFirstNode(node);
			else if (idx == this.size) addLastNode(node);
			else {
				Node nodeAt = getNodeAt(idx - 1);
				node.next = nodeAt.next;
				nodeAt.next = node;
				this.size++;
			}
		}

		public void addAt(int data, int idx) {
			Node node = new Node(data);
			addAtNode(node, idx);
		}

		//get.===============================================

		public int getFirst() {
			if (this.size() == 0) throw new NoSuchElementException();
			return this.head.data;
		}

		public int getLast() {
			if (this.size() == 0) throw new NoSuchElementException();
			return this.tail.data;
		}

		public int getAt(int idx) {
			if (this.size() == 0) throw new NoSuchElementException();
			return getNodeAt(idx).data;
		}

		//remove.=============================================
		private Node removeFirstNode() {
			if (this.size == 0) throw new NoSuchElementException();

			Node node = this.head;
			this.head = node.next;
			node.next = null;

			if (this.size == 1) this.tail = null;

			this.size--;
			return node;

		}

		public int removeFirst() {
			return removeFirstNode().data;
		}

		private Node removeLastNode() {
			if (this.size == 0) throw new NoSuchElementException();

			Node rn = null;
			if (this.size == 1) {
				rn = this.head;
				this.head = null;
				this.tail = null;
			} else {
				Node node = getNodeAt(this.size - 2);

				rn = this.tail;
				this.tail = node;
				this.tail.next = null; //node.next=null;
			}

			this.size--;
			return rn;
		}

		public int removeLast() {
			return removeLastNode().data;
		}

		private Node removeAtNode(int idx) {
			if (this.size == 0) throw new NoSuchElementException();

			if (idx == 0) return removeFirstNode();
			else if (idx == this.size - 1) return removeLastNode();

			Node node = getNodeAt(idx - 1);
			Node rn = node.next;

			node.next = rn.next;
			rn.next = null;

			this.size--;
			return rn;
		}

		public int removeAt(int idx) {
			return removeAtNode(idx).data;
		}

        //Question_set1.======================================================
        
        public int getMid(){    //O(n)
            return getMidNode().data;
        }

        public void reverseDataItr(){  
            int i=0;
            int j=this.size-1;
            while(i<j){
                Node left=getNodeAt(i);
                Node right=getNodeAt(j);
                swapData(left,right);
                i++;
                j--;
            }
        }

        public void recersePointerItr(){
            Node prev=null;
            Node curr=this.head;
            Node forw=null
            while(curr!=null){
                forw=curr.next;
                curr.next=prev;

                prev=curr;
                curr=forw;
            }

            this.tail=this.head;
            this.head=prev;
        }

        Node sNode=this.head;
        private void reverseDataRecursive(Node node,int level){
            if(node==null) return;

            reverseDataRecursive(node.next,level+1);
            if(level>=this.size/2){
                swapData(sNode,node);
                sNode=sNode.next;
            }
        }
	}

	public static void solve() {
		linkedlist ll = new linkedlist();
		for (int i = 1; i<= 13; i++) {
			ll.addFirst(i * 10);
		}

		// ll.addAt(150,10);

		System.out.println(ll);
	}

	public static void main(String[] args) {
		solve();
	}
}