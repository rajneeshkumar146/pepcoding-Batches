import java.util.PriorityQueue;
import java.util.HashMap;
public class l001{
    static HashMap<Character,String> BinaryCodes= new HashMap<>();
    static HashMap<String,Character> CharacterDecoder = new HashMap<>();

    public static void main(String[] args){
        HuffmanncoderDcoder();   
        String str = "abcadajv";
        String encodeStr = encode(str);
        String decoderStr = decoder(encodeStr);
        System.out.println(decoderStr);
    }

    public static String encode(String str){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<str.length();i++){
            sb.append(BinaryCodes.get(str.charAt(i)));
        }

        return sb.toString();
    }

    public static String decoder(String str){
        StringBuilder ans = new StringBuilder();
        StringBuilder prefix = new StringBuilder();
        for(int i=0;i<str.length();i++){
            prefix.append(str.charAt(i));
            if(CharacterDecoder.containsKey(prefix.toString())){
                ans.append(CharacterDecoder.get(prefix.toString()));
                prefix = new StringBuilder();
            }
        }

        return ans.toString();
    }

    //=====================================================================================

    public static void HuffmanncoderDcoder(){
        encoder("aaaaaaabbbbbbbbbbbbbbbbbbbbbbbbddddddddddddddhjjjjjjjjjjjjjjdsbcjsldjdsjbcksjdbcjscjsvcjsvcsjvcsv");
    }

    public static class Node implements Comparable<Node> {
        char ch;
        int freq = 0;
        Node left = null;
        Node right = null;

        Node(int freq,Node left,Node right){
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        Node(char ch,int freq){
            this.ch = ch;
            this.freq = freq;
        }

        @Override
        public int compareTo(Node o){
            return this.freq - o.freq;
        }
    }

    public static void encoder(String str){
        int freq[] = new int[26];
        for(int i=0;i<str.length();i++) freq[str.charAt(i)-'a']++;
        PriorityQueue<Node> pq = new PriorityQueue();
        for(int i=0;i<26;i++){
            if(freq[i] > 0)
               pq.add(new Node((char)(i+'a'), freq[i]));
        }

        while(pq.size()!=1){
            Node n1 = pq.remove();
            Node n2 = pq.remove();

            pq.add(new Node(n1.freq+n2.freq, n1,n2));
        }

        Node root = pq.remove();
        traverse(root,"");
    }

    private static void traverse(Node node,String code){
        if(node.left == null && node.right == null){
            BinaryCodes.put(node.ch,code);
            CharacterDecoder.put(code,node.ch);
            return;
        }

        traverse(node.left,code + "0");
        traverse(node.right,code + "1");
    }
}
