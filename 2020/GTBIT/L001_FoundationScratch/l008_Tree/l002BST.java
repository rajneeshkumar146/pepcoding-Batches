public class l002BST {
    public static class Node {
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node constructTree(int[] arr, int si, int ei) {
        if (si > ei)
            return null;

        int mid = (si + ei) / 2;
        Node node = new Node(arr[mid]);

        node.left = constructTree(arr, si, mid - 1);
        node.right = constructTree(arr, mid + 1, ei);

        return node;
    }

    public static Node constructTree(int[] arr){
        return constructTree(arr, 0, arr.length - 1);
    }

    public static int size(Node node){

    }

    public static int height(Node node){

    }

    public static int maximum(Node node){

    }

    public static int minimum(Node node){

    }

    public static boolean find(Node node,int data){

    }




}