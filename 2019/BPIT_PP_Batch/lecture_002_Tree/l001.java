public class l001{

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

    static int idx = 0;
    public static Node constructTree(int[] arr){
        if(idx==arr.length || arr[idx]==-1){
            idx++;
            return null;
        }

        Node node=new Node(arr[idx++]);

        node.left = constructTree(arr);
        node.right = constructTree(arr);

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
        return node==null? 0 : size(node.left) + size(node.right) + 1; 
    }

    public static int height(Node node){
        return node==null? -1 : Math.max(height(node.left) , height(node.right)) + 1; 
    }

    public static void preOrder(Node node){
        if(node == null) return ;

        System.out.print(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void InOrder(Node node){
        if(node == null) return ;

        InOrder(node.left);
        System.out.print(node.data);
        InOrder(node.right);
    }

    public static void postOrder(Node node){
        if(node == null) return ;

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data);
    }

    // Set1.===========================================================================

    public static int diameter(Node node){
        if(node==null) return 0;

        int ld = diameter(node.left);
        int rd = diameter(node.right);
        
        int lh = height(node.left);
        int rh = height(node.right);

        return Math.max(Math.max(ld,rd),lh+rh+2);
    }
    
    // d, h
    public static int[] diameter_02(Node node){
        if(node==null) return new int[]{0,-1};
        
        int[] lr = diameter_02(node.left);   // left result
        int[] rr = diameter_02(node.right);  // right result

        int dia = Math.max(Math.max(lr[0],rr[0]), lr[1] + rr[1] + 2);
        int height = Math.max(lr[1],rr[1]) + 1;
       
        return new int[]{dia , height};
    }

    
    public static int[] diameter_03(Node node,int[] res){
        if(node==null) return -1;
        
        int lh = diameter_03(node.left,res);   // left result
        int rh = diameter_03(node.right,res);  // right result

        res[0] = Math.max(res[0],lh + rh + 2);

        return Math.max(lh,rh) + 1;
    }

    public static void diameter(){
        int[] res=new int[1];
        
    }

    // https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/

    public int LeafToLeafAns = -(int)1e8;
    public int leafToLeafSum_(Node node){
        if(node == null) return -(int)1e8;
        if(node.left == null && node.right == null) return node.data;

        int lMax = leafToLeafSum_(node.left); 
        int rMax = leafToLeafSum_(node.right);
        
        if(node.left != null && node.right != null){
            LeafToLeafAns = Math.max(LeafToLeafAns, lMax + node.data +rMax);
        }

        return Math.max(lMax,rMax) + node.data;
    }

    int maxPathSum(Node root)
    { 
        leafToLeafSum_(root);
        return LeafToLeafAns;
    } 


    



    




    public static void solve(){
        int[] arr={10,20,40,-1,-1,50,80,-1,-1,90,-1,-1,30,60,100,-1,-1,-1,70,110,-1,-1,120,-1,-1};
        Node root = constructTree(arr);
        display(root);
    }
}