import java.util.ArrayList;

public class l002 {
	public static void main(String[] args) {
		solve();
	}

	public static void solve() {

		// floodFillCate();
        // coinChange();
        sudoku();
	}

	//=================================================================

	public static void floodFillCate() {
		int[][] board = new int[4][4];
		int ans = floodfill_Height(0, 0, 3, 3, board);
		//    pair ansP=floodfill_LongestPath(0,0,3,3,board);
		pair ansP = floodfill_ShortestPath(0, 0, 3, 3, board);

		System.out.println(ansP.str + " -> " + ansP.len);

	}

	static int[][] dir = {
		{
			0,
			1
		},
		{ - 1,
			1
		},
		{ - 1,
			0
		},
		{ - 1,
			-1
		},
		{
			0,
			-1
		},
		{
			1,
			-1
		},
		{
			1,
			0
		},
		{
			1,
			1
		}
	};
	static String[] dirN = {
		"R",
		"E",
		"U",
		"N",
		"L",
		"W",
		"D",
		"S"
	};

	public static int floodfill_Height(int sr, int sc, int er, int ec, int[][] board) {
		if (sr == er && sc == ec) {
			return 0;
		}

		board[sr][sc] = 2;
		int maxH = 0;
		for (int d = 0; d < dir.length; d++) {
			int r = sr + dir[d][0];
			int c = sc + dir[d][1];
			if (r >= 0 && c >= 0 && r < board.length && c < board[0].length && board[r][c] == 0) {
				int recH = floodfill_Height(r, c, er, ec, board);
				maxH = Math.max(recH, maxH);
			}
		}

		board[sr][sc] = 0;
		return maxH + 1;
	}

	static class pair {
		int len = 0;
		String str = "";

		pair(int len, String str) {
			this.len = len;
			this.str = str;
		}
	}

	public static pair floodfill_LongestPath(int sr, int sc, int er, int ec, int[][] board) {
		if (sr == er && sc == ec) {
			return new pair(0, "");
		}

		board[sr][sc] = 2;

		pair mypair = new pair(0, "");

		for (int d = 0; d < dir.length; d++) {
			int r = sr + dir[d][0];
			int c = sc + dir[d][1];
			if (r >= 0 && c >= 0 && r < board.length && c < board[0].length && board[r][c] == 0) {
				pair recP = floodfill_LongestPath(r, c, er, ec, board);

				if (recP.len + 1 > mypair.len) {
					mypair.len = recP.len + 1;
					mypair.str = recP.str + dirN[d];
				}
			}
		}

		board[sr][sc] = 0;
		return mypair;
	}

	public static pair floodfill_ShortestPath(int sr, int sc, int er, int ec, int[][] board) {
		if (sr == er && sc == ec) {
			return new pair(0, ""); //c++ -> INT_MAX;
		}

		board[sr][sc] = 2;

		pair mypair = new pair((int) 1e7, "");

		for (int d = 0; d < dir.length; d++) {
			int r = sr + dir[d][0];
			int c = sc + dir[d][1];
			if (r >= 0 && c >= 0 && r < board.length && c < board[0].length && board[r][c] == 0) {
				pair recP = floodfill_ShortestPath(r, c, er, ec, board);

				if (recP.len + 1 < mypair.len) {
					mypair.len = recP.len + 1;
					mypair.str = dirN[d] + recP.str;
				}
			}
		}

		board[sr][sc] = 0;
		return mypair;
	}

	//CoinTrees.====================================================

	public static int coinChangePermuINFI_01(int[] arr, int tar, String ans) {
		if (tar == 0) {
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (tar - arr[i] >= 0) count += coinChangePermuINFI_01(arr, tar - arr[i], ans + arr[i] + " ");
		}

		return count;
	}

	public static int coinChangePermu_01(int[] arr, int tar, boolean[] vis, String ans) {
		if (tar == 0) {
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (!vis[i] && tar - arr[i] >= 0) {
				vis[i] = true;
				count += coinChangePermu_01(arr, tar - arr[i], vis, ans + i + " ");
				vis[i] = false;
			}
		}
		return count;
	}

	public static int coinChangeCombinationINFI_01(int[] arr, int idx, int tar, String ans) {
		if (tar == 0) {
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int i = idx; i < arr.length; i++) {
			if (tar - arr[i] >= 0) count += coinChangeCombinationINFI_01(arr, i, tar - arr[i], ans + arr[i] + " ");
		}

		return count;
	}

	public static int coinChangeCombination_01(int[] arr, int idx, int tar, String ans) {
		if (tar == 0) {
			System.out.println(ans);
			return 1;
		}

		int count = 0;
		for (int i = idx; i < arr.length; i++) {
			if (tar - arr[i] >= 0) count += coinChangeCombination_01(arr, i + 1, tar - arr[i], ans + arr[i] + " ");
		}

		return count;
	}

	public static int coinChangeCombination_02(int[] arr, int idx, int tar, String ans) {
		if (idx == arr.length || tar == 0) {
			if (tar == 0) {
				System.out.println(ans);
				return 1;
			}

			return 0;
		}

		int count = 0;
		if (tar - arr[idx] >= 0) count += coinChangeCombination_02(arr, idx + 1, tar - arr[idx], ans + arr[idx] + " ");
		count += coinChangeCombination_02(arr, idx + 1, tar, ans);

		return count;
	}

	public static int coinChangeCombinationINFI_02(int[] arr, int idx, int tar, String ans) {
		if (idx == arr.length || tar == 0) {
			if (tar == 0) {
				System.out.println(ans);
				return 1;
			}

			return 0;
		}

		int count = 0;
		if (tar - arr[idx] >= 0) count += coinChangeCombinationINFI_02(arr, idx, tar - arr[idx], ans + arr[idx] + " ");
		count += coinChangeCombinationINFI_02(arr, idx + 1, tar, ans);

		return count;
	}

	public static int coinChangePermuINFI_02(int[] arr, int idx, int tar, String ans) {
		if (idx == arr.length || tar == 0) {
			if (tar == 0) {
				System.out.println(ans);
				return 1;
			}

			return 0;
		}

		int count = 0;
		if (tar - arr[idx] >= 0) count += coinChangePermuINFI_02(arr, 0, tar - arr[idx], ans + arr[idx] + " ");
		count += coinChangePermuINFI_02(arr, idx + 1, tar, ans);

		return count;
	}

	public static int coinChangePermu_02(int[] arr, boolean[] vis, int idx, int tar, String ans) {
		if (idx == arr.length || tar == 0) {
			if (tar == 0) {
				System.out.println(ans);
				return 1;
			}

			return 0;
		}

		int count = 0;
		if (tar - arr[idx] >= 0 && !vis[idx]) {
			vis[idx] = true;
			count += coinChangePermu_02(arr, vis, 0, tar - arr[idx], ans + arr[idx] + " ");
			vis[idx] = false;
		}

		count += coinChangePermu_02(arr, vis, idx + 1, tar, ans);

		return count;
	}

	//sudoku.============================================================

	public static boolean isSafeToPlaceNumber(int[][] board, int r, int c, int num) {

		//for Row
		for (int i = 0; i < board[0].length; i++)
		if (board[r][i] == num) return false;

		//for Col
		for (int i = 0; i < board.length; i++)
		if (board[i][c] == num) return false;

		//3X3 Matrix
		int x = (r / 3) * 3;
		int y = (c / 3) * 3;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[x + i][y + j] == num) return false;
			}
		}

		return true;
	}

	public static boolean sudoku_01(int[][] board, int idx) {
		if (idx == 81) {
            display2D(board);
			return true;
		}

		int r = idx / 9;
		int c = idx % 9;
        boolean res = false;
        
		if (board[r][c] == 0) {
			for (int num = 1; num <= 9 ; num++) {
				if (isSafeToPlaceNumber(board, r, c, num)) {

					board[r][c] = num;
                    res= res || sudoku_01(board, idx + 1);
					board[r][c] = 0;
                }
			}
		} else res= res || sudoku_01(board, idx + 1);

		return res;
    }

    public static boolean sudoku_02(int[][] board,ArrayList<Integer> calls, int idx) {
		if (idx == calls.size()) {
            display2D(board);
			return true;
		}

		int r = calls.get(idx) / 9;
		int c = calls.get(idx) % 9;
        boolean res = false;
        
		for (int num = 1; num <= 9 ; num++) {
			if (isSafeToPlaceNumber(board, r, c, num)) {

				board[r][c] = num;
                res= res || sudoku_02(board,calls, idx + 1);
				board[r][c] = 0;
            }
		}
		return res;
    }

    static int[] row;
    static int[] col;
    static int[][] mat;

    public static int sudoku_03(int[][] board,ArrayList<Integer> calls, int idx) {
		if (idx == calls.size()) {
            display2D(board);
			return 1;
		}

		int r = calls.get(idx) / 9;
		int c = calls.get(idx) % 9;
        int count=0;
        
		for (int num = 1; num <= 9 ; num++) {
            int mask=(1<<num);
            if ((row[r]&mask)==0 && (col[c]&mask)==0 && (mat[r/3][c/3]&mask)==0) {

                board[r][c] = num;
                row[r]^=mask;
                col[c]^=mask;
                mat[r/3][c/3]^=mask;
            
                count+= sudoku_03(board,calls ,idx + 1);
            
                board[r][c] = 0;
                row[r]^=mask;
                col[c]^=mask;
                mat[r/3][c/3]^=mask;
            
            }
		}
		return count;
    }

    //leet`.
	
	//wordBreak and Crypto.=============================================

static HashSet<String> map=new HashSet<>();
public static int wordBreak_(String str, String ans)
{
    if (str.length() == 0)
    {
        System.out.println(ans);
        return 1;
    }

    int count = 0;
    for (int i = 1; i <= str.length(); i++)
    {
        String smallstr = str.substring(0, i);
        if (map.conatins(smallstr))
        {
            count += wordBreak_(str.substr(i), ans + smallstr + " ");
        }
    }

    return count;
}

void wordBreak()
{
    String[] words{"like", "samsung", "i", "ilike", "sam", "sung", "and", "man", "go", "mango"};
    String word = "ilikesamsungandmango";

    for (String word : words)
        map.add(word);

	int ans=wordBreak_(word, "");
	System.out.println(ans);

}
    
    //===========================================================

	public static void coinChange() {
		// int[] arr={2,3,5,7};
		int[] arr = {
			1,
			1,
			1,
			1,
			1
		};
		int tar = 3;
		int ans = 0;
		boolean[] vis = new boolean[arr.length];
		// ans=coinChangePermuINFI_01(arr,tar,"");
		ans = coinChangePermu_01(arr, tar, vis, "");

		// ans=coinChangeCombinationINFI_01(arr,0,tar,"");
		// ans=coinChangeCombination_01(arr,0,tar,"");

		// ans=coinChangePermuINFI_02(arr,0,tar,"");

		// ans=coinChangeCombinationINFI_02(arr,0,tar,"");
		// ans=coinChangeCombination_02(arr,0,tar,"");

		System.out.println(ans);
    }

    public static void sudoku(){
        int[][] board = {{3, 0, 0, 0, 0, 0, 0, 0, 0},  
                      {5, 2, 0, 0, 0, 0, 0, 0, 0},  
                      {0, 8, 7, 0, 0, 0, 0, 3, 1},  
                      {0, 0, 3, 0, 1, 0, 0, 8, 0},  
                      {9, 0, 0, 8, 6, 3, 0, 0, 5},  
                      {0, 5, 0, 0, 9, 0, 6, 0, 0},  
                      {1, 3, 0, 0, 0, 0, 2, 5, 0},  
                      {0, 0, 0, 0, 0, 0, 0, 7, 4},  
                      {0, 0, 5, 2, 0, 6, 3, 0, 0}};  
        
        ArrayList<Integer> calls=new ArrayList<>();
        row=new int[9];
        col=new int[9];
        mat=new int[3][3];

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]==0){
                    calls.add((i*9 + j));
                }else{
                    int mask=(1<<board[i][j]);
                    row[i]^=mask;
                    col[j]^=mask;
                    mat[i/3][j/3]^=mask; 
                    
                }
            }
        }
        // System.out.println(sudoku_01(board,0));
        // System.out.println(sudoku_02(board,calls,0));
        System.out.println(sudoku_03(board,calls,0));
    }
    


    public static void display2D(int[][] arr){
        for(int[] ar: arr){
            for(int ele: ar){
                System.out.print(ele + " ");
            }
            System.out.println();
        }

        System.out.println();

    }

}