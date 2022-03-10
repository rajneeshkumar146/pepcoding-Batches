import java.util.Arrays;

public class l001 {
    public int numDecodings_memo(String s, int idx, int[] dp) {
        if (idx == s.length()) {
            return dp[idx] = 1;
        }

        if (dp[idx] != -1) {
            return dp[idx];
        }

        char ch1 = s.charAt(idx);
        int count = 0;
        if (ch1 != '0') {
            count += numDecodings_memo(s, idx + 1, dp);

            if (idx < s.length() - 1) {
                int num = (ch1 - '0') * 10 + (s.charAt(idx + 1) - '0');
                if (num <= 26)
                    count += numDecodings_memo(s, idx + 2, dp);
            }
        }
        return dp[idx] = count;
    }

    public int numDecodings_tabu(String s, int IDX, int[] dp) {
        for (int idx = s.length(); idx >= 0; idx--) {
            if (idx == s.length()) {
                dp[idx] = 1;
                continue;
            }

            char ch1 = s.charAt(idx);
            int count = 0;
            if (ch1 != '0') {
                count += dp[idx + 1];

                if (idx < s.length() - 1) {
                    int num = (ch1 - '0') * 10 + (s.charAt(idx + 1) - '0');
                    if (num <= 26)
                        count += dp[idx + 2];
                }
            }
            dp[idx] = count;
        }

        return dp[IDX];
    }

    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        return numDecodings_memo(s, 0, dp);
    }

}