public class l006_CrossWord {

    static char[][] box = { { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
            { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' }, { '+', '-', '-', '-', '-', '-', '-', '-', '+', '+' },
            { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' }, { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
            { '+', '-', '-', '-', '-', '-', '-', '+', '+', '+' }, { '+', '-', '+', '+', '+', '-', '+', '+', '+', '+' },
            { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' }, { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
            { '+', '+', '+', '+', '+', '+', '+', '+', '+', '+' } };

    static String[] words = { "agra", "norway", "england", "gwalior" };

    public static void print(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean isPossiblePlaceH(int r, int c, String word) {
        int l = word.length();
        // if (c == 0 && c + l < 10 && box[r][c + l] != '+')
        // return false;
        // else if (c != 0 && c + l == 10 && box[r][c - 1] != '+')
        // return false;
        // else if (c != 0 && c + l != 10 && (box[r][c - 1] != '+' || box[r][c + l] !=
        // '+'))
        // return false;

        // for (int i = 0; i < 10 && c + i < 10; i++) {
        // if (box[r][c + i] != '-' && box[r][c + i] != word.charAt(i))
        // return false;
        // }

        int len = 0, C = c;
        while (C < 10 && box[r][C++] != '+') {
            len++;
        }

        if (len != l || (c > 0 && box[r][c - 1] != '+'))
            return false;

        for (int i = 0; i < word.length(); i++) {
            if (box[r][c + i] != '-' && box[r][c + i] != word.charAt(i))
                return false;
        }

        return true;
    }

    public static int placeH(int r, int c, String word) {
        int loc = 0;
        for (int i = 0; i < word.length(); i++) {
            if (box[r][c + i] == '-') {
                box[r][c + i] = word.charAt(i);
                loc ^= (1 << i);
            }
        }
        return loc;
    }

    public static void unPlaceH(int r, int c, int loc, String word) {
        for (int i = 0; i < word.length(); i++) {
            if ((loc & (1 << i)) != 0) {
                box[r][c + i] = '-';
            }
        }
    }

    public static boolean isPossiblePlaceV(int r, int c, String word) {
        int l = word.length();
        int len = 0, R = r;
        while (R < 10 && box[R++][c] != '+') {
            len++;
        }

        if (len != l || (r > 0 && box[r - 1][c] != '+'))
            return false;

        for (int i = 0; i < word.length(); i++) {
            if (box[r + i][c] != '-' && box[r + i][c] != word.charAt(i))
                return false;
        }

        return true;
    }

    public static int placeV(int r, int c, String word) {
        int loc = 0;
        for (int i = 0; i < word.length(); i++) {
            if (box[r + i][c] == '-') {
                box[r + i][c] = word.charAt(i);
                loc ^= (1 << i);
            }
        }
        return loc;
    }

    public static void unPlaceV(int r, int c, int loc, String word) {
        for (int i = 0; i < word.length(); i++) {
            if ((loc & (1 << i)) != 0) {
                box[r + i][c] = '-';
            }
        }
    }

    public static void crossWord(int idx) {
        if (idx == words.length) {
            print(box);
            return;
        }

        String word = words[idx];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (box[i][j] == '-' || box[i][j] == word.charAt(0)) {

                    if (isPossiblePlaceH(i, j, word)) {
                        int loc = placeH(i, j, word);
                        crossWord(idx + 1);
                        unPlaceH(i, j, loc, word);
                    }

                    if (isPossiblePlaceV(i, j, word)) {
                        int loc = placeV(i, j, word);
                        crossWord(idx + 1);
                        unPlaceV(i, j, loc, word);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        crossWord(0);
    }

}