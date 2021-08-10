import java.lang.reflect.Array;
import java.util.*;

public class l005_Questions {

    public class pair {
        int r = 0;
        int c = 0;

        pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public boolean isValidToPlaceNumber(char[][] board, int r, int c, int num) {
        // row
        for (int i = 0; i < 9; i++)
            if (board[r][i] - '0' == num)
                return false;

        // col
        for (int i = 0; i < 9; i++)
            if (board[i][c] - '0' == num)
                return false;

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

    public boolean solveSudoku(char[][] board, int idx, ArrayList<pair> arr) {
        if (idx == arr.size()) {
            return true;
        }

        pair p = arr.get(idx);
        int r = p.r;
        int c = p.c;

        for (int num = 1; num <= 9; num++) {
            if (isValidToPlaceNumber(board, r, c, num)) {
                board[r][c] = (char) (num + '0');
                if (solveSudoku(board, idx + 1, arr))
                    return true;
                board[r][c] = '.';
            }
        }

        return false;
    }

    public void solveSudoku(char[][] board) {
        ArrayList<pair> arr = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    arr.add(new pair(i, j)); // i * 9 + j
                }
            }
        }

        solveSudoku(board, 0, arr);
    }

    public static int[] rows, cols;
    public static int[][] mat;

    public boolean solveSudokuBits(char[][] board, int idx, ArrayList<pair> arr) {
        if (idx == arr.size()) {
            return true;
        }

        pair p = arr.get(idx);
        int r = p.r;
        int c = p.c;

        for (int num = 1; num <= 9; num++) {
            int mask = (1 << num);
            if ((rows[r] & mask) == 0 && (cols[c] & mask) == 0 && (mat[r / 3][c / 3] & mask) == 0) {
                board[r][c] = (char) (num + '0');
                rows[r] ^= mask;
                cols[c] ^= mask;
                mat[r / 3][c / 3] ^= mask;

                if (solveSudoku(board, idx + 1, arr))
                    return true;

                board[r][c] = '.';
                rows[r] ^= mask;
                cols[c] ^= mask;
                mat[r / 3][c / 3] ^= mask;
            }
        }

        return false;
    }

    public void solveSudokuBits(char[][] board) {
        ArrayList<pair> arr = new ArrayList<>();
        rows = new int[9];
        cols = new int[9];
        mat = new int[3][3];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    arr.add(new pair(i, j)); // i * 9 + j
                } else {
                    int mask = 1 << (board[i][j] - '0');
                    rows[i] ^= mask;
                    cols[j] ^= mask;
                    mat[i / 3][j / 3] ^= mask;
                }
            }
        }

        solveSudokuBits(board, 0, arr);
    }

    // 36
    public boolean isValidSudoku(char[][] board) {
        rows = new int[9];
        cols = new int[9];
        mat = new int[3][3];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int mask = 1 << (board[i][j] - '0');
                    if ((rows[i] & mask) == 0 && (cols[j] & mask) == 0 && (mat[i / 3][j / 3] & mask) == 0) {
                        rows[i] ^= mask;
                        cols[j] ^= mask;
                        mat[i / 3][j / 3] ^= mask;
                    } else {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    // Crypto
    public static int stringToInt(String str, HashMap<Character, Integer> charIntMap) {
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            res = res * 10 + charIntMap.get(str.charAt(i));
        }
        return res;
    }

    public static void crypto(String unique, int idx, HashMap<Character, Integer> charIntMap, boolean[] usedNumbers,
            String s1, String s2, String s3) {
        if (idx == unique.length()) {
            int x = stringToInt(s1, charIntMap);
            int y = stringToInt(s2, charIntMap);
            int z = stringToInt(s3, charIntMap);

            if (x + y == z) {
                for (int i = 0; i < 26; i++) {
                    char ch = (char) (i + 'a');
                    if (charIntMap.containsKey(ch))
                        System.out.print(ch + "-" + charIntMap.get(ch) + " ");
                }
                System.out.println();
            }
            return;
        }

        char ch = unique.charAt(idx);
        for (int num = 0; num < 10; num++) {
            if (!usedNumbers[num]) {
                usedNumbers[num] = true;
                charIntMap.put(ch, num);

                crypto(unique, idx + 1, charIntMap, usedNumbers, s1, s2, s3);

                charIntMap.remove(ch);
                usedNumbers[num] = false;
            }

        }
    }

    // 2 set of equal sum
    public static int equalSet(int[] arr, int idx, int sum1, String set1, int sum2, String set2) {
        if (idx == arr.length) {
            if (sum1 == sum2) {
                System.out.println(set1 + " = " + set2);
                return 1;
            }
            return 0;
        }

        int count = 0;
        count += equalSet(arr, idx + 1, sum1 + arr[idx], set1 + arr[idx] + " ", sum2, set2);
        count += equalSet(arr, idx + 1, sum1, set1, sum2 + arr[idx], set2 + arr[idx] + " ");

        return count;
    }

    public static void ksubsets(int[] arr, int idx, int[] subsetSum, ArrayList<ArrayList<Integer>> ans) {
        if (idx == arr.length) {
            int s = subsetSum[0];
            for (int ele : subsetSum) {
                if (s != ele) {
                    return;
                }
            }

            for (ArrayList<Integer> a : ans) {
                System.out.print(a + " ");
            }
            System.out.println();

            return;
        }

        for (int k = 0; k < subsetSum.length; k++) {
            ArrayList<Integer> set = ans.get(k);
            set.add(arr[idx]);
            subsetSum[k] += arr[idx];

            ksubsets(arr, idx + 1, subsetSum, ans);

            subsetSum[k] -= arr[idx];
            set.remove(set.size() - 1);
            if (set.size() == 0)
                break;
        }
    }

    public static void equalSet(int[] arr, int k) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < k; i++)
            ans.add(new ArrayList<>());

        int sum = 0;
        for (int ele : arr)
            sum += ele;

        if (sum % k != 0)
            return;

        int[] sumArray = new int[k];
        ksubsets(arr, 0, sumArray, ans);
        System.out.println(ans);
    }

    static int count = 1;

    public static void kPartition(int num, int TotalNum, ArrayList<ArrayList<Integer>> ans) {
        if (num > TotalNum) {
            if (ans.get(ans.size() - 1).size() == 0)
                return;

            System.out.print(count++ + ". ");
            for (ArrayList<Integer> a : ans)
                System.out.print(a + " ");
            System.out.println();

            return;
        }

        for (ArrayList<Integer> a : ans) {
            a.add(num);

            kPartition(num + 1, TotalNum, ans);

            a.remove(a.size() - 1);
            if (a.size() == 0)
                break;
        }
    }
    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80 };
        equalSet(arr, 0, 0, " ", 0, "");
        // equalSet(arr, 1 , 10, "10 ", 0, "");
    }

}