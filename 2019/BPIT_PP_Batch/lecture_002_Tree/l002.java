import java.util.ArrayList;
public class l002{
    public static void main(String[] args){
        solve();
    }

    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data){
            this.data = data;
        }
    }

    //Basic.==================================================================================

    public static Node constructBST(ArrayList<Integer> arr,int si,int ei){
        if(si>ei) return null; 

        int mid = (si + ei ) >> 1;
        Node node = new Node(arr.get(mid));

        node.left = constructBST(arr,si,mid - 1);
        node.right = constructBST(arr,mid + 1, ei);
        return node;
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

    public static int size(Node node){
        return node == null ? 0 : size(node.left) + size(node.right) + 1; 
    }

    public static int height(Node node){
        return node == null ? -1 : Math.max(height(node.left) , height(node.right)) + 1; 
    }

    public static boolean find(Node node,int data){
        Node curr = node;
        while(curr != null){
            if(curr.data == data) return true;
            else if(curr.data < data) curr = curr.right;
            else curr = curr.left;
        }

        return false;
    }

    public static int minimum(Node node){
        Node curr = node;

        while(curr.left != null)
            curr=curr.left;

        return curr.data;
    }

    public static int maximum(Node node){
        Node curr = node;

        while(curr.right != null)
            curr=curr.right;

        return curr.data;
    }

    static int idx = 0;
    public static Node BSTUsingPreOrder(int[] arr,int lRange, int rRange){
        if(idx >= arr.length || arr[idx] < lRange  ||  arr[idx] > rRange) return null;

        Node node = new Node(arr[idx++]);

        node.left = BSTUsingPreOrder(arr,lRange, node.data);
        node.right = BSTUsingPreOrder(arr,node.data,rRange);

        return node;
    }

    
    public static int BSTPreOrderHeight(int[] arr,int lRange, int rRange){
        if(idx >= arr.length || arr[idx] < lRange  ||  arr[idx] > rRange) return -1;

        int ele = arr[idx++];
        int lh = BSTPreOrderHeight(arr,lRange, ele);
        int rh = BSTPreOrderHeight(arr,ele,rRange);

        return Math.max(lh,rh)+1;
    }

    //for you: popstOrder, LevelOrder.

    public static void PredSucc(Node node,int data){

        Node curr = node;
        Node pred = null, Succ = null;

        while(curr!=null){

            if(curr.data == data){

                if(curr.left != null){
                    pred = curr.left;
                    while(pred.right != null) pred = pred.right;
                }

                if(curr.right != null){
                    succ = curr.right;
                    while(succ.left != null) succ = succ.left;
                }


                break;
            }else if(curr.data < data){
                pred = curr;
                curr= curr.right;
            }else{
                succ = curr;
                curr= curr.left;
            }
        }
    }

    // In BST, ceil and floor are the pred and Succ respectively..

    public static void BSTUsingPreOrder(){
        int[] arr = {7,3,1,0,2,6,4,5,12,9,8,11,10,13,15,14};
        // display(BSTUsingPreOrder(arr,-(int)1e8,(int)1e8));
        System.out.println(BSTPreOrderHeight(arr,-(int)1e8,(int)1e8));
    }

    public static Node addNode(Node node,int data){
        if(node == null) return new Node(data);
        if(data < node.data) node.left = addNode(node.left,data);
        else node.right = addNode(node.right,data);

        return node;
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

        return node;
    }





    public static void solve(){
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 1; i <= 15; i++) arr.add(i*10);

        Node root = constructBST(arr,0,arr.size()-1);

        // display(root);
        BSTUsingPreOrder();
    }

}