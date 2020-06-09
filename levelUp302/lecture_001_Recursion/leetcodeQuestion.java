public class leetcodeQuestion{
 
    // leetcode 36==========================================
    int[] row;
    int[] col;
    int[][] mat;

    public boolean isValidSudoku(char[][] board) {
        row=new int[9];
        col=new int[9];
        mat=new int[3][3];

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]!='.'){
                    int mask=(1<<(board[i][j]-'0'));
                    if ((row[i]&mask)==0 && (col[j]&mask)==0 && (mat[i/3][j/3]&mask)==0){
                 
                    row[i]^=mask;
                    col[j]^=mask;
                    mat[i/3][j/3]^=mask; 
                }else 
                       return false;
            }
        }   
    }
        return true;
}

// leetcode 37.======================================================

    public void solveSudoku(char[][] board) {
        ArrayList<Integer> calls=new ArrayList<>();
        row=new int[9];
        col=new int[9];
        mat=new int[3][3];

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]=='.'){
                    calls.add((i*9 + j));
                }else{
                    int mask=(1<<(board[i][j]-'0'));
                    row[i]^=mask;
                    col[j]^=mask;
                    mat[i/3][j/3]^=mask; 
                    
                }
            }
        }
        
        sudoku_03(board,calls,0);
    }
    

    public boolean sudoku_03(char[][] board,ArrayList<Integer> calls, int idx) {
		if (idx == calls.size()) {
			return true;
		}

		int r = calls.get(idx) / 9;
		int c = calls.get(idx) % 9;
      
        
		for (int num = 1; num <= 9 ; num++) {
            int mask=(1<<num);
            if ((row[r]&mask)==0 && (col[c]&mask)==0 && (mat[r/3][c/3]&mask)==0) {

                board[r][c] = (char)(num+'0');
                row[r]^=mask;
                col[c]^=mask;
                mat[r/3][c/3]^=mask;
            
                if(sudoku_03(board,calls ,idx + 1)) return true;
            
                board[r][c] = '.';
                row[r]^=mask;
                col[c]^=mask;
                mat[r/3][c/3]^=mask;
            
            }
		}
        return false;	
    }

    //leetocde 51,52


    //leetcode 77.=================================================

    public List<List<Integer>> combine(int n, int k) {
        return combination(n,k,1,new ArrayList<>());
     }
     
     private static List<List<Integer>> combination(int n,int k,int vidx,ArrayList<Integer> list){
         if(list.size()==k){
         List<List<Integer>> base=new ArrayList<>();
         List<Integer> ll=new ArrayList<>();
         ll.addAll(list);
             base.add(ll);
             return base;
             
             
         }
            
         List<List<Integer>> ans=new ArrayList<>();
         for(int i=vidx;i<=n;i++){
             
             list.add(i);
             ans.addAll(combination(n,k,i+1,list));
             list.remove(list.size()-1);
             
         }
         
         return ans;
         
     }


}