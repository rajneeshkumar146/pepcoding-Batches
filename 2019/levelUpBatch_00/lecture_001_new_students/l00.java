import java.util.ArrayList;

public class l00 {
    public static void main(String[] args) {
        solve();

    }

    public static void solve() {
        // fibo_01(0, 1, 10);
        // subsequence("abc", "");
        // System.out.println(subseq("abc"));

        // System.out.println(nokiaKeyPad("459"));
        System.out.println(nokiaKeyPad_01("459",""));
    }

    public static int[] allIndex(int[] arr, int idx, int data, int count) {
        if (idx == arr.length) {
            int[] base = new int[count];
            return base;
        }

        if (arr[idx] == data)
            count++;

        int[] recAns = allIndex(arr, idx + 1, data, count);

        if (arr[idx] == data)
            recAns[count - 1] = idx;

        return recAns;

    }

    public static int powerBtr(int a, int b) {
        if (a == 0)
            return 0;
        if (b == 0)
            return 1;

        int smallAns = powerBtr(a, b / 2);
        smallAns *= smallAns;

        return b % 2 == 0 ? smallAns : smallAns * a;
    }

    public static int fibo(int n) {
        if (n <= 1)
            return n;

        return fibo(n - 1) + fibo(n - 2);
    }

    public static void fibo_01(int a, int b, int n) {
        if (n == 0) {
            return;
        }

        System.out.print(a + " ");
        fibo_01(b, a + b, n - 1);
    }

    public static int euler(int n, int level) {
        if (n <= 1) {
            System.out.println("Base: " + n + "@" + level);
            return n;
        }

        System.out.println("Pre: " + n + "@" + level);

        int a = euler(n - 1, level + 1);
        System.out.println("In: " + n + "@" + level);
        int b = euler(n - 2, level + 1);

        System.out.println("Post: " + n + "@" + level);

        return a + b + 3;
    }

    public static int subsequence(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        String nstr = str.substring(1); // str.substr(1);
        char ch = str.charAt(0); // str[0];

        return subsequence(nstr, ans + ch) + subsequence(nstr, ans);
    }

    public static ArrayList<String> subseq(String str) {
        if (str.length() == 0) {
            ArrayList<String> base = new ArrayList<>(); // vector<string>* base=new vector<>();
            base.add(""); // base->push_back("");
            return base;
        }

        String nstr = str.substring(1); // str.substr(1);
        char ch = str.charAt(0); // str[0];

        ArrayList<String> recAns = subseq(nstr);
        int size = recAns.size();
        // ArrayList<String> myAns = new ArrayList<>(recAns);
        for (int i = 0; i < size; i++) {
            recAns.add(ch + recAns.get(i));
        }

        return recAns;
    }

    static String[] words = { "$^%", ";:.,", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz", "+-/", "@#*" };

    public static ArrayList<String> nokiaKeyPad(String str) {
        if (str.length() == 0) {
            ArrayList<String> base = new ArrayList<>(); // vector<string>* base=new vector<>();
            base.add(""); // base->push_back("");
            return base;
        }

        String nstr = str.substring(1); // str.substr(1);
        int idx = str.charAt(0) - '0'; // str[0];
        String word = words[idx];

        ArrayList<String> recAns = nokiaKeyPad(nstr);

        ArrayList<String> myAns = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            for (String s : recAns) {
                myAns.add(word.charAt(i) + s);
            }
        }

        return myAns;
    }

    public static int nokiaKeyPad_01(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        String nstr = str.substring(1); // str.substr(1);
        int idx = str.charAt(0) - '0'; // str[0];
        String word = words[idx];

        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            count += nokiaKeyPad_01(nstr, ans + word.charAt(i));
        }

        return count;

    }

}