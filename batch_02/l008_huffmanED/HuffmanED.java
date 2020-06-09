import java.util.PriorityQueue;
import java.util.HashMap;
public class HuffmanED{
    private static HashMap<String,String> encode=new HashMap<>();
    private static HashMap<String,String> decode=new HashMap<>();

    private class Node implements Comparable<Node>{
        String data="";
        int freq=0;
        Node left=null;
        Node right=null;

        public Node(String data,int freq,Node left,Node right){
            this.data=data;
            this.freq=freq;
            this.left=left;
            this.right=right;
        }

        @Override
        public int compareTo(Node o){
            return this.freq-o.freq;
        }
    }

    public HuffmanED(String str){
        HuffManTree(str);
    }

    private void HuffManTree(String str){
        int[] freqMap=new int[26];
        for(int i=0;i<str.length();i++){
            freqMap[str.charAt(i)-'a']++;
        }

        PriorityQueue<Node> pq=new PriorityQueue<>();
        for(int i=0;i<freqMap.length;i++){
            if(freqMap[i]>0) pq.add(new Node((char)(i+'a')+"", freqMap[i],null,null));
        }

        while(pq.size()!=1){
            Node one=pq.poll();
            Node two=pq.poll();
            pq.add(new Node("",one.freq+two.freq,one,two));
        }

        Node root=pq.poll();
        HuffmanTraversal(root,"");

    }
    
    private void HuffmanTraversal(Node root,String Binarycode){
      if(root.left==null && root.right==null) {
        encode.put(root.data,Binarycode);
        decode.put(Binarycode,root.data);
        return;
      }

      HuffmanTraversal(root.left,Binarycode+"0");
      HuffmanTraversal(root.right,Binarycode+"1");
    }

    public String encodeString(String str){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<str.length();i++){
            String s=str.charAt(i)+"";
            sb.append(encode.get(s));
        }

        return sb.toString();
    }

    public String decodeString(String str){
        StringBuilder sb=new StringBuilder();
        int i=0;
        for(int j=1;j<=str.length();j++){
            String s=str.substring(i,j);
            if(decode.containsKey(s)){
                sb.append(decode.get(s));
                i=j;
            }
        }

        return sb.toString();
    }
}