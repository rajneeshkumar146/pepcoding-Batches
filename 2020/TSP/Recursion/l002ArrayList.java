import java.util.ArrayList;

public class l002ArrayList {
    public static void test1() {
        ArrayList<Integer> al = new ArrayList<>();
        al.add(10);
        al.add(20);
        al.add(30);
        al.add(40);
        al.add(50);

        System.out.println(al);
        // for (int i = 0; i < al.size(); i++)
        // System.out.println(al.get(i));

        for (Integer ele : al)
            System.out.println(ele);

        al.remove(3); // O(n)
        al.set(3, 100); // O(1)
        al.add(3, 200); // O(n)
    }

    public static boolean isPrime(int val) {
        for (int i = 2; i * i <= val; i++) {
            if (val % i == 0)
                return false;
        }

        return true;
    }

    // No extra Space allowed
    public static void removePrimes(ArrayList<Integer> arr) {
        int i = arr.size() - 1;
        while (i >= 0) {
            if (isPrime(arr.get(i)))
                arr.remove(i);
            i--;
        }
    }

    // best possible complexity, No extra Space allowed, answer can be in any order.
    public static void removeData(ArrayList<Integer> arr, int data) {
        int i = arr.size() - 1;
        while (i >= 0) {
            if (arr.get(i) == data) {
                int n = arr.size();
                int lastEle = arr.get(n - 1);
                arr.set(i, lastEle);
                arr.remove(n - 1);
            }
            i--;
        }
    }

    public static void StringTest() {
        String str = "abcdef";
        str += 'd';
        str += "ghi";
        String str1 = "abcdefd";
        System.out.println(str.charAt(3));
        System.out.println(str.length());

        System.out.println(str.substring(3, 7 + 1));
        System.out.println(str.substring(3));
        System.out.println(str.substring(3, str.length()));
    }

    public static void StringBuilderTest() {
        StringBuilder sb = new StringBuilder();
        sb.append('a');
        sb.append("abcde");
        sb.append("fg");

        System.out.println(sb);
        String str = sb.toString();
        System.out.println(str);

        System.out.println(sb.charAt(4));

        System.out.println(sb.substring(3, 5 + 1));
        System.out.println(sb.substring(3));
        System.out.println(sb.substring(3, sb.length()));

        sb.deleteCharAt(3);
        sb.setCharAt(3, 'z');
    }

    public static void test2() {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i <= (int) 1e6; i++) {
            ans.append(i);
        }
    }

    public static void main(String... args) {
        // StringTest();
        // StringBuilderTest();

        double s = System.currentTimeMillis();
        test2();
        double e = System.currentTimeMillis();

        System.out.println(e - s);

    }
}