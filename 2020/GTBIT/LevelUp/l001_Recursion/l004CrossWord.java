import java.util.Arrays;

public class l004CrossWord {

    static char[][] box = { { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
            { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' }, { '+', '-', '-', '-', '-', '-', '-', '-', '+', '+' },
            { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' }, { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
            { '+', '-', '-', '-', '-', '-', '-', '+', '+', '+' }, { '+', '-', '+', '+', '+', '-', '+', '+', '+', '+' },
            { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' }, { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
            { '+', '+', '+', '+', '+', '+', '+', '+', '+', '+' } };

    static String[] words = { "agra", "norway", "england", "gwalior" };

    public static boolean isSafeToPlaceH(String word, int r, int c) {
        
        for (int i = 0; i < word.length(); i++) {
            if (c + i >= box[0].length)
                return false;
            if (box[r][c + i] != '-' && box[r][c + i] != word.charAt(i))
                return false;
        }
        return true;
    }

    public static boolean[] placeH(String word, int r, int c) {
        boolean[] loc = new boolean[word.length()];
        for (int i = 0; i < word.length(); i++) {
            if (box[r][c + i] == '-') {
                box[r][c + i] = word.charAt(i);
                loc[i] = true;
            }
        }
        return loc;
    }

    public static void unPlaceH(String word, int r, int c, boolean[] loc) {
        for (int i = 0; i < word.length(); i++) {
            if (loc[i]) {
                box[r][c + i] = '-';
            }
        }
    }

    public static boolean isSafeToPlaceV(String word, int r, int c) {
        for (int i = 0; i < word.length(); i++) {
            if (r + i == box.length)
                return false;
            if (box[r + i][c] != '-' && box[r + i][c] != word.charAt(i))
                return false;
        }
        return true;
    }

    public static boolean[] placeV(String word, int r, int c) {
        boolean[] loc = new boolean[word.length()];
        for (int i = 0; i < word.length(); i++) {
            if (box[r + i][c] == '-') {
                box[r + i][c] = word.charAt(i);
                loc[i] = true;
            }
        }
        return loc;
    }

    public static void unPlaceV(String word, int r, int c, boolean[] loc) {
        for (int i = 0; i < word.length(); i++) {
            if (loc[i]) {
                box[r + i][c] = '-';
            }
        }
    }

    public static int crossWord(int idx) {
        if (idx == words.length) {
            print2D();
            return 1;
        }

        String word = words[idx];
        int n = box.length, m = box[0].length, count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j] == '-' || box[i][j] == word.charAt(0)) {
                    if (isSafeToPlaceH(word, i, j)) {
                        boolean[] loc = placeH(word, i, j);
                        count += crossWord(idx + 1);
                        unPlaceH(word, i, j, loc);
                    }

                    if (isSafeToPlaceV(word, i, j)) {
                        boolean[] loc = placeV(word, i, j);
                        count += crossWord(idx + 1);
                        unPlaceV(word, i, j, loc);
                    }

                }

            }
        }

        return count;
    }

    public static void print2D() {
        int n = box.length, m = box[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(box[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void crossWord() {
        Arrays.sort(words, (a, b) -> {
            return b.length() - a.length();
        });

        System.out.println(crossWord(0));
    }

    public static void main(String[] args) {
        crossWord();
    }

}