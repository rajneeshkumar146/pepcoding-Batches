import java.util.ArrayList;

public class l001 {

    public static boolean isPalindrome(String str) {
        int si = 0, ei = str.length() - 1;
        while (si < ei) {
            if (str.charAt(si++) != str.charAt(ei--))
                return false;
        }

        return true;
    }

    public static void getAllSubstrings(String str) {
        for (int i = 0; i < str.length(); i++) {
            for (int len = 1; i + len <= str.length(); len++) {
                System.out.println(str.substring(i, i + len));
            }
        }
    }

    public static void getAllPalindromicSubstrings(String str) {
        for (int i = 0; i < str.length(); i++) {
            for (int len = 1; i + len <= str.length(); len++) {
                String s = str.substring(i, i + len);
                if (isPalindrome(s))
                    System.out.println(s);
            }
        }
    }

    public static String compression1(String str) {
        if (str.length() <= 1)
            return str;
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));

        for (int i = 1; i <= str.length(); i++) {
            int count = 1;
            while (i < str.length() && str.charAt(i - 1) == str.charAt(i)) {
                count++;
                i++;
            }

            // if(count != 1) sb.append(count);
            if (i < str.length())
                sb.append(str.charAt(i));
        }

        return sb.toString();
    }

    public static String compression2(String str) {
        if (str.length() <= 1)
            return str;
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));

        for (int i = 1; i <= str.length(); i++) {
            int count = 1;
            while (i < str.length() && str.charAt(i - 1) == str.charAt(i)) {
                count++;
                i++;
            }

            if (count != 1)
                sb.append(count);
            if (i < str.length())
                sb.append(str.charAt(i));
        }

        return sb.toString();
    }

    public static String toggle(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'a' && ch <= 'z')
                sb.append((char) (ch - 'a' + 'A'));
            else
                sb.append((char) (ch - 'A' + 'a'));
        }

        return sb.toString();
    }

    public static String diffrenceOfTwoNumber(String str) {
        if (str.length() <= 1)
            return str;
        StringBuilder sb = new StringBuilder();

        sb.append(str.charAt(0));
        for (int i = 1; i < str.length(); i++) {
            char ch0 = str.charAt(i - 1);
            char ch1 = str.charAt(i);
            sb.append(ch1 - ch0);
            sb.append(ch1);
        }

        return sb.toString();
    }

    public static ArrayList<String> subseq(String str) {
        ArrayList<String> ans = new ArrayList<>();
        ans.add("");

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int size = ans.size();
            for (int j = 0; j < size; j++) {
                ans.add(ans.get(j) + ch);
            }
        }

        return ans;
    }

    public static void solution(String str) {
        ArrayList<String> ans = new ArrayList<>();
        ans.add("");
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            ArrayList<String> myAns = new ArrayList<>();

            for (String s : ans) {
                for (int j = 0; j <= s.length(); j++) {
                    myAns.add(s.substring(0, j) + ch + s.substring(j));
                }
            }

            ans = myAns;
        }

        System.out.println(ans);
    }

    public static boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0)
                return false;
        }

        return true;
    }

    public static void solution(ArrayList<Integer> al) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int ele : al) {
            if (!isPrime(ele))
                list.add(ele);
        }

        al.clear();
        for (int ele : list)
            al.add(ele);
    }

    public static ArrayList<Integer> allPrimes(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 2; i * i <= n; i++) {
            if (isPrime(i))
                list.add(i);
        }
        return list;
    }

    public static void powerForm(int num, ArrayList<Integer> list) {
        System.out.print(num + " -> ");
        int idx = 0;
        while (idx < list.size() && num > 1) {
            int count = 0;
            while (num % list.get(idx) == 0 && num > 1) {
                num /= list.get(idx);
                count++;
            }

            if (count > 0)
                System.out.print(list.get(idx) + "^" + count + " ");
            idx++;
        }

        if (num > 1)
            System.out.print(num + "^" + 1 + " ");
        System.out.println();
    }

    public static void exponForm(int[] query) {
        ArrayList<Integer> list = allPrimes(1000000);
        // System.out.println(list);
        for (int ele : query) {
            powerForm(ele, list);
        }
    }

    public static void main(String[] args) {
        int[] arr = { 11, 97, 197, 339, 55, 498, 44, 39, 454545, 23, 2312, 676, 256, 555, 222222 };
        exponForm(arr);

    }
}