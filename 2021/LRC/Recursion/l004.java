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

    public static int mazePath(int sr, int sc, int er, int ec, String asf) {
        if (sr == er && sc == ec) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        if (sc + 1 <= ec)
            count += mazePath(sr, sc + 1, er, ec, asf + 'h');
        if (sr + 1 <= er)
            count += mazePath(sr + 1, sc, er, ec, asf + 'v');

        return count;
    }

    public static int mazePathJump(int sr, int sc, int er, int ec, String asf) {
        if (sr == er && sc == ec) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for (int jump = 1; sc + jump <= ec; jump++)
            count += mazePathJump(sr, sc + jump, er, ec, asf + 'h' + jump + " ");
        for (int jump = 1; sr + jump <= er; jump++)
            count += mazePathJump(sr + jump, sc, er, ec, asf + 'v' + jump + " ");

        return count;
    }

    public static int mazePath_02(int sr, int sc, int er, int ec, int[][] dir, String[] dirS, String asf) {
        if (sr == er && sc == ec) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                count += mazePath_02(r, c, er, ec, dir, dirS, asf + dirS[d]);
            }
        }

        return count;
    }

    public static int mazePathJump_02(i  nt sr, int sc, int er, int ec, int[][] dir, String[] dirS, String asf) {
        if (sr == er && sc == ec) {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad <= Math.max(er, ec); rad++) {
                int r = sr + rad * dir[d][0];
                int c = sc + rad * dir[d][1];

                if (r >= 0 && c >= 0 && r <= er && c <= ec) {
                    count += mazePathJump_02(r, c, er, ec, dir, dirS, asf + dirS[d] + rad + " ");
                } else
                    break;
            }

        }

        return count;
    }

    public static void main(String[] args) {
        // System.out.println(printSubseq("abc", 0, ""));
        // System.out.println(printKPC("572", 0, ""));
        // System.out.println(mazePath(0, 0, 2, 2, ""));

        int[][] dir = { { 0, 1 }, { 1, 0 },{1,1} };
        String[] dirS = { "h", "v","d" };

        System.out.println(mazePathJump_02(0, 0, 2, 2, dir, dirS, ""));
        // System.out.println(mazePathJump(0, 0, 2, 2, ""));
    }
}