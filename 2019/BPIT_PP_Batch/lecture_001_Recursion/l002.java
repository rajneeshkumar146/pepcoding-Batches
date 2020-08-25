import java.util.HashSet;
import java.util.ArrayList;
public class l002{

    public static void main(String[] args){
        solve();
    }

    public static void solve(){
        int[] coins={2,3,5,7};
        int tar=10;

        // System.out.println(coinChangeInfnitePermuatation(coins,0,tar,""));
        // System.out.println(coinChangeInfniteCombination(coins,0,tar,""));
        // System.out.println(coinChangeSingleCombination(coins,0,tar,""));
        // System.out.println(coinChangeSinglePermuatation(coins,0,tar,""));
         
        // System.out.println(coinChangeInfnitePermuatation_subseq(coins,0,tar,""));
        // System.out.println(coinChangeInfniteCombination_subseq(coins,0,tar,""));
        // System.out.println(coinChangeSingleCombination_subseq(coins,0,tar,""));
        // System.out.println(coinChangeSinglePermuatation_subseq(coins,0,tar,""));
         
        // boolean[] box=new boolean[6];
        // int tnq=3;
        // System.out.println(oneDQueenCombination(box,0,tnq,0,""));
        // System.out.println(oneDQueenPermutation(box,0,tnq,0,""));

        // boolean[][] box=new boolean[4][4];
        // int tnq = 4;
        // System.out.println(twoDQueenCombination(box,0,tnq,""));
        // System.out.println(twoDQueenPermutation(box,0,tnq,""));
        // System.out.println(twoDNqueenFloorWise(box,0,tnq,""));


        // Nqueen();
        // wordBreak();
        // isCryptoValid();
        // sudoku();
        crossWord();
    }

    public static int coinChangeInfnitePermuatation(int[] arr,int idx,int tar,String ans){
        if(tar==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        for(int i=idx;i<arr.length;i++){
            if(tar-arr[i] >= 0)
              count += coinChangeInfnitePermuatation(arr,0,tar-arr[i],ans + arr[i]);
        }
        return count;
    }

    public static int coinChangeInfniteCombination(int[] arr,int idx,int tar,String ans){
        if(tar==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        for(int i=idx;i<arr.length;i++){
            if(tar-arr[i] >= 0)
              count += coinChangeInfniteCombination(arr,i,tar-arr[i],ans + arr[i]);
        }
        return count;
    }

    
    public static int coinChangeSingleCombination(int[] arr,int idx,int tar,String ans){
        if(tar==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        for(int i=idx;i<arr.length;i++){
            if(tar-arr[i] >= 0)
              count += coinChangeSingleCombination(arr,i+1,tar-arr[i],ans + arr[i]);
        }
        return count;
    }
    
    public static int coinChangeSinglePermuatation(int[] arr,int idx,int tar,String ans){
        if(tar==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;

        for(int i=idx;i<arr.length;i++){
            if(arr[i] > 0 && tar-arr[i] >= 0){
                int temp = arr[i];
                arr[i]=-arr[i];
                count += coinChangeSinglePermuatation(arr,0,tar-temp,ans + temp);
                arr[i]=-arr[i];
            }
        }
        return count;
    }

    public static int coinChangeSingleCombination_subseq(int[] arr,int idx,int tar,String ans){
        if(tar==0 || idx==arr.length){
            if(tar==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        
        int count=0;
        if(tar-arr[idx]>=0)
           count+=coinChangeSingleCombination_subseq(arr,idx+1,tar-arr[idx],ans+arr[idx]);

        count+=coinChangeSingleCombination_subseq(arr,idx+1,tar,ans);
        
        return count;
    }

    public static int coinChangeInfniteCombination_subseq(int[] arr,int idx,int tar,String ans){
        if(tar==0 || idx==arr.length){
            if(tar==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        
        int count=0;
        if(tar-arr[idx]>=0)
           count+=coinChangeInfniteCombination_subseq(arr,idx,tar-arr[idx],ans+arr[idx]);

        count+=coinChangeInfniteCombination_subseq(arr,idx+1,tar,ans);
        
        return count;
    }

    public static int coinChangeInfnitePermuatation_subseq(int[] arr,int idx,int tar,String ans){
        if(tar==0 || idx==arr.length){
            if(tar==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        
        int count=0;
        if(tar-arr[idx]>=0)
           count+=coinChangeInfnitePermuatation_subseq(arr,0,tar-arr[idx],ans+arr[idx]);

        count+=coinChangeInfnitePermuatation_subseq(arr,idx+1,tar,ans);
        
        return count;
    }

    public static int coinChangeSinglePermuatation_subseq(int[] arr,int idx,int tar,String ans){
        if(tar==0 || idx==arr.length){
            if(tar==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        
        int count=0;
        if(arr[idx] > 0 && tar-arr[idx] >= 0){
            int temp = arr[idx];
            arr[idx]=-arr[idx];
            count += coinChangeSinglePermuatation_subseq(arr,0,tar-temp,ans + temp);
            arr[idx]=-arr[idx];
        }

        count+=coinChangeSinglePermuatation_subseq(arr,idx+1,tar,ans);
        
        return count;
    }

    public static int oneDQueenCombination(boolean[] box,int idx,int tnq,int qpsf,String ans){
        if(qpsf==tnq){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        for(int i=idx;i<box.length;i++){
            count+=oneDQueenCombination(box,i+1,tnq,qpsf+1,ans + "b" + i + "q" + qpsf + " ");
        }

        return count;
    }

    public static int oneDQueenPermutation(boolean[] box,int idx,int tnq,int qpsf,String ans){
        if(qpsf==tnq){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        for(int i=idx;i<box.length;i++){
            if(!box[i]){
                box[i] = true;
                count+=oneDQueenPermutation(box,0,tnq,qpsf+1,ans + "b" + i + "q" + qpsf + " ");
                box[i] = false;
            }
        }

        return count;
    }

    public static int twoDQueenCombination(boolean[][] box,int idx,int tnq,String ans){
        if(tnq==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        int n=box.length;
        int m=box[0].length;

        for(int i=idx;i<n*m;i++){
            int r = i / m;
            int c = i % m; 
            count+=twoDQueenCombination(box,i+1,tnq-1,ans + "(" + r + ", " + c + ") ");
        }

        return count;
    }

    public static int twoDQueenPermutation(boolean[][] box,int idx,int tnq,String ans){
        if(tnq==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        int n=box.length;
        int m=box[0].length;
        for(int i=idx;i<n*m;i++){
            int r = i / m;
            int c = i % m; 
            if(!box[r][c]){
                box[r][c] = true;
                count+=twoDQueenPermutation(box,0,tnq-1,ans + "(" + r + ", " + c + ") ");
                box[r][c] = false;
            }
        }

        return count;
    }

    public static int twoDNqueenFloorWise(boolean[][] box,int row,int tnq,String ans){
        if(tnq==0 || row==box.length){
            if(tnq==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int m = box[0].length;
        int count = 0; 
        for(int i = 0; i < m; i++){
            count+=twoDNqueenFloorWise(box,row + 1, tnq - 1, ans + "(" + row + ", " + i + ") " );
        }
        
         return count;
    }

    //Nqueen.=============================================================================

    public static boolean isSafeToPlaceQueen(boolean[][] box,int r,int c){
        // int[][] dirP = {{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{-1,1},{1,-1}};
        int[][] dirC = {{-1,0},{0,-1},{-1,1},{-1,-1}};

        for(int[] d: dirC){
            for(int rad=1;rad<=Math.max(box.length,box[0].length);rad++){
                int x = r + rad * d[0];
                int y = c + rad * d[1];

                if(x >= 0 && x < box.length && y >=0 && y < box[0].length){
                   if(box[x][y])
                    return false;
                }else break;
            }
        }

        return true;
    }



    public static int NQueenCombination(boolean[][] box,int idx,int tnq,String ans){
        if(tnq==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        int n=box.length;
        int m=box[0].length;

        for(int i=idx;i<n*m;i++){
            int r = i / m;
            int c = i % m; 
            if(isSafeToPlaceQueen(box,r,c)){
                box[r][c] = true;
                count+=NQueenCombination(box,i+1,tnq-1,ans + "(" + r + ", " + c + ") ");
                box[r][c] = false;
            }
        }

        return count;
    }

    static boolean[] rowsQ;
    static boolean[] colsQ;
    static boolean[] diagQ;
    static boolean[] AdiagQ;

    public static int NQueenCombination_usingBooleanArray(int n,int idx,int tnq,String ans){
        if(tnq==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        int m=n;

        for(int i=idx;i<n*m;i++){
            int r = i / m;
            int c = i % m; 
            if(!rowsQ[r] && !colsQ[c] && !diagQ[r+c] && !AdiagQ[r-c+m]){
                rowsQ[r] = true;
                colsQ[c] = true;
                diagQ[r+c] = true;
                AdiagQ[r-c+m] = true;
                
                count+=NQueenCombination_usingBooleanArray(n,i+1,tnq-1,ans + "(" + r + ", " + c + ") ");
                
                rowsQ[r] = false;
                colsQ[c] = false;
                diagQ[r+c] = false;
                AdiagQ[r-c+m] = false;
            }
        }

        return count;
    }

    static int rowsBQ = 0;
    static int colsBQ = 0;
    static int diagBQ = 0;
    static int AdiagBQ = 0;

    public static int NQueenCombination_usingBits(int n,int idx,int tnq,String ans){
        if(tnq==0){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        int m=n;

        for(int i=idx;i<n*m;i++){
            int r = i / m;
            int c = i % m; 
            if((rowsBQ & (1 << r))==0 && (colsBQ & (1 << c))==0 && (diagBQ & (1 << (r+c)))==0 && (AdiagBQ & (1 << (r - c + m)))==0){
                rowsBQ ^=(1 << r);
                colsBQ ^=(1 << c);
                diagBQ ^=(1 << (r + c));
                AdiagBQ ^=(1 << (r - c + m));
                
                count+=NQueenCombination_usingBits(n,i+1,tnq-1,ans + "(" + r + ", " + c + ") ");
                
                rowsBQ ^=(1 << r);
                colsBQ ^=(1 << c);
                diagBQ ^=(1 << (r + c));
                AdiagBQ ^=(1 << (r - c + m));
            }
        }

        return count;
    }

    public static int NQueenCombination_best(boolean[][] box,int row,int tnq,String ans){
        if(tnq==0 || row==box.length){
            if(tnq==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int m = box[0].length;
        int count = 0; 
        for(int i = 0; i < m; i++){
            if(isSafeToPlaceQueen(box,row,i)){
                box[row][i] = true;
                count+=NQueenCombination_best(box,row + 1, tnq - 1, ans + "(" + row + ", " + i + ") " );
                box[row][i] = false;
            }
        }
        
         return count;
    }

    public static int NQueenCombination_bestUsingBits(boolean[][] box,int row,int tnq,String ans){
        if(tnq==0 || row==box.length){
            if(tnq==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int m = box[0].length;
        int count = 0; 
        for(int i = 0; i < m; i++){
            int r = row;
            int c = i; 
            if((colsBQ & (1 << c))==0 && (diagBQ & (1 << (r+c)))==0 && (AdiagBQ & (1 << (r - c + m)))==0){
                colsBQ ^=(1 << c);
                diagBQ ^=(1 << (r + c));
                AdiagBQ ^=(1 << (r - c + m));
                
                count+=NQueenCombination_bestUsingBits(box,row + 1,tnq-1,ans + "(" + r + ", " + c + ") ");
                
                colsBQ ^=(1 << c);
                diagBQ ^=(1 << (r + c));
                AdiagBQ ^=(1 << (r - c + m));
            }
        }
         return count;
    }

    public static void Nqueen(){
        int n=4,m=4,tnq=4;
        boolean[][] box=new boolean[n][m];
       
        rowsQ  = new boolean[n];
        colsQ  = new boolean[m];
        diagQ  = new boolean[n + m - 1];
        AdiagQ = new boolean[n + m - 1];
                
        // System.out.println(NQueenCombination(box,0,tnq,""));
        System.out.println(NQueenCombination_best(box,0,tnq,""));   
        
        
        // System.out.println(NQueenCombination_bestUsingBits(box,0,tnq,""));   
        System.out.println(NQueenCombination_usingBits(n,0,tnq,""));   
        
           
    }

    //WordBreak.=====================================================================

    public static int wordBreak(String ques,int idx,String ans,int maxLenWord,HashSet<String> words){
        if(idx == ques.length()){
            System.out.println(ans);
            return 1;
        }

        int count=0;
        for(int i = idx + 1 ; i <=  (idx + maxLenWord + 1) && i <= ques.length();i++){
            String str = ques.substring(idx,i);
            if(words.contains(str))
               count+=wordBreak(ques,i,ans + str + " ",maxLenWord,words);
        }

        return count;   
    }

    public static void wordBreak(){
        String ques="ilikesamsungandmango";
        String[] word={"mobile","samsung","sam","sung", 
        "man","mango","icecream","and", 
         "go","i","like","ice","cream","ilike"};
        
        HashSet<String> words=new HashSet();
        int maxLenWord=0;
        for(String s: word){
            maxLenWord = Math.max(maxLenWord, s.length());
            words.add(s);
        }

        System.out.println(wordBreak(ques,0,"",maxLenWord,words));
    }

    //Crypto.=========================================================================
    static String a="send";
    static String b="more";
    static String c="money";
    static int[] charToNumber=new int[26];
    static boolean[] numberUsed=new boolean[10];

    public static int stringToNumber(String str){
        int num=0;
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            int val = charToNumber[ch-'a'];

            num = num * 10 + val;
        }

        return num;
    }

    public static int cryptoSolver(String str,int idx){
        if(idx == str.length()){
            int x = stringToNumber(a);
            int y = stringToNumber(b);
            int z = stringToNumber(c);

            if(x + y == z && charToNumber[a.charAt(0)-'a']!=0 && charToNumber[b.charAt(0)-'a']!=0 && charToNumber[c.charAt(0)-'a']!=0){
               System.out.println(x + "\n+" + y + "\n" + "----\n" + z + '\n');
                return 1;
            }

            return 0;
        }


        char ch=str.charAt(idx);
        int count = 0;
        for(int num = 0; num <= 9;num++){
            if(!numberUsed[num]){
                
                numberUsed[num] = true;
                charToNumber[ch-'a'] = num;
            
                count+=cryptoSolver(str,idx+1);
            
                charToNumber[ch-'a'] = 0;
                numberUsed[num] = false;
            }
        }

        return  count;
    }



    public static void isCryptoValid(){
        String str=a+b+c;

        int[] freq=new int[26];
        for(int i=0;i<str.length();i++) freq[str.charAt(i)-'a']++;

        str="";
        for(int i=0;i<26;i++) if(freq[i] > 0) str+=(char)(i + 'a');
        // System.out.println(str);

        System.out.println(cryptoSolver(str,0));
    }

    //Leetcode 37 : Sudoku.============================================================================
    public static void display(int[] board){
        for(int ele: board) System.out.print(ele);
        System.out.println();
    }
    
    public static void display2D(int[][] board){
        for(int[] a: board) display(a);
        System.out.println();
    }

    public static boolean isValidToPlaceNumber(int[][] board,int r,int c,int num){

        //row
        for(int i=0;i<9;i++) if(board[r][i] == num) return false;
        
        //col
        for(int i=0;i<9;i++) if(board[i][c] == num) return false;

        //matri 3X3
        r = (r/3)*3;
        c = (c/3)*3;

        for(int i=0;i < 3; i++)
           for(int j=0; j < 3; j++)
               if(board[r + i][c + j]==num) return false;

        return true;
    }

    public static int sudokuSolver(int[][] board,int vidx){
        if(vidx == 81){
            display2D(board);
            return 1;
        }
        int r = vidx / 9;
        int c = vidx % 9;

        if(board[r][c]!=0) return sudokuSolver(board,vidx+1);
        
        int count=0;
        for(int num = 1 ; num <= 9; num++){
            if(isValidToPlaceNumber(board,r,c,num)){
                board[r][c] = num;
                count += sudokuSolver(board,vidx + 1);
                board[r][c] = 0;
            }
        }

        return count;
    }

    public static int sudokuSolver_02(int[][] board,int vidx,ArrayList<Integer> locOfZeros){
        if(vidx == locOfZeros.size()){
            display2D(board);
            return 1;
        }

        int twoDloc = locOfZeros.get(vidx);
        int r = twoDloc / 9;
        int c = twoDloc % 9;

        int count=0;
        for(int num = 1 ; num <= 9; num++){
            if(isValidToPlaceNumber(board,r,c,num)){
                board[r][c] = num;
                count += sudokuSolver_02(board,vidx + 1,locOfZeros);
                board[r][c] = 0;
            }
        }

        return count;
    }

    static int[] rows;
    static int[] cols;
    static int[][] mat;

    public static int sudokuSolver_BitMasking(int[][] board,int vidx,ArrayList<Integer> locOfZeros){
        if(vidx == locOfZeros.size()){
            display2D(board);
            return 1;
        }

        int twoDloc = locOfZeros.get(vidx);
        int r = twoDloc / 9;
        int c = twoDloc % 9;
        
        int count=0;
        
        for(int num = 1 ; num <= 9; num++){    
            int mask= (1 << num);
            if((rows[r] & mask) == 0 && (cols[c] & mask) == 0 && (mat[r/3][c/3] & mask) == 0){
                
                rows[r] ^=mask;
                cols[c] ^=mask;
                mat[r/3][c/3] ^=mask;
                board[r][c] = num;

                count += sudokuSolver_02(board,vidx + 1,locOfZeros);

                rows[r] ^=mask;
                cols[c] ^=mask;
                mat[r/3][c/3] ^=mask;
                board[r][c] = 0;
            }
        }
        return count;
    }


    public static void sudoku(){
        int[][] board = {{3, 0, 0, 6, 0, 0, 0, 9, 2},  
                         {5, 2, 0, 0, 0, 0, 4, 0, 8},  
                         {0, 8, 7, 0, 0, 0, 0, 3, 1},  
                         {0, 0, 3, 0, 1, 0, 0, 8, 0},  
                         {9, 0, 0, 8, 6, 3, 0, 0, 5},  
                         {0, 5, 0, 0, 9, 0, 6, 0, 0},  
                         {1, 3, 0, 0, 0, 0, 2, 5, 0},  
                         {0, 0, 0, 0, 0, 0, 0, 7, 4},  
                         {0, 0, 5, 2, 0, 6, 3, 0, 0}};  
        
        rows = new int[9];
        cols = new int[9];
        mat = new int[3][3];

        ArrayList<Integer> locOfZeros=new ArrayList<>();
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j] == 0){
                    locOfZeros.add( i*9 + j );
                }else{
                    int val = board[i][j];
                    int mask= (1 << val);
                    rows[i] ^=mask;
                    cols[j] ^=mask;
                    mat[i/3][j/3] ^=mask;
                }
            }
        }

        System.out.println(sudokuSolver_02(board,0,locOfZeros));
    }

    // crossWord.===============================================================================
    public static void dislayCrossWord(char[][] board){
        for(char[] bo: board){
            for(char b: bo){
                System.out.print(b + " ");
            }
            System.out.println();
        }
    }

    public static boolean canPlaceH(char[][] board,int r,int c,String word){
        
        
        
        for(int i=0;i<word.length();i++){
            if(c + i >= board[0].length || (board[r][c+i]!='-' && board[r][c+i] != word.charAt(i))) return false;
        }

        return true;
    }

    public static boolean[] PlaceH(char[][] board,int r,int c,String word){
        boolean[] loc=new boolean[word.length()];
        for(int i=0;i<word.length();i++){
            if(board[r][c+i]=='-'){
                loc[i]=true;
                board[r][c+i]=word.charAt(i);
            }
        }

        return loc;
    }

    public static void unPlaceH(char[][] board,int r,int c,String word,boolean[] loc){
        for(int i=0;i<loc.length;i++){
            if(loc[i]){
                board[r][c+i]='-';
            }
        }
    }

    public static boolean canPlaceV(char[][] board,int r,int c,String word){
        for(int i=0;i<word.length();i++){
            if(r + i >= board.length || (board[r+i][c]!='-' && board[r+i][c] != word.charAt(i))) return false;
        }

        return true;
    }

    public static boolean[] PlaceV(char[][] board,int r,int c,String word){
        boolean[] loc=new boolean[word.length()];
        for(int i=0;i<word.length();i++){
            if(board[r+i][c]=='-'){
                loc[i]=true;
                board[r+i][c]=word.charAt(i);
            }
        }

        return loc;
    }

    public static void unPlaceV(char[][] board,int r,int c,String word,boolean[] loc){
        for(int i=0;i<loc.length;i++){
            if(loc[i]){
                board[r+i][c]='-';
            }
        }
    }


    public static int crossWord_(char[][] board,String[] words,int vidx){
        if(vidx == words.length){
            dislayCrossWord(board);
            return 1;
        }


        String word = words[vidx];
        int count=0;

        for(int i=0;i<board.length; i++){
            for(int j=0;j<board[0].length; j++){
                if(board[i][j]=='-' || board[i][j]==word.charAt(0)){
                    if(canPlaceH(board,i,j,word)){
                        boolean[] loc = PlaceH(board,i,j,word);
                        count+=crossWord_(board,words,vidx + 1);
                        unPlaceH(board,i,j,word,loc);
                    }

                    if(canPlaceV(board,i,j,word)){
                        boolean[] loc = PlaceV(board,i,j,word);
                        count+=crossWord_(board,words,vidx + 1);
                        unPlaceV(board,i,j,word,loc);
                    }
                }
            }
        }
        return count;
    }

    public static void crossWord(){
    char[][] board = {
        {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
        {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
        {'+', '-', '-', '-', '-', '-', '-', '-', '+', '+'},
        {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
        {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
        {'+', '-', '-', '-', '-', '-', '-', '+', '+', '+'},
        {'+', '-', '+', '+', '+', '-', '+', '+', '+', '+'},
        {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
        {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
        {'+', '+', '+', '+', '+', '+', '+', '+', '+', '+'}};

    String[] words = {"agra", "norway", "england", "gwalior"};
    System.out.println(crossWord_(board, words, 0));
}


}