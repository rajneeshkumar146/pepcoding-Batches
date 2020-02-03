import java.util.ArrayList;

public class basic {
    public static void main(String[] args) {
        
        // System.out.println(printWithSpace("ABC", 0));
    }

    public static void yashInfiniteCall(int i){

        yashInfiniteCall(i+1);

    }

    static ArrayList<String> printWithSpace(String str, int idx) {
        if (str.length() - 1 == idx) {
            ArrayList<String> base = new ArrayList<>();
            base.add(str.charAt(idx)+"");
            return base;
        }

        char ch = str.charAt(idx);
        // string reststr = str.substr(1);
        ArrayList<String> recAns = printWithSpace(str, idx + 1);

        ArrayList<String> ans = new ArrayList<>();
        for (String s : recAns) {
            ans.add(ch + s);
            ans.add(ch + " " + s);
        }

        return ans;
    }

}
