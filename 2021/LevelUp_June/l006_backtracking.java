import java.util.ArrayList;
import java.util.HashSet;

public class l006_backtracking {

    public static boolean isSafeToPlaceNumber(char[][] board, int r, int c, int num) {
        // row
        for (int i = 0; i < 9; i++) {
            if (board[r][i] - '0' == num)
                return false;
        }

        // col
        for (int i = 0; i < 9; i++) {
            if (board[i][c] - '0' == num)
                return false;
        }

        // mat
        r = (r / 3) * 3;
        c = (c / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[r + i][c + j] - '0' == num)
                    return false;
            }
        }

        return true;
    }

    public boolean solveSudoku(char[][] board, ArrayList<Integer> emptyIndex, int idx) {
        if (idx == emptyIndex.size()) {
            return true;
        }

        int cell = emptyIndex.get(idx);
        int r = cell / 9;
        int c = cell % 9;

        for (int num = 1; num <= 9; num++) {
            if (isSafeToPlaceNumber(board, r, c, num)) {
                board[r][c] = (char) (num + '0');
                if (solveSudoku(board, emptyIndex, idx + 1))
                    return true;
                board[r][c] = '.';
            }
        }

        return false;
    }

    public void solveSudoku(char[][] board) {
        ArrayList<Integer> emptyIndex = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.')
                    emptyIndex.add(i * 9 + j);
            }
        }

        solveSudoku(board, emptyIndex, 0);
    }

    int[] row = new int[9];
    int[] col = new int[9];
    int[][] mat = new int[3][3];

    public boolean solveSudoku(char[][] board, ArrayList<Integer> emptyIndex, int idx) {
        if (idx == emptyIndex.size()) {
            return true;
        }

        int oneDEncodedValue = emptyIndex.get(idx); // emptyIndex[idx];
        int r = oneDEncodedValue / 9;
        int c = oneDEncodedValue % 9;

        for (int num = 1; num <= 9; num++) {
            int mask = 1 << num;
            if ((row[r] & mask) == 0 && (col[c] & mask) == 0 && (mat[r / 3][c / 3] & mask) == 0) {
                row[r] ^= mask;
                col[c] ^= mask;
                mat[r / 3][c / 3] ^= mask;

                board[r][c] = (char) (num + '0');
                if (solveSudoku(board, emptyIndex, idx + 1))
                    return true;
                board[r][c] = '.';

                row[r] ^= mask;
                col[c] ^= mask;
                mat[r / 3][c / 3] ^= mask;

            }
        }

        return false;
    }

    public void solveSudoku(char[][] board) {
        ArrayList<Integer> emptyIndex = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.')
                    emptyIndex.add(i * 9 + j); // emptyIndex.push_back(i * 9 + j);
                else {
                    int mask = 1 << (board[i][j] - '0');
                    row[i] ^= mask;
                    col[j] ^= mask;
                    mat[i / 3][j / 3] ^= mask;
                }
            }
        }

        solveSudoku(board, emptyIndex, 0);
    }

    // 36
    public boolean isValidSudoku(char[][] board) {
        int[] row = new int[9];
        int[] col = new int[9];
        int[][] mat = new int[3][3];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int mask = 1 << (board[i][j] - '0');
                    if ((row[i] & mask) == 0 && (col[j] & mask) == 0 && (mat[i / 3][j / 3] & mask) == 0) {
                        row[i] ^= mask;
                        col[j] ^= mask;
                        mat[i / 3][j / 3] ^= mask;
                    } else
                        return false;

                }
            }
        }

        return true;
    }

    public static int wordBreak(String str, String ans, HashSet<String> dict, int longestWordLen) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            String nstr = str.substring(0, i + 1);
            if (nstr.length() > longestWordLen)
                break;
            if (dict.contains(nstr)) {
                count += wordBreak(str.substring(i + 1), ans + nstr + " ", dict, longestWordLen);
            }
        }

        return count;
    }

    public static int friendsPairing(int n, String ans, boolean[] used) {
        int idx = 0; // first Unsed Friend
        while (idx <= n) {
            if (!used[idx])
                break;
            idx++;
        }

        if (idx > n) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        used[idx] = true;
        count += friendsPairing(n, ans + "(" + idx + ") ", used); // single

        for (int i = idx + 1; i <= n; i++) {
            if (!used[i]) {
                String friend = "(" + idx + "" + i + ") ";
                used[i] = true;
                count += friendsPairing(n, ans + friend, used); // pairing
                used[i] = false;
            }
        }

        used[idx] = false;

        return count;
    }

    static char[][] box = { { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
            { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' }, { '+', '-', '-', '-', '-', '-', '-', '-', '+', '+' },
            { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' }, { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
            { '+', '-', '-', '-', '-', '-', '-', '+', '+', '+' }, { '+', '-', '+', '+', '+', '-', '+', '+', '+', '+' },
            { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' }, { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
            { '+', '+', '+', '+', '+', '+', '+', '+', '+', '+' } };

    public static boolean isPossibleToPlace_H(char[][] board, String word, int r, int c) {
        int l = word.length(), m = board[0].length;
        if (c + l > m)
            return false;
        if (c == 0 && c + l < m && board[r][c + l] != '+')
            return false;
        if (c != 0 && c + l == m && board[r][c - 1] != '+')
            return false;
        if (c != 0 && c + l < m && (board[r][c + l] != '+' || board[r][c - 1] != '+'))
            return false;

        for (int i = 0; i < word.length(); i++) {
            if (board[r][c + i] != '-' && word.charAt(i) != board[r][c + i])
                return false;
        }

        return true;
    }

    public static int place_H(char[][] board, String word, int r, int c) {
        int loc = 0;
        for (int i = 0; i < word.length(); i++) {
            if (board[r][c + i] == '-') {
                loc ^= (1 << i);
                board[r][c + i] = word.charAt(i);
            }
        }

        return loc;
    }

    public static void unPlace_H(char[][] board, String word, int r, int c, int loc) {
        for (int i = 0; i < word.length(); i++) {
            int mask = (1 << i);
            if ((loc & mask) != 0) {
                board[r][c + i] = '-';
            }
        }
    }

    public static boolean isPossibleToPlace_V(char[][] board, String word, int r, int c) {
        int l = word.length(), n = board.length;
        if (r + l > n)
            return false;
        if (r == 0 && r + l < n && board[r + l][c] != '+')
            return false;
        if (r != 0 && r + l == n && board[r - 1][c] != '+')
            return false;
        if (r != 0 && r + l < n && (board[r + l][c] != '+' || board[r - 1][c] != '+'))
            return false;

        for (int i = 0; i < word.length(); i++) {
            if (board[r + i][c] != '-' && word.charAt(i) != board[r + i][c])
                return false;
        }

        return true;
    }

    public static int place_V(char[][] board, String word, int r, int c) {
        int loc = 0;
        for (int i = 0; i < word.length(); i++) {
            if (board[r + i][c] == '-') {
                loc ^= (1 << i);
                board[r + i][c] = word.charAt(i);
            }
        }

        return loc;

    }

    public static void unPlace_V(char[][] board, String word, int r, int c, int loc) {
        for (int i = 0; i < word.length(); i++) {
            int mask = (1 << i);
            if ((loc & mask) != 0) {
                board[r + i][c] = '-';
            }
        }
    }

    public static int crossWord(char[][] board, String[] words, int idx) {
        if (idx == words.length) {
            return 1;
        }

        String word = words[idx];
        int count = 0;
        int n = board.length, m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '-' || board[i][j] == word.charAt(0)) {

                    if (isPossibleToPlace_H(board, word, i, j)) {
                        int loc = place_H(board, word, i, j);
                        count += crossWord(board, words, idx + 1);
                        unPlace_H(board, word, i, j, loc);
                    }

                    if (isPossibleToPlace_V(board, word, i, j)) {
                        int loc = place_V(board, word, i, j);
                        count += crossWord(board, words, idx + 1);
                        unPlace_V(board, word, i, j, loc);
                    }

                }
            }
        }

        return count;
    }

    public static int goldMine(int[][] arr, int r, int c, int[][] dir) {
        int n = arr.length, m = arr[0].length;
        if (c == m - 1)
            return arr[r][c];

        int myMaxAns = 0;
        for (int d = 0; d < dir.length; d++) {
            int x = r + dir[d][0];
            int y = c + dir[d][1];

            if (x >= 0 && y >= 0 && x < n && y < m) {
                int recMaxAns = goldMine(arr, x, y, dir);
                if (recMaxAns + arr[r][c] > myMaxAns)
                    myMaxAns = recMaxAns + arr[r][c];
            }
        }

        return myMaxAns;
    }

    public static int goldMine(int[][] arr) {
        int[][] dir = { { -1, 1 }, { 0, 1 }, { 1, 1 } };
        int maxAns = 0;
        int n = arr.length, m = arr[0].length;
        for (int i = 0; i < n; i++) {
            maxAns = Math.max(maxAns, goldMine(arr, i, 0, dir));
        }

        return maxAns;
    }

    public static int kSubSetSum(int[] arr, int idx, int[] setSum, ArrayList<ArrayList<Integer>> sets) {
        if (idx == arr.length) {
            for (int i = 1; i < setSum.length; i++)
                if (setSum[i - 1] != setSum[i])
                    return 0;

            for (ArrayList<Integer> s : sets)
                System.out.print(s + " ");

            System.out.println();

            return 1;
        }

        int k = setSum.length, count = 0;
        for (int i = 0; i < k; i++) {
            setSum[i] += arr[idx];
            sets.get(i).add(arr[idx]); // sets[i].push_back(arr[idx]);

            count += kSubSetSum(arr, idx + 1, setSum, sets);

            setSum[i] -= arr[idx];
            sets.get(i).remove(sets.get(i).size() - 1); // sets[i].pop_back();

            if (sets.get(i).size() == 0) {
                break;
            }
        }

        return count;

    }

    public static void kSubSetSum() {
        int k = 3;
        int[] arr = { 1, 2, 3, 4, 5, 6 };
        ArrayList<ArrayList<Integer>> sets = new ArrayList<>();
        for (int i = 0; i < k; i++)
            sets.add(new ArrayList<>());
        int[] setSum = new int[k];

        System.out.println(kSubSetSum(arr, 0, setSum, sets));
    }

    public static int kPartitions(String[] Character, int idx, int TotalTeam, ArrayList<ArrayList<String>> ans) {
        if (idx == Character.length) {
            for (ArrayList<String> a : ans)
                if (a.size() == 0)
                    return 0;

            for (ArrayList<String> a : ans)
                System.out.print(a + " ");

            System.out.println();
            return 1;
        }

        int count = 0;
        for (int i = 0; i < TotalTeam; i++) {
            ans.get(i).add(Character[idx]);
            count += kPartitions(Character, idx + 1, TotalTeam, ans);
            ans.get(i).remove(ans.get(i).size() - 1);

            if (ans.get(i).size() == 0)
                break;
        }

        return count;
    }

    public static void kPartitions() {
        int k = 3;
        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        String[] Character = { "Thor", "IronMan", "Hulk", "SpiderMan", "CaptainAmerica" };
        for (int i = 0; i < k; i++)
            ans.add(new ArrayList<>());

        System.out.println(kPartitions(Character, 0, k, ans));

    }

    static int[] map = new int[26];
    static boolean[] isUsed = new boolean[10];

    public static int StringToInteger(String str) {
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int mapedValue = map[ch - 'a'];
            res = res * 10 + mapedValue;
        }

        return res;
    }

    public static int crypto(String uniqueString, int idx, String str1, String str2, String str3) {
        if (idx == uniqueString.length()) {
            int n1 = StringToInteger(str1);
            int n2 = StringToInteger(str2);
            int n3 = StringToInteger(str3);

            // if (n1 + n2 == n3) {
            // System.out.println("" + n1 + "\n+" + n2 + "\n------\n" + n3);
            // System.out.println();
            // return 1;
            // }

            if (n1 + n2 == n3) {
                for (int i = 0; i < 26; i++) {
                    if (map[i] != -1) {
                        System.out.print((char) (i + 'a') + "-" + map[i] + " ");
                    }
                }
                return 1;
            }

            return 0;
        }

        int count = 0;
        char ch = uniqueString.charAt(idx);
        for (int num = 0; num <= 9; num++) {
            if ((str1.charAt(0) == ch || str2.charAt(0) == ch || str3.charAt(0) == ch) && num == 0)
                continue;

            if (!isUsed[num]) {
                isUsed[num] = true;
                map[ch - 'a'] = num;

                count += crypto(uniqueString, idx + 1, str1, str2, str3);

                isUsed[num] = false;
                map[ch - 'a'] = -1;
            }
        }

        return count;

    }

    public static void crypto(String str1, String str2, String str3) {
        String str = str1 + str2 + str3;
        boolean[] freq = new boolean[26];
        for (int i = 0; i < str.length(); i++) {
            freq[str.charAt(i) - 'a'] = true;
        }

        str = "";
        for (int i = 0; i < 26; i++) {
            if (freq[i]) {
                str += (char) (i + 'a');
            }
        }

        Arrays.fill(map, -1);

        System.out.println(crypto(str, 0, str1, str2, str3));

    }

}