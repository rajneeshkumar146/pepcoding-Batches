public class l003_AVL{
    public static void main(String[] args){
        solve();
    }

    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;

        int height = 0;
        int bal = 0;

        Node(int data){
            this.data = data;
        }
    }

    public static void display(Node node){
        if(node==null) return;
        StringBuilder sb = new StringBuilder();
        
        sb.append(node.left!=null?node.left.data+"" : ".");
        sb.append(" <- " + node.data + " -> ");
        sb.append(node.right!=null?node.right.data+"" : ".");

        System.out.println(sb.toString());

        display(node.left);
        display(node.right);
    }

    public static void updateHeightBalance(Node node){  // O(1)
        int lh = -1;  
        int rh = -1;
        if(node.left != null) lh = node.left.height;
        if(node.right != null) rh = node.right.height;

        int bal = lh - rh;
        int height = Math.max(lh,rh) + 1;

        node.height = height;
        node.bal = bal;
    }

    public static Node rightRotation(Node A){
        Node B = A.left;
        Node Bright = B.right;

        B.right = A;
        A.left = Bright;

        updateHeightBalance(A);
        updateHeightBalance(B);

       return B;
    }

    public static Node leftRotation(Node A){
        Node B = A.right;
        Node Bleft = B.left;

        B.left = A;
        A.right = Bleft;

        updateHeightBalance(A);
        updateHeightBalance(B);

       return B;
    }



    public static Node getRotation(Node node){  // O(1)
        updateHeightBalance(node);
        if(node.bal == 2){  // ll,lr
            if(node.left.bal == 1){  // ll
                return rightRotation(node);
            }else{  //lr
                node.left = leftRotation(node.left);
                return rightRotation(node);
            }
        }else if(node.bal == -2){ // rr,rl
            if(node.right.bal == -1){  // rr
                return leftRotation(node);
            }else{  //rl
                node.right = rightRotation(node.right);
                return leftRotation(node);
            }
        }

        return node;
    }

    public static Node addNode(Node node,int data){
        if(node == null) return new Node(data);
        if(data < node.data) node.left = addNode(node.left,data);
        else node.right = addNode(node.right,data);

        
        node = getRotation(node);  // O(1)
        return node;
    }

    public static int minimum(Node node){
        Node curr = node;

        while(curr.left != null)
            curr=curr.left;

        return curr.data;
    }

    public static Node removeData(Node node,int data){
        if(node == null) return null;

        if(data < node.data) node.left = removeData(node.left,data);
        else if(data < node.data) node.right = removeData(node.right,data);
        else{
            if(node.left == null || node.right == null) return  node.left != null? node.left: node.right;

            int minEle = minimum(node.right);
            node.data = minEle;

            node.right = removeData(node.right,minEle);
        }
        
        node = getRotation(node);// O(1)
        return node;
    }

    public static void solve(){
        Node root = null;

        for(int i=1;i<=18;i++) {
           root = addNode(root,i*10);
        //    display(root);

        //    System.out.println("=================================================");
        }

        for(int i=1;i<=18;i++) {
           root = removeData(root,i*10);
           display(root);

           System.out.println("=================================================");
        }
    }

}