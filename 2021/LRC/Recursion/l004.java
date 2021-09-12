public class l004 {
    public static int printSubseq(String str, int idx, String asf) {
        if (idx == str.length()) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        count += printSubseq(str, idx + 1, asf + str.charAt(idx));
        count += printSubseq(str, idx + 1, asf);

        return count;
    }

    public static String[] words = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static int printKPC(String str, int idx, String asf) {
        if (idx == str.length()) {
            System.out.println(asf);
            return 1;
        }

        int index = str.charAt(idx) - '0';
        String word = words[index];

        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            count += printKPC(str, idx + 1, asf + ch);
        }

        return count;
    }

    public static int stairPath(int n, String asf) {
        if (n == 0) {
            System.out.println(asf);
            return 1;
        }
        int count = 0;
        for (int jump = 1; jump <= 3 && n - jump >= 0; jump++) {
            count += stairPath(n - jump, asf + jump);
        }
        return count;
    }

    public static void main(String[] args) {
        // System.out.println(printSubseq("abc", 0, ""));
        // System.out.println(printKPC("572", 0, ""));
        System.out.println(stairPath(5,""));
    }
}