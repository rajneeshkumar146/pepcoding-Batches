import java.util.ArrayList;

public class l001Basic {

    public static void printDecreasing(int n) {
        if (n == 0)
            return;

        System.out.println("Hi" + n);
        printDecreasing(n - 1);
        System.out.println("Bye" + n);
    }

    public static void fun(int n) {
        if (n == 0) {
            System.out.println("i have to stop" + n);
            return;
        }

        for (int i = 0; i < 3; i++)
            System.out.print(n + "@" + i);
        System.out.println();

        if (n % 2 == 0)
            System.out.println("heading toward child function" + n);
        fun(n - 1);
        if (n % 2 != 0)
            System.out.println("Back to parent function" + n);
    }

    public static void printIncreasingDecreasing(int n) {
        if (n == 0)
            return;

        System.out.println(n);
        printIncreasingDecreasing(n - 1);
        System.out.println(n);
    }

    public static int factorial(int n) {
        if (n == 0)
            return 1;
        int ans = factorial(n - 1);
        return ans * n;
    }

    public static int factorial2(int n) {
        return n == 0 ? 1 : factorial2(n - 1) * n;
    }

    public static int power(int x, int n) {
        if (n == 0)
            return 1;
        int smallAns = power(x, n / 2);
        smallAns *= smallAns;

        return (n % 2 == 0 ? smallAns : smallAns * x);
    }

    public static int[] allIndices(int[] arr, int data, int idx, int count) {
        if (idx == arr.length) {
            int[] base = new int[count];
            return base;
        }

        if (arr[idx] == data)
            count++;

        int[] ans = allIndices(arr, data, idx + 1, count);

        if (arr[idx] == data)
            ans[count - 1] = idx;

        return ans;
    }

    public static ArrayList<String> gss(String str) {
        if (str.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        char ch = str.charAt(0);
        String ros = str.substring(1);

        ArrayList<String> smallAns = gss(ros);
        ArrayList<String> myAns = new ArrayList<>(smallAns);

        for (String s : smallAns)
            myAns.add(ch + s);

        return myAns;
    }

    public static ArrayList<String> gss2(String str, int idx) {
        if (idx == str.length()) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        char ch = str.charAt(idx);

        ArrayList<String> smallAns = gss2(str, idx + 1);
        ArrayList<String> myAns = new ArrayList<>(smallAns);

        for (String s : smallAns)
            myAns.add(ch + s);

        return myAns;
    }

    static String[] codes = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };
    
    public static ArrayList < String > getKPC(String str, int idx) {
        if (idx == str.length()) {
            ArrayList < String > base = new ArrayList < > ();
            base.add("");
            return base;
        }

        char ch = str.charAt(idx);
        int codeIdx = ch - '0';
        String code = codes[codeIdx];
        ArrayList<String> smallAns = getKPC(str,idx + 1);
        ArrayList<String> ans = new ArrayList<>();

        for(int i = 0;i < code.length();i++){
            for(String s : smallAns){
                ans.add(code.charAt(i) + s);
            }
        }

        return ans;
    }
    public static void main(String[] args) {
        // printDecreasing(5);
        fun(5);
    }
}