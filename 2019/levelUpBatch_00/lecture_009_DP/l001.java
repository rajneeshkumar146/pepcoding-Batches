import java.util.LinkedList;
import java.util.Arrays;
public class l001 {

	public static void main(String[] args) {
		solve();
	}

	//Basic.=============================================

	public static int fib_01(int n, int[] dp) {
		if (n <= 1) {
			dp[n] = n;
			return n;
		}

		if (dp[n] != 0) return dp[n];

		int ans = fib_01(n - 1, dp) + fib_01(n - 2, dp);

		return dp[n] = ans;
	}

	public static int fibDP_01(int N, int[] dp) {
		for (int n = 0; n <= N; n++) {

			if (n <= 1) {
				dp[n] = n;
				continue;
			}

			int ans = dp[n - 1] + dp[n - 2];
			dp[n] = ans;
		}

		return dp[N];
	}

	public static int fibDPExtend_01(int N) {
		int a = 0;
		int b = 1;
		int sum = 0;

		for (int n = 0; n <= N; n++) {
			if (n <= 1) {
				continue;
			}

			sum = a + b;
			a = b;
			b = sum;
		}

		return sum;
	}

	public static int[][] multiplication(int[][] a, int[][] b) {
		int[][] ans = new int[2][2];

		int a0 = a[0][0] * b[0][0] + a[0][1] * b[1][0];
		int a1 = a[0][0] * b[0][1] + a[0][1] * b[1][1];
		int a2 = a[1][0] * b[0][0] + a[1][1] * b[1][0];
		int a3 = a[1][0] * b[0][1] + a[1][1] * b[1][1];

		ans[0][0] = a0;
		ans[0][1] = a1;
		ans[1][0] = a2;
		ans[1][1] = a3;

		return ans;
	}

	public static int[][] fiboLogn(int[][] a, int n) {
		if (n == 1) return a;

		int[][] recAns = fiboLogn(a, n / 2);
		recAns = multiplication(recAns, recAns);

		return n % 2 != 0 ? multiplication(recAns, a) : recAns;
	}

	public static void basic() {
		int n = 7;
		int[] dp = new int[n + 1];
		int ans = 0;

		// ans=fib_01(n,dp);
		// ans=fibDP_01(n,dp);

		int[][] a = {
			{
				1,
				1
			},
			{
				1,
				0
			}
		};
		int[][] mat = fiboLogn(a, n);
		ans = mat[0][1];

		display2D(mat);
		// display(dp);
		System.out.println(ans);

	}

	//PathSeries.=============================================================

	public static int mazePathHV_rec(int sr, int sc, int er, int ec, int[][] dp) {
		if (sr == er && sc == ec) {
			dp[sr][sc] = 1;
			return 1;
		}

		if (dp[sr][sc] != 0) return dp[sr][sc];

		int count = 0;
		if (sr + 1 <= er) count += mazePathHV_rec(sr + 1, sc, er, ec, dp);

		if (sr + 1 <= er && sc + 1 <= ec) count += mazePathHV_rec(sr + 1, sc + 1, er, ec, dp);

		if (sc + 1 <= ec) count += mazePathHV_rec(sr, sc + 1, er, ec, dp);

		return dp[sr][sc] = count;
	}

	public static int mazePathHV_DP(int sr, int sc, int er, int ec, int[][] dp) {

		for (sr = er; sr >= 0; sr--) {
			for (sc = ec; sc >= 0; sc--) {

				if (sr == er && sc == ec) {
					dp[sr][sc] = 1;
					continue;
				}

				int count = 0;
				if (sr + 1 <= er) count += dp[sr + 1][sc];

				if (sr + 1 <= er && sc + 1 <= ec) count += dp[sr + 1][sc + 1];

				if (sc + 1 <= ec) count += dp[sr][sc + 1];

				dp[sr][sc] = count;
			}
		}
		return dp[0][0];
	}

	public static int mazePathMulti_rec(int sr, int sc, int er, int ec, int[][] dp) {
		if (sr == er && sc == ec) {
			dp[sr][sc] = 1;
			return 1;
		}

		if (dp[sr][sc] != 0) return dp[sr][sc];

		int count = 0;
		for (int jump = 1; sr + jump <= er; jump++)
		count += mazePathMulti_rec(sr + jump, sc, er, ec, dp);

		for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
		count += mazePathMulti_rec(sr + jump, sc + jump, er, ec, dp);

		for (int jump = 1; sc + jump <= ec; jump++)
		count += mazePathMulti_rec(sr, sc + jump, er, ec, dp);

		return dp[sr][sc] = count;
	}

	public static int mazePathMulti_DP(int sr, int sc, int er, int ec, int[][] dp) {

		for (sr = er; sr >= 0; sr--) {
			for (sc = ec; sc >= 0; sc--) {

				if (sr == er && sc == ec) {
					dp[sr][sc] = 1;
					continue;
				}

				int count = 0;
				for (int jump = 1; sr + jump <= er; jump++)
				count += dp[sr + jump][sc];

				for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
				count += dp[sr + jump][sc + jump];

				for (int jump = 1; sc + jump <= ec; jump++)
				count += dp[sr][sc + jump];

				dp[sr][sc] = count;
			}
		}

		return dp[0][0];
	}

	public static int boradPath(int si, int ei, int[] dp) {
		if (si == ei) return dp[si] = 1;

		if (dp[si] != 0) return dp[si];

		int count = 0;
		for (int dice = 1; dice <= 6; dice++) {
			if (si + dice <= ei) {
				count += boradPath(si + dice, ei, dp);
			}
		}

		return dp[si] = count;
	}

	public static int boradPath_DP(int si, int ei, int[] dp) {

		for (int i = ei; i >= si; i--) {
			if (i == ei) {
				dp[i] = 1;
				continue;
			}

			int count = 0;
			for (int dice = 1; dice <= 6; dice++) {
				if (i + dice <= ei) {
					count += dp[i + dice];
				}
			}

			dp[i] = count;
		}

		return dp[0];
	}

	public static int boradPath_02_DP(int si, int ei, int[] steps, int[] dp) {

		for (int i = ei; i >= si; i--) {
			if (i == ei) {
				dp[i] = 1;
				continue;
			}

			int count = 0;
			for (int j = 0; j < steps.length; j++) {
				if (i + steps[j] <= ei) {
					count += dp[i + steps[j]];
				}
			}

			dp[i] = count;
		}

		return dp[0];
	}

	public static int boradPath_opti(int si, int ei, int[] dp) {
		LinkedList < Integer > ll = new LinkedList < >();

		for (int i = ei; i >= si; i--) {
			if (i > ei - 2) {
				ll.addFirst(1);
				continue;
			}

			ll.addFirst(2 * ll.getFirst());
			if (ll.size() == 8) {
				int lastValue = ll.removeLast();
				ll.addFirst(ll.removeFirst() - lastValue);
			}
		}

		return ll.getFirst();
	}

	//leetcode 70.
	public static int climbStairs(int n) {
		if (n <= 1) return 1;

		int count = climbStairs(n - 1) + climbStairs(n - 2);
		return count;
	}

	public static int climbStairs_DP(int n) {
		int[] dp = new int[n + 1];

		for (int i = 0; i <= n; i++) {
			if (i <= 1) {
				dp[i] = 1;
				continue;
			}

			dp[i] = dp[i - 1] + dp[i - 2];
		}

		display(dp);
		return dp[n];
	}

	public static int climbStairs_fast(int n) {
		int a = 1;
		int b = 1;
		int ans = 1;
		for (int i = 2; i <= n; i++) {
			ans = a + b;
			a = b;
			b = ans;
		}

		return ans;
	}

	//leetcode 746
	public static int minCostClimbingStairs_(int i, int[] cost, int[] dp) {
		if (i <= 1) return cost[i];

		if (dp[i] != 0) return dp[i];
		int c1 = minCostClimbingStairs_(i - 1, cost, dp);
		int c2 = minCostClimbingStairs_(i - 2, cost, dp);

		return dp[i] = Math.min(c1, c2) + ((i != cost.length) ? cost[i] : 0);
	}

	public static void minCostClimbingStairs_DP(int i, int[] cost, int[] dp) {

		for (i = 0; i <= cost.length; i++) {
			if (i <= 1) {
				dp[i] = cost[i];
				continue;
			}

			int c1 = dp[i - 1];
			int c2 = dp[i - 2];

			dp[i] = Math.min(c1, c2) + ((i != cost.length) ? cost[i] : 0);
		}
	}

	public static int minCostClimbingStairs_fast(int[] cost) {
		int a = cost[0];
		int b = cost[1];
		int sum = 0;
		for (int i = 2; i < cost.length; i++) {

			sum = cost[i] + Math.min(a, b);
			// sum=Math.min(a,b) + ((i!=cost.length)?cost[i]:0);
			a = b;
			b = sum;
		}

		return Math.min(a, b);
	}

	public static int minCostClimbingStairs_03_(int idx, int[] cost, int[] dp) {
		if (idx >= cost.length) return 0;
		if (dp[idx] != 0) return dp[idx];

		return dp[idx] = Math.min(minCostClimbingStairs_03_(idx + 1, cost, dp), minCostClimbingStairs_03_(idx + 2, cost, dp)) + cost[idx];
	}

	public static int minCostClimbingStairs_03(int[] cost) {
		int[] dp = new int[cost.length];
		minCostClimbingStairs_03_(0, cost, dp);
		return Math.min(dp[0], dp[1]);
	}

	public int minCostClimbingStairs(int[] cost) {
		int[] dp = new int[cost.length + 1];
		// minCostClimbingStairs_(cost.length,cost,dp);
		// minCostClimbingStairs_DP(cost.length,cost,dp);

		return minCostClimbingStairs_fast(cost);

		// return dp[dp.length-1];

	}

	public static int minCostPath(int[][] cost, int sr, int sc, int[][] dp) {
		if (sr == cost.length - 1 && sc == cost[0].length - 1) return dp[sr][sc] = cost[sr][sc];

		int minAns = (int) 1e7;
		if (sr + 1 < cost.length) minAns = Math.min(minAns, minCostPath(cost, sr + 1, sc, dp));
		if (sc + 1 < cost[0].length) minAns = Math.min(minAns, minCostPath(cost, sr, sc + 1, dp));
		if (sr + 1 < cost.length && sc + 1 < cost[0].length) minAns = Math.min(minAns, minCostPath(cost, sr + 1, sc + 1, dp));

		return dp[sr][sc] = minAns + cost[sr][sc];
	}

	public static int minCostPath_DP(int[][] cost, int sr, int sc, int[][] dp) {

		for (sr = cost.length - 1; sr >= 0; sr--) {
			for (sc = cost[0].length - 1; sc >= 0; sc--) {
				if (sr == cost.length - 1 && sc == cost[0].length - 1) {
					dp[sr][sc] = cost[sr][sc];
					continue;
				}

				int minAns = (int) 1e7;
				if (sr + 1 < cost.length) minAns = Math.min(minAns, dp[sr + 1][sc]);
				if (sc + 1 < cost[0].length) minAns = Math.min(minAns, dp[sr][sc + 1]);
				if (sr + 1 < cost.length && sc + 1 < cost[0].length) minAns = Math.min(minAns, dp[sr + 1][sc + 1]);

				return dp[sr][sc] = minAns + cost[sr][sc];
			}
		}

		return dp[0][0];

	}

	public static int goldMineRec_(int[][] mat, int sr, int sc, int[][] dp) {
		if (sc == mat[0].length - 1) return dp[sr][sc] = mat[sr][sc];

		if (dp[sr][sc] != 0) return dp[sr][sc];

		int cost = 0;
		cost = Math.max(cost, (sr - 1) < 0 ? 0 : goldMineRec_(mat, sr - 1, sc + 1, dp));
		cost = Math.max(cost, goldMineRec_(mat, sr, sc + 1, dp));
		cost = Math.max(cost, (sr + 1) >= mat.length ? 0 : goldMineRec_(mat, sr + 1, sc + 1, dp));

		return dp[sr][sc] = cost + mat[sr][sc];
	}

	public static int goldMineDP(int[][] mat, int[][] dp) {

		for (int sc = mat[0].length - 1; sc >= 0; sc--) {
			for (int sr = mat.length - 1; sr >= 0; sr--) {

				if (sc == mat[0].length - 1) {
					dp[sr][sc] = mat[sr][sc];
					continue;
				}

				int cost = 0;
				cost = Math.max(cost, (sr - 1) < 0 ? 0 : dp[sr - 1][sc]);
				cost = Math.max(cost, dp[sr][sc]);
				cost = Math.max(cost, (sr + 1) >= mat.length ? 0 : dp[sr + 1][sc]);

				dp[sr][sc] = cost + mat[sr][sc];

			}
		}

		int maxAns = 0;
		for (int i = 0; i < mat.length; i++) {
			maxAns = Math.max(maxAns, dp[i][0]);
		}

		return maxAns;

	}

	public static int goldMine(int[][] mat) {
		int[][] dp = new int[mat.length][mat[0].length];
		int maxAns = 0;
		for (int i = 0; i < mat.length; i++) {
			maxAns = Math.max(maxAns, goldMineRec_(mat, i, 0, dp));
		}

		// maxAns=goldMineDP(mat,dp);

		return maxAns;
	}

	public static int pairAndSingle_01(int n, int[] dp) {
		if (n <= 1) return dp[n] = 1;
		if (dp[n] != 0) return dp[n];

		int singleWays = pairAndSingle_01(n - 1, dp);
		int pairUpWays = pairAndSingle_01(n - 2, dp) * (n - 1);

		return dp[n] = singleWays + pairUpWays;
	}

	public static int pairAndSingle_DP(int N, int[] dp) {

		for (int n = 0; n <= N; n++) {

			if (n <= 1) {
				dp[n] = 1;
				continue;
			}

			int singleWays = dp[n - 1];
			int pairUpWays = dp[n - 2] * (n - 1);

			dp[n] = singleWays + pairUpWays;
		}

		return dp[N];
	}

	public static int pairAndSingle_fast(int n) {
		if (n <= 1) return 1;

		int a = 1;
		int b = 1;
		int ans = 1;

		for (int i = 2; i <= n; i++) {
			ans = b + a * (i - 1); // b is (n-1) and a is (n-2)
			a = b;
			b = ans;
		}

		return ans;
	}

	public static int divideItemsInKGroup(int n, int k, int[][] dp) {
		if (n < k) return 0;
		if (n == k || k == 1) return dp[n][k] = 1;

		if (dp[n][k] != 0) return dp[n][k];

		int aNewSet = divideItemsInKGroup(n - 1, k - 1, dp);

		int noOfSolution = divideItemsInKGroup(n - 1, k, dp); // no of solution when A chosse to be part of created set.=
		int totalSet = noOfSolution * k;

		return dp[n][k] = aNewSet + totalSet;
	}

	public static int divideItemsInKGroup_DP(int N, int K, int[][] dp) {

		for (int n = 1; n <= N; n++) {
			for (int k = 1; k <= K; k++) {

				if (n < k) continue;
				if (n == k || k == 1) {
					dp[n][k] = 1;
					continue;
				}
				int aNewSet = dp[n - 1][k - 1];

				int noOfSolution = dp[n - 1][k]; // no of solution when A chosse to be part of created set.=
				int totalSet = noOfSolution * k;

				dp[n][k] = aNewSet + totalSet;
			}

		}

		return dp[N][K];

	}

	//stringSet.=================================================

	public static boolean[][] isPalindromicSubString(String str) {
		boolean[][] dp = new boolean[str.length()][str.length()];

		for (int gap = 0; gap < str.length(); gap++) {
			for (int si = 0, ei = gap; ei < str.length(); si++, ei++) {
				if (gap == 0) dp[si][ei] = true;
				else if (str.charAt(si) == str.charAt(ei) && gap == 1) dp[si][ei] = true;
				else dp[si][ei] = str.charAt(si) == str.charAt(ei) && dp[si + 1][ei - 1];
			}
		}

		return dp;
	}

	public static int longestPalindromicSubstring(String str) {
		int[][] dp = new int[str.length()][str.length()];
		int maxLength = 0;

		for (int gap = 0; gap < str.length(); gap++) {
			for (int si = 0, ei = gap; ei < str.length(); si++, ei++) {

				if (gap == 0) dp[si][ei] = 1;
				else if (str.charAt(si) == str.charAt(ei) && gap == 1) dp[si][ei] = 2;
				else if (str.charAt(si) == str.charAt(ei) && dp[si + 1][ei - 1] != 0) {
					dp[si][ei] = dp[si + 1][ei - 1] + 2;
				}
				maxLength = Math.max(maxLength, dp[si][ei]);
			}
		}

		return maxLength;

	}

	public static int longestPalindromicSubsubsequence_Rec(String str, int si, int ei, int[][] dp) {
		if (si > ei) return 0;
		if (si == ei) return dp[si][ei] = 1;

		if (dp[si][ei] != 0) return dp[si][ei];

		if (str.charAt(si) == str.charAt(ei)) {
			return dp[si][ei] = longestPalindromicSubsubsequence_Rec(str, si + 1, ei - 1, dp) + 2;
		}

		int strA = longestPalindromicSubsubsequence_Rec(str, si + 1, ei, dp);
		int strB = longestPalindromicSubsubsequence_Rec(str, si, ei - 1, dp);

		return dp[si][ei] = Math.max(strA, strB);
	}

	public static int longestPalindromicSubsubsequence(String str) {
		int[][] dp = new int[str.length()][str.length()];

		for (int gap = 0; gap < str.length(); gap++) {
			for (int si = 0, ei = gap; ei < str.length(); si++, ei++) {

				if (gap == 0) dp[si][ei] = 1; //length 1
				else if (str.charAt(si) == str.charAt(ei)) dp[si][ei] = dp[si + 1][ei - 1] + 2;
				else dp[si][ei] = Math.max(dp[si + 1][ei], dp[si][ei - 1]);
			}
		}

		return dp[0][str.length() - 1];
	}

	public static String longestPalindromicSubsubsequence_String(String str) {
		String[][] dp = new String[str.length()][str.length()];

		for (int gap = 0; gap < str.length(); gap++) {
			for (int si = 0, ei = gap; ei < str.length(); si++, ei++) {

				if (gap == 0) dp[si][ei] = str.charAt(si) + ""; //length 1
				else if (gap == 1 && str.charAt(si) == str.charAt(ei)) dp[si][ei] = str.substring(si, ei + 1);
				else if (str.charAt(si) == str.charAt(ei)) dp[si][ei] = str.charAt(si) + dp[si + 1][ei - 1] + str.charAt(ei);
				else dp[si][ei] = dp[si + 1][ei].length() >= dp[si][ei - 1].length() ? dp[si + 1][ei] : dp[si][ei - 1];
			}
		}

		return dp[0][str.length() - 1];
	}

	public static int countOfPalindromicSubstring(String str) {
		boolean[][] dp = new boolean[str.length()][str.length()];
		int count = 0;
		for (int gap = 0; gap < str.length(); gap++) {
			for (int si = 0, ei = gap; ei < str.length(); si++, ei++) {
				if (gap == 0) dp[si][ei] = true;
				else if (str.charAt(si) == str.charAt(ei) && gap == 1) dp[si][ei] = true;
				else dp[si][ei] = str.charAt(si) == str.charAt(ei) && dp[si + 1][ei - 1];

				count = dp[si][ei] ? count + 1 : count;
			}
		}

		return count;

	}

	public static int countOfPalindromicSubsubsequence_Rec(String str, int si, int ei, int[][] dp) {
		if (si > ei) return 0;
		if (si == ei) return dp[si][ei] = 1;

		if (dp[si][ei] != 0) return dp[si][ei];

		int middleString = countOfPalindromicSubsubsequence_Rec(str, si + 1, ei - 1, dp);
		int withoutFirstCharString = countOfPalindromicSubsubsequence_Rec(str, si + 1, ei, dp);
		int withoutLastCharString = countOfPalindromicSubsubsequence_Rec(str, si, ei - 1, dp);

		int rAns = withoutFirstCharString + withoutLastCharString;

		return dp[si][ei] = (str.charAt(si) == str.charAt(ei) ? rAns + 1 : rAns - middleString);
	}

	public static int countOfPalindromicSubsubsequence_DP(String str, int si, int ei, int[][] dp) {

		for (int gap = 0; gap < str.length(); gap++) {
			for (si = 0, ei = gap; ei < str.length(); si++, ei++) {
				if (gap == 0) dp[si][ei] = 1; //length 1
				else if (str.charAt(si) == str.charAt(ei)) dp[si][ei] = dp[si + 1][ei] + dp[si][ei - 1] + 1;
				else dp[si][ei] = dp[si + 1][ei] + dp[si][ei - 1] - dp[si + 1][ei - 1];
			}
		}

		return dp[0][str.length() - 1];
	}

	public static int longestCommonSubsequnece_Rec(String str1, String str2, int i, int j, int[][] dp) {
		if (i == str1.length() || j == str2.length()) return 0;

		if (dp[i][j] != 0) return dp[i][j];

		if (str1.charAt(i) == str2.charAt(j)) return dp[i][j] = longestCommonSubsequnece_Rec(str1, str2, i + 1, j + 1, dp) + 1;

		int a = longestCommonSubsequnece_Rec(str1, str2, i + 1, j, dp);
		int b = longestCommonSubsequnece_Rec(str1, str2, i, j + 1, dp);
		return dp[i][j] = Math.max(a, b);
	}

	static int maxAnsSubstring = 0;
	public static int longestCommonSubstring_Rec(String str1, String str2, int i, int j, int[][] dp) {
		if (i == str1.length() || j == str2.length()) return 0;

		if (dp[i][j] != -1) return dp[i][j];

		int a = 0;
		if (str1.charAt(i) == str2.charAt(j)) {
			a = longestCommonSubsequnece_Rec(str1, str2, i + 1, j + 1, dp) + 1;
			maxAnsSubstring = Math.max(maxAnsSubstring, a);
		}
		longestCommonSubsequnece_Rec(str1, str2, i + 1, j, dp);
		longestCommonSubsequnece_Rec(str1, str2, i, j + 1, dp);

		return dp[i][j] = a;
	}

	public static int longestCommonSubsequnece_DP(String str1, String str2, int i, int j, int[][] dp) {

		for (i = str1.length() - 1; i >= 0; i--) {
			for (j = str2.length() - 1; j >= 0; j--) {

				if (str1.charAt(i) == str2.charAt(j)) dp[i][j] = dp[i + 1][j + 1] + 1;
				else dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
			}
		}

		return dp[0][0];
	}

	public static int longestCommonSubstring_DP(String str1, String str2, int i, int j, int[][] dp) {
		int max = 0;
		for (i = str1.length() - 1; i >= 0; i--) {
			for (j = str2.length() - 1; j >= 0; j--) {

				if (str1.charAt(i) == str2.charAt(j)) {
					dp[i][j] = dp[i + 1][j + 1] + 1;
					max = Math.max(dp[i][j], max);
				}
			}
		}

		return max;
	}

	public static int editDistance(String str1, String str2) {
		int[][] dp = new int[str1.length() + 1][str2.length() + 1];

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				if (i == 0) dp[i][j] = j;
				else if (j == 0) dp[i][j] = i;
				else if (str1.charAt(i - 1) == str2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
				else dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
			}
		}

		return dp[str1.length()][str2.length()];
	}

	public static int string_occurs_given_string(String str1, String str2, int n, int m, int[][] dp) {
		if (m == 0) return dp[n][m] = 1;
		if (m > n) return 0;

		if (dp[n][m] != 0) return dp[n][m];

		if (str1.charAt(n - 1) == str2.charAt(m - 1)) return dp[n][m] = string_occurs_given_string(str1, str2, n - 1, m - 1, dp) + string_occurs_given_string(str1, str2, n - 1, m, dp);

		return dp[n][m] = string_occurs_given_string(str1, str2, n - 1, m, dp);
	}

	public static int string_occurs_given_string_02(String str1, String str2, int i, int j, int[][] dp) {
		if (j == str2.length() || (i == str1.length() && j == str2.length())) return dp[i][j] = 1;
		if (i == str1.length() || (str2.length() - j) > (str1.length() - i)) return 0;

		if (dp[i][j] != 0) return dp[i][j];

		if (str1.charAt(i) == str2.charAt(j)) return dp[i][j] = string_occurs_given_string_02(str1, str2, i + 1, j + 1, dp) + string_occurs_given_string_02(str1, str2, i + 1, j, dp);

		return dp[i][j] = string_occurs_given_string_02(str1, str2, i + 1, j, dp);
	}

	public static int string_occurs_given_string_DP(String str1, String str2, int i, int j, int[][] dp) {
		for (i = str1.length(); i >= 0; i--) {
			for (j = str2.length(); j >= 0; j--) {
				if (j == str2.length() || (i == str1.length() && j == str2.length())) {
					dp[i][j] = 1;
					continue;
				}
				if (i == str1.length() || (str2.length() - j) > (str1.length() - i)) continue;

				if (str1.charAt(i) == str2.charAt(j)) dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
				else dp[i][j] = dp[i + 1][j];
			}
		}

		return dp[0][0];
	}

	//leetcode: decode ways 91.======================================================
	public int numDecodings_Rec(String s,int idx,int[] dp) {
		if(idx==s.length()) return dp[idx]=1;
		
		char ch=s.charAt(idx);
		int count=0;
		
		if(dp[idx]!=0) return dp[idx];

		if(ch!='0')
		   count+=numDecodings_Rec(s,idx+1,dp);

		if(idx+1<s.length()){
			char ch2=s.charAt(idx+1);
			int val=(ch-'0')*10 + (ch2-'0');
			  if(val>=10 && val<=26)
			    count+=numDecodings_Rec(s,idx+2,dp);
		}

		return dp[idx]=count;		
	}
	
	public int numDecodings_DP(String s,int[] dp) {
		for(int idx=s.length();idx>=0;idx--){

			if(idx==s.length()){
				dp[idx]=1;
				continue;
			}

			char ch=s.charAt(idx);
			int count=0;
			if(ch!='0')
			   count+=dp[idx+1];
	
			if(idx+1<s.length()){
				char ch2=s.charAt(idx+1);
				int val=(ch-'0')*10 + (ch2-'0');
				  if(val>=10 && val<=26)
					count+=dp[idx+2];
			}
	
		    dp[idx]=count;	
		}

		return dp[0];
    }
	
	public int numDecodings(String s) {
		int[] dp=new int[s.length()+1];
		
		// return numDecodings_Rec(s,0,dp);
		return numDecodings_DP(s,dp);
		
	}
	
    public int numDecodingsII(String s) {
		long[] dp=new long[s.length()+1];
	
	return (int)decodeWaysII(s,0,dp);
}

	static int m=(int)1e9+7;
public static long decodeWaysII(String s,int idx,long[] dp){
	if(idx==s.length()) return dp[idx]=1;

	if(dp[idx]!=0) return dp[idx];
	
	long count=0;
	char ch=s.charAt(idx);
	if(ch=='*'){
		count=(count + 9*decodeWaysII(s,idx+1,dp))%m;
		if(idx+1< s.length()){
			char ch2=s.charAt(idx+1);
			if(ch2 >='0' && ch2<='6') count=(count + 2 * decodeWaysII(s,idx+2,dp))%m;
			if(ch2>='7' && ch2<='9') count=(count + decodeWaysII(s,idx+2,dp))%m;
			if(ch2=='*') count=(count + 15 * decodeWaysII(s,idx+2,dp))%m;
		}
		


	}else if(ch!='0'){
		  count=(count + decodeWaysII(s,idx+1,dp))%m;
		 
		  if(idx+1< s.length()){
			  char ch2=s.charAt(idx+1);
			  if(ch2=='*'){
				  if(ch =='1') count=(count + 9 * decodeWaysII(s,idx+2,dp))%m;
				  else if(ch=='2') count=(count + 6 * decodeWaysII(s,idx+2,dp))%m;


			  }else{
				int val=(ch-'0')*10 + (ch2-'0');
				if(val<=26)
				count=(count + decodeWaysII(s,idx+2,dp))%m;
			  }
		  }
	}
	return dp[idx]=count;
}

	//targetSet.===================================================================

	public static int coinChangePermuatation_Rec(int[] coins, int tar) {
		if (tar == 0) return 1;

		int count = 0;
		for (int c: coins) {
			if (tar - c >= 0) count += coinChangePermuatation_Rec(coins, tar - c);
		}

		return count;
	}

	public static int coinChangePermuatation(int[] coins, int tar) {

		int[] dp = new int[tar + 1];
		dp[0] = 1;

		for (int t = 0; t <= tar; t++) {
			for (int c: coins) {
				if (t - c >= 0) {
					dp[t] += dp[t - c];
				}
			}
		}

		return dp[tar];
	}

	public static int coinChangeCombination(int[] coins, int tar) {

		int[] dp = new int[tar + 1];
		dp[0] = 1;
		for (int c: coins) {
			for (int t = 0; t <= tar; t++) {
				if (t - c >= 0) {
					dp[t] += dp[t - c];
				}
			}
		}

		return dp[tar];
	}

	public static int linearEquationOfNvariables(int[] coeff, int y) {
		return coinChangeCombination(coeff, y);
	}

	//leetcode 322
	public int coinChangeMinHeight_rec(int[] coins, int tar, int[] dp) {
		if (tar == 0) return 0;

		if (dp[tar] != 0) return dp[tar];

		int height = (int) 1e8;
		for (int c: coins) {
			if (tar - c >= 0) {
				int recH = coinChangeMinHeight_rec(coins, tar - c, dp);
				if (recH != (int) 1e8) height = Math.min(height, recH + 1);
			}
		}

		return dp[tar] = height;
	}

	public int coinChangeMinHeight_DP(int[] coins, int tar, int[] dp) {
		Arrays.fill(dp, 100000000);
		dp[0] = 0;

		for (int t = 1; t <= tar; t++) {
			for (int c: coins) {
				if (t - c >= 0) {
					// dp[t]=Math.min(dp[t],dp[t-c]+1);
					int recH = dp[t - c];
					if (recH != (int) 1e8) dp[t] = Math.min(dp[t], recH + 1);
				}
			}
		}

		return dp[tar];
	}

	public int coinChange(int[] coins, int tar) {
		int[] dp = new int[tar + 1];
		int ans = 0;
		// ans=coinChangeMinHeight_rec(coins,tar,dp);
		ans = coinChangeMinHeight_DP(coins, tar, dp);

		return ans != (int) 1e8 ? ans: -1;
	}

	public static int targetSum(int[] arr, int tar, int idx, int[][] dp) { // dp: arr X tar
		if (tar == 0 || idx == arr.length) {
			return dp[idx][tar] = tar == 0 ? 1 : 0;
		}

		if (dp[idx][tar] != 0) return dp[idx][tar];

		int count = 0;
		if (tar - arr[idx] >= 0) count += targetSum(arr, tar - arr[idx], idx + 1, dp);

		count += targetSum(arr, tar, idx + 1, dp);

		return dp[idx][tar] = count;
	}

	public static int targetSum_DP(int[] arr, int tar, int[][] dp) { // dp: arr X tar
		dp[0][0] = 1;

		for (int idx = 1; idx < dp.length; idx++) {
			for (int t = 0; t <= tar; t++) {

				int count = 0;
				if (t - arr[idx - 1] >= 0) {
					count += dp[idx - 1][t - arr[idx - 1]];
				}
				count += dp[idx - 1][t];

				dp[idx][t] = count;
			}
		}

		return dp[dp.length - 1][dp[0].length - 1];
	}

	int knapsack_01_rec(int[] weight, int[] value, int w, int idx, int[][] dp) { // dp: weight X W
		if (w == 0 || idx == weight.length) {
			return 0;
		}
		if (dp[idx][w] != 0) return dp[idx][w];

		int pick = 0;
		if (w - weight[idx] >= 0) {
			pick = knapsack_01_rec(weight, value, w - weight[idx], idx + 1, dp) + value[idx];
		}

		int notPicked = knapsack_01_rec(weight, value, w, idx + 1, dp);

		return dp[idx][w] = Math.max(pick, notPicked);
	}

	int knapsack_01_rec_01(int[] weight, int[] value, int w, int n, int[][] dp) { // dp: weight X W, n is size of weight
		if (w == 0 || n == 0) {
			return 0;
		}
		if (dp[n][w] != 0) return dp[n][w];

		int pick = 0;
		if (w - weight[n - 1] >= 0) {
			pick = knapsack_01_rec(weight, value, w - weight[n - 1], n - 1, dp) + value[n - 1];
		}

		int notPicked = knapsack_01_rec(weight, value, w, n - 1, dp);

		return dp[n][w] = Math.max(pick, notPicked);
	}

	int knapsack_01_rec_DP(int[] weight, int[] value, int W, int[][] dp) { // dp: weight X W, n is size of weight
		dp[0][0] = 0;
		for (int idx = 1; idx < dp.length; idx++) {
			for (int w = 0; w <= W; w++) {

				int pick = 0;
				if (w - weight[idx - 1] >= 0) {
					pick = dp[idx - 1][w - weight[idx - 1]] + value[idx - 1];
				}

				int notPicked = dp[idx - 1][w];

				dp[idx][w] = Math.max(pick, notPicked);
			}
		}

		return dp[dp.length - 1][dp[0].length - 1];
	}

	int unBoundedKnapsack(int[] weight, int[] value, int W) {
		int[] dp = new int[W + 1];

		for (int idx = 0; idx < weight.length; idx++) {
			for (int w = 1; w <= W; w++) {
                int picked=0;
				if (w - weight[idx] >= 0) picked = dp[w - weight[idx]] + value[idx];
				int unPicked = dp[w];

				dp[w] = Math.max(picked, unPicked);
			}
		}

		return dp[W];
	}

	//LIS_Type.=================================================================

	// LIS from left to right
	public static int LIS_DP(int[] arr, int[] dp) {

		dp[0] = 1;
		int maxLen = 0;
		for (int i = 1; i < arr.length; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) { // har ek j cell apne ko include krke apne tak ka maximum Increasing subsequnece store krke rakhta hai.
				if (arr[i] > arr[j]) { // if i'th cell element is grater than j'th cell element than length will increase by 1.
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			maxLen = Math.max(maxLen, dp[i]);
		}

		return maxLen;
	}


	//LIS from right to left
	public static int LDS_DP(int[] arr, int[] dp) {
        int n=arr.length;
		dp[n-1] = 1;
		int maxLen = 0;
		for (int i = n-2 ; i >= 0 ;i--) {
			dp[i] = 1;
			for (int j = i+1; j < n; j++) { // har ek j cell apne ko include krke apne tak ka maximum Increasing subsequnece store krke rakhta hai.
				if (arr[i] > arr[j]) { // if i'th cell element is grater than j'th cell element than length will increase by 1.
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			maxLen = Math.max(maxLen, dp[i]);
		}

		return maxLen;
	}

	public static int LBS_DP(int[] arr){
	    int n=arr.length;

		int[] LIS=new int[n];
		int[] LDS=new int[n];

		LIS_DP(arr,LIS);
		LDS_DP(arr,LDS);

		int maxLen = 0;
		for(int i=0;i<n;i++){
			int bitonicLen=LIS[i]+LDS[i]-1;
			maxLen=Math.max(maxLen,bitonicLen);
		}

		return maxLen;
	}

	public static int maximumSumIncreasingSubsequnece(int[] arr){
		int n=arr.length;
		int[] dp=new int[n];
        int maxSum=0;
		for(int i=0;i<n;i++){
			  dp[i]=arr[i];
			for(int j=0;j<i;j++){
				if(arr[i]>arr[j]){
					dp[i]=Math.max(dp[i],dp[j]+arr[i]);
				}
			}
			maxSum=Math.max(maxSum,dp[i]);
		}

		return maxSum;
	}
	
	// minimum no of deletion to make array in sorted order in increasing order.
	public static int minimum_No_of_deletion(int[] arr){
		int n=arr.length;
		int[] dp=new int[n];

		dp[0] = 1;
		int maxLen = 0;
		for (int i = 1; i < arr.length; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) { 
				if (arr[i] >= arr[j]) { 
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			maxLen = Math.max(maxLen, dp[i]);
		}

		return n-maxLen;
	}

	public static int LDS_from_left_right(int[] arr,int[] dp){
		int n=arr.length;
		dp[0]=1;

		int maxLen = 0;
		for(int i=1;i<n;i++){
			dp[i]=1;
			for(int j=0;j<i;j++){
				if(arr[i]<arr[j])
				dp[i] = Math.max(dp[i], dp[j] + 1);
			}
		   maxLen = Math.max(maxLen, dp[i]);

		}

		return maxLen;
	}

	public static int LDS_from_Right_left(int[] arr,int[] dp){
		int n=arr.length;
		dp[n-1]=1;
        
        int maxLen = 0;
		for(int i=n-2;i>=0;i--){
			dp[i]=1;
			for(int j=i+1;j<n;j++){
				if(arr[i]<arr[j])
				dp[i] = Math.max(dp[i], dp[j] + 1);
			}
		   maxLen = Math.max(maxLen, dp[i]);
		}

		return maxLen;
	}

	public static int LBS_DP_02(int[] arr){
	    int n=arr.length;

		int[] LIS=new int[n];
		int[] LDS=new int[n];

		LDS_from_left_right(arr,LIS);
		LDS_from_Right_left(arr,LDS);

		int maxLen = 0;
		for(int i=0;i<n;i++){
			int bitonicLen=LIS[i]+LDS[i]-1;
			maxLen=Math.max(maxLen,bitonicLen);
		}

		return maxLen;
	}
	
	// leetcode 673
	public static int findNumberOfLIS(int[] arr) {
		if(arr.length<=1) return arr.length;
		
		int n=arr.length;
		int[] dp=new int[n];
		int[] count=new int[n];
		
		int maxLen=0;
		int maxCount=0;
		
		for(int i=0;i<n;i++){
			dp[i]=1;
			count[i]=1;
			for(int j=0;j<i;j++){
			  if(arr[i]>arr[j]){
                 if(dp[j] + 1 > dp[i]){
					 dp[i]=dp[j]+1;
					 count[i]=count[j];
				 }else if(dp[j]+1==dp[i]){
					 count[i]+=count[j];
				 }
			  }	
			}

			if(dp[i]>maxLen){
				maxLen=dp[i];
				maxCount=count[i];
			}else if(dp[i]==maxLen){
				maxCount+=count[i];
			}
		}

		return maxCount;

		
	}

	//leetcode 354 =================================================

	public static int maxEnvelopes(int[][] arr) {

		Arrays.sort(arr,(a, b)->{ 
			// 0'th index-> width and 1'st index -> height, 
			// for C++: sort(arr.begin(),arr.end(),[](vector<int>& a,vector<int>& b){ //logic });
			if(a[0]==b[0]) return b[1] - a[1];   // reverse sort.  for c++ replace '-' with '<'
			else return a[0]-b[0];   // default sort.  for c++ replace '-' with '<'
		});

		int n=arr.length;
		int[] dp=new int[n];
		int maxLen = 0;
		for (int i =0;i<n;i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) { 
				if (arr[i][1] > arr[j][1]) { // compare height. 
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			maxLen = Math.max(maxLen, dp[i]);
		}

		return maxLen;
	}
	
	
	//cutTypeQuestion.=====================================================

	public static int MCM_rec(int[] arr,int st,int end,int[][] dp){
		if(st+1==end) return dp[st][end] = 0;
		
		if(dp[st][end]!=-1) return dp[st][end];

        int min_=(int) 1e8;
		for(int cut=st+1;cut<end;cut++){
			int leftOptimalCost=MCM_rec(arr,st,cut,dp);
			int rightOptimalCost=MCM_rec(arr,cut,end,dp);

			int myCost=leftOptimalCost +  arr[st] * arr[cut] * arr[end]   + rightOptimalCost;
            min_=Math.min(min_,myCost);
		}

		return dp[st][end]=min_;
	}

	public static int MCM_DP(int[] arr,int n,int[][] dp,String[][] sdp){
		for(int gap=1;gap<n;gap++){
			for(int st=0,end=gap;end<n;st++,end++){
				if(st+1==end) {
					dp[st][end] = 0;
					sdp[st][end]=(char)(st+'A') + "";
				    continue;
				}

				int min_=(int) 1e8;
				String mins="";
		        for(int cut=st+1;cut<end;cut++){
			        int leftOptimalCost=dp[st][cut];
			        int rightOptimalCost=dp[cut][end];

			        int myCost=leftOptimalCost +  arr[st] * arr[cut] * arr[end]   + rightOptimalCost;
					
					if(myCost<min_){
						min_=myCost;
						mins=  "(" + sdp[st][cut]+sdp[cut][end] + ")";
					}
		        }

				dp[st][end]=min_;
				sdp[st][end]=mins;
				
			}

		}
		System.out.print(sdp[0][n-1] + " -> ");
		return dp[0][n-1];
	}

	//leetcode 132.===========================================================
	//Time: O(n3)
	public static int minCut_(int st,int end,int[][] dp,boolean[][] isPalindrome){
		if(st==end || isPalindrome[st][end]) return dp[st][end]=0; 
		
		if(dp[st][end]!=-1) return dp[st][end];

        int min_=(int) 1e8;
		for(int cut=st;cut<end;cut++){
			int leftMinCut=isPalindrome[st][cut]?0:minCut_(st,cut,dp,isPalindrome);
			int rightMinCut=isPalindrome[cut+1][end]?0:minCut_(cut+1,end,dp,isPalindrome);

			int myCost=leftMinCut +  1   + rightMinCut;
            min_=Math.min(min_,myCost);
		}

		return dp[st][end]=min_;
	}

    //Time: O(n2)
	public static int minCut_02(int st,int end,int[] dp,boolean[][] isPalindrome){
		if(st>end) return -1;
		if(dp[st]!=-1) return dp[st];

        int min_=(int) 1e8;
		for(int cut = st;cut <=end;cut++){
			if(isPalindrome[st][cut]){
				int cuts_ = minCut_02(cut+1,end,dp,isPalindrome)+1;
				min_=Math.min(min_,cuts_);
			}
		}

		return dp[st]=min_;
	}

	public static int minCut_02_DP(int st,int end,int[] dp,boolean[][] isPalindrome){
		for( st=end;st>=0;st--){
			int min_=(int) 1e8;
			for(int cut = st;cut <=end;cut++){
				if(isPalindrome[st][cut]){
					int cuts_ = (( cut + 1==end+1 )? -1 : dp[cut+1]) + 1;
					min_=Math.min(min_, cuts_);
				}
			}
	
			dp[st]=min_;
		}
		return dp[0];
	}


	public static int minCut(String str) {
		int n=str.length();
		int[] dp=new int[n];
		boolean[][] isPalindrome=new boolean[n][n];

		for(int i=0;i<n;i++) dp[i]=-1;

		for (int gap = 0; gap < n; gap++) {
			for (int si = 0, ei = gap; ei < n; si++, ei++) {
				if (gap == 0) isPalindrome[si][ei] = true;
				else if (str.charAt(si) == str.charAt(ei) && gap == 1) isPalindrome[si][ei] = true;
				else isPalindrome[si][ei] = str.charAt(si) == str.charAt(ei) && isPalindrome[si + 1][ei - 1];
			}
		}

		return minCut_02(0,n-1,dp,isPalindrome);
    }


	public static void PathSeries() {
		int er = 3;
		int ec = 3;
		int sr = 0;
		int sc = 0;

		int ans = 0;
		// int[][] dp = new int[er + 1][ec + 1];

		// ans = mazePathHV_rec(sr, sc, er, ec, dp);

		// ans = mazePathMulti_rec(sr, sc, er, ec, dp);
		// ans = mazePathMulti_DP(sr, sc, er, ec, dp);

		// int si=0;
		// int ei=10;
		// int[] dp=new int[ei+1];
		// ans=boradPath(si,ei,dp);
		// ans=boradPath_DP(si,ei,dp);
		// ans=boradPath_opti(si,ei,dp);

		// climbStair_DP(8);

		// goldMine(arr);

		// int n = 6;
		// int[] dp = new int[n + 1];
		// ans = pairAndSingle_01(n, dp);

		int n = 11,
		k = 4;
		int[][] dp = new int[n + 1][k + 1];
		// ans = divideItemsInKGroup(n, k, dp);
		ans = divideItemsInKGroup_DP(n, k, dp);

		// display(dp);
		display2D(dp);
		System.out.println(ans);

	}

	public static void stringSet() {
		// String str="geeksse";
		// int[][] dp=new int[str.length()][str.length()];
		// System.out.println(longestPalindromicSubsubsequence_Rec(str,0,str.length()-1,dp));
		// System.out.println(longestPalindromicSubsubsequence(str));
		// System.out.println(longestPalindromicSubsubsequence_String(str));

		// String str="baccbab";
		// int[][] dp=new int[str.length()][str.length()];
		// // System.out.println(countOfPalindromicSubsubsequence_Rec(str,0,str.length()-1,dp));
		// System.out.println(countOfPalindromicSubsubsequence_DP(str,0,str.length()-1,dp));

		// String str1="abcde";
		// String str2="ace";
		// int[][] dp=new int[str1.length()+1][str2.length()+1];
		// System.out.println(longestCommonSubsequnece_Rec(str1,str2,0,0,dp));
		// System.out.println(longestCommonSubstring_Rec(str1,str2,0,0,dp));

		String str1 = "aabbcc";
		String str2 = "abc";
		int[][] dp = new int[str1.length() + 1][str2.length() + 1];
		// System.out.println(string_occurs_given_string_DP(str1,str2,0,0,dp));
		// System.out.println(string_occurs_given_string_02(str1,str2,0,0,dp));
		System.out.println(string_occurs_given_string(str1, str2, str1.length(), str2.length(), dp));

		display2D(dp);
	}

	public static void targetSet() {
		int[] arr={2,3,1,5,6};
		// int[] arr = {1,1,1,1,1};
		int tar = 3;
		int ans = 0;

		int[][] dp = new int[arr.length + 1][tar + 1];
		// ans=targetSum(arr,tar,0,dp);
		// ans += targetSum_DP(arr, tar, dp);

		// display(dp);
		display2D(dp);
		System.out.println(ans);
	}

	public static void LIS_Type() {
		// int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
		int[] arr={1,3,5,4,7};
		int[] dp=new int[arr.length];

		int ans=0;
		// ans = LIS_DP(arr,dp);
		// ans = LDS_DP(arr,dp);
		// ans =LBS_DP(arr);

		ans=findNumberOfLIS(arr);

		
		System.out.println(ans);
		// display(dp);
	}

	public static void cutType(){
		int[] arr={10, 20, 30, 40, 30};
		int ans=0;
		int n=arr.length;

		int[][] dp=new int[n][n];
		String[][] sdp=new String[n][n];
		for(int i=0;i<n;i++) for(int j=0;j<n;j++) dp[i][j]=-1;

		// ans=MCM_rec(arr,0,n-1,dp);
		// ans=MCM_DP(arr,n,dp,sdp);
		String str="bbbbbb";
		ans=minCut(str);


		System.out.println(ans);
		display2D(dp);
	}
	

	//util.=================================================================

	public static void display2D(int[][] arr) {
		for (int[] ar: arr) {
			for (int ele: ar) {
				System.out.print(ele + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void display(int[] arr) {
		for (int ele: arr) System.out.print(ele + " ");
		System.out.println();
	}

	public static void solve() {
		// basic();
		// PathSeries();
		// stringSet();
		// targetSet();
		// LIS_Type();
		cutType();

	}
}