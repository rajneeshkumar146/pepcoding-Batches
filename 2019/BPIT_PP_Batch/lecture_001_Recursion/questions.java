public class questions {

  //geeks: https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1

  public static ArrayList<String> printPath(int[][] m, int n) {
    if (
      n == 0 || m[0][0] == 0 || m[n - 1][n - 1] == 0
    ) return new ArrayList<>();

    int[][] dir = { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };
    String[] dirS = { "D", "L", "R", "U" };

    ArrayList<String> res = new ArrayList<>();
    floodFill_(0, 0, n - 1, n - 1, m, dir, dirS, "", res);
    return res;
  }

  public static int floodFill_(
    int sr,
    int sc,
    int er,
    int ec,
    int[][] vis,
    int[][] dir,
    String[] dirS,
    String ans,
    ArrayList<String> res
  ) {
    if (sr == er && sc == ec) {
      res.add(ans);
      return 1;
    }

    vis[sr][sc] = 0;
    int count = 0;

    for (int d = 0; d < 4; d++) {
      int r = sr + dir[d][0];
      int c = sc + dir[d][1];

      if (r >= 0 && c >= 0 && r <= er && c <= ec && vis[r][c] == 1) {
        count += floodFill_(r, c, er, ec, vis, dir, dirS, ans + dirS[d], res);
      }
    }

    vis[sr][sc] = 1;
    return count;
  }

  //Leetcode 1219
  public int getMaximumGold(int[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) return 0;
    int n = grid.length;
    int m = grid[0].length;
    int[][] dir = { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };

    int maxGold = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] != 0) maxGold =
          Math.max(maxGold, getMaximumGold_(grid, i, j, n, m, dir));
      }
    }
    return maxGold;
  }

  public int getMaximumGold_(
    int[][] grid,
    int sr,
    int sc,
    int n,
    int m,
    int[][] dir
  ) {
    grid[sr][sc] = -grid[sr][sc];

    int maxGold = 0;
    for (int d = 0; d < 4; d++) {
      int r = sr + dir[d][0];
      int c = sc + dir[d][1];

      if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] > 0) {
        maxGold = Math.max(maxGold, getMaximumGold_(grid, r, c, n, m, dir));
      }
    }

    grid[sr][sc] = -grid[sr][sc];
    return maxGold + grid[sr][sc];
  }

  //Leetcode 980.
  public int uniquePathsIII(int[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) return 0;
    int n = grid.length;
    int m = grid[0].length;
    int[][] dir = { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };

    int countZeros = 0, sr = 0, sc = 0, er = 0, ec = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] != -1) countZeros++;

        if (grid[i][j] == 1) {
          sr = i;
          sc = j;
        } else if (grid[i][j] == 2) {
          er = i;
          ec = j;
        }
      }
    }

    return uniquePathsIII_(grid, sr, sc, er, ec, countZeros - 1, dir);
  }

  public int uniquePathsIII_(
    int[][] grid,
    int sr,
    int sc,
    int er,
    int ec,
    int countZeros,
    int[][] dir
  ) {
    if (sr == er && sc == ec) {
      return countZeros == 0 ? 1 : 0;
    }

    int temp = grid[sr][sc];
    grid[sr][sc] = -2;

    int count = 0;
    int n = grid.length;
    int m = grid[0].length;
    for (int d = 0; d < 4; d++) {
      int r = sr + dir[d][0];
      int c = sc + dir[d][1];

      if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] >= 0) {
        count += uniquePathsIII_(grid, r, c, er, ec, countZeros - 1, dir);
      }
    }

    grid[sr][sc] = temp;
    return count;
  }

  //Leetcode 46
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> smallAns = new ArrayList<>();

    int n = nums.length;
    boolean[] vis = new boolean[n];
    permutation_distinct(nums, 0, vis, res, smallAns);
    return res;
  }

  public void permutation_distinct(
    int[] nums,
    int count,
    boolean[] vis,
    List<List<Integer>> res,
    List<Integer> smallAns
  ) {
    if (count == nums.length) {
      List<Integer> ans = new ArrayList<>(smallAns);
      res.add(ans);
      return;
    }

    for (int i = 0; i < nums.length; i++) {
      if (!vis[i]) {
        vis[i] = true;
        smallAns.add(nums[i]);

        permutation_distinct(nums, count + 1, vis, res, smallAns);

        vis[i] = false;
        smallAns.remove(smallAns.size() - 1);
      }
    }
  }

  //Leetcode 47
  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> smallAns = new ArrayList<>();

    int n = nums.length;
    boolean[] vis = new boolean[n];

    Arrays.sort(nums);
    permuteUnique_(nums, 0, vis, res, smallAns);
    return res;
  }

  public void permuteUnique_(
    int[] nums,
    int count,
    boolean[] vis,
    List<List<Integer>> res,
    List<Integer> smallAns
  ) {
    if (count == nums.length) {
      List<Integer> ans = new ArrayList<>(smallAns);
      res.add(ans);
      return;
    }

    HashSet<Integer> duplicate = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
      if (!vis[i] && !duplicate.contains(nums[i])) {
        vis[i] = true;
        smallAns.add(nums[i]);
        duplicate.add(nums[i]);

        permuteUnique_(nums, count + 1, vis, res, smallAns);

        vis[i] = false;
        smallAns.remove(smallAns.size() - 1);
      }
    }
  }

  public void permuteUnique_02_(
    int[] nums,
    int count,
    boolean[] vis,
    List<List<Integer>> res,
    List<Integer> smallAns
  ) {
    if (count == nums.length) {
      List<Integer> ans = new ArrayList<>(smallAns);
      res.add(ans);
      return;
    }

    int prev = (int) -1e8;
    for (int i = 0; i < nums.length; i++) {
      if (!vis[i] && prev != nums[i]) {
        vis[i] = true;
        smallAns.add(nums[i]);

        permuteUnique_02_(nums, count + 1, vis, res, smallAns);

        vis[i] = false;
        smallAns.remove(smallAns.size() - 1);
        prev = nums[i];
      }
    }
  }

  //Leetcode 17
  public List<String> letterCombinations(String digits) {
    if (digits.length() == 0) return new ArrayList<>();
    String[] codes = {
      "",
      "",
      "abc",
      "def",
      "ghi",
      "jkl",
      "mno",
      "pqrs",
      "tuv",
      "wxyz",
    };

    List<String> ans = new ArrayList<>();
    letterCombinations(digits, 0, codes, ans, "");
    return ans;
  }

  public static int letterCombinations(
    String digits,
    int idx,
    String[] codes,
    List<String> ans,
    String res
  ) {
    if (idx == digits.length()) {
      ans.add(res);
      return 1;
    }

    int cidx = (digits.charAt(idx) - '0');
    String code = codes[cidx];

    int count = 0;
    for (int i = 0; i < code.length(); i++) {
      count +=
        letterCombinations(digits, idx + 1, codes, ans, res + code.charAt(i));
    }

    return count;
  }

  public int numDecodings(String s) {
    if (s.length() == 0) return 0;
    return numDecodings(s, 0, "");
  }

  //Leetcode 91
  public int numDecodings(String s, int idx, String ans) {
    if (idx == s.length()) {
      System.out.println(ans);
      return 1;
    }
    char ch = s.charAt(idx);
    int cidx = ch - '0';
    if (cidx == 0) return 0;

    int count = 0;
    count += numDecodings(s, idx + 1, ans + (char) (cidx + 'a' - 1));

    if (idx < s.length() - 1) {
      cidx = cidx * 10 + (s.charAt(idx + 1) - '0');
      if (cidx >= 10 && cidx <= 26) {
        count += numDecodings(s, idx + 1, ans + (char) (cidx + 'a' - 1));
      }
    }

    return count;
  }

  //Leetcode 37
  public void solveSudoku(char[][] board) {
    ArrayList<Integer> locOfZeros=new ArrayList<>();
for(int i=0;i<9;i++){
for(int j=0;j<9;j++){
    if(board[i][j] == '.'){
        locOfZeros.add( i*9 + j );
    }
}
}

sudokuSolver_02(board,0,locOfZeros);
}

public boolean isValidToPlaceNumber(char[][] board,int r,int c,int num){

//row
for(int i=0;i<9;i++) if(board[r][i] -'0' == num) return false;

//col
for(int i=0;i<9;i++) if(board[i][c] - '0' == num) return false;

//matri 3X3
r = (r/3)*3;
c = (c/3)*3;

for(int i=0;i < 3; i++)
for(int j=0; j < 3; j++)
   if(board[r + i][c + j]-'0'==num) return false;

return true;
}


public boolean sudokuSolver_02(char[][] board,int vidx,ArrayList<Integer> locOfZeros){
if(vidx == locOfZeros.size()){
// display2D(board);
return true;
}

int twoDloc = locOfZeros.get(vidx);
int r = twoDloc / 9;
int c = twoDloc % 9;


for(int num = 1 ; num <= 9; num++){
if(isValidToPlaceNumber(board,r,c,num)){
    board[r][c] = (char)(num+'0');
    
    if(sudokuSolver_02(board,vidx + 1,locOfZeros)) return true;
    
    board[r][c] = '.';
}
}
return false;
}

 
    
// leetcode 37
    
    static int[] rows;
    static int[] cols;
    static int[][] mat;
    
    public void solveSudoku(char[][] board) {
                
        rows = new int[9];
        cols = new int[9];
        mat = new int[3][3];

        ArrayList<Integer> locOfZeros=new ArrayList<>();
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j] == '.'){
                    locOfZeros.add( i*9 + j );
                }else{
                    int val = board[i][j]-'0';
                    int mask= (1 << val);
                    rows[i] ^=mask;
                    cols[j] ^=mask;
                    mat[i/3][j/3] ^=mask;
                }
            }
        }
        
        sudokuSolver_BitMasking(board,0,locOfZeros);
        
    }

    public static boolean sudokuSolver_BitMasking(char[][] board,int vidx,ArrayList<Integer> locOfZeros){
        if(vidx == locOfZeros.size()){
            // display2D(board);
            return true;
        }

        int twoDloc = locOfZeros.get(vidx);
        int r = twoDloc / 9;
        int c = twoDloc % 9;
        
        for(int num = 1 ; num <= 9; num++){    
            int mask= (1 << num);
            if((rows[r] & mask) == 0 && (cols[c] & mask) == 0 && (mat[r/3][c/3] & mask) == 0){
                
                rows[r] ^=mask;
                cols[c] ^=mask;
                mat[r/3][c/3] ^=mask;
                board[r][c] = (char)(num + '0');

                if(sudokuSolver_BitMasking(board,vidx + 1,locOfZeros)) return true;

                rows[r] ^=mask;
                cols[c] ^=mask;
                mat[r/3][c/3] ^=mask;
                board[r][c] = '.';
            }
        }
        return false;
    }

    public boolean isValidSudoku(char[][] board) {
      int[] rows = new int[9];
      int[] cols = new int[9];
      int[][] mat = new int[3][3];

      for(int i=0;i<9;i++){
          for(int j=0;j<9;j++){
              if(board[i][j] != '.'){
                  int val = board[i][j]-'0';
                  int mask= (1 << val);
                  if((rows[i] & mask) == 0 && (cols[j] & mask) == 0 && (mat[i/3][j/3] & mask) == 0){
                      rows[i] ^=mask;
                      cols[j] ^=mask;
                      mat[i/3][j/3] ^=mask;
                  }else return false;
                }
          }
      }

      return true;

    }



}
