public class L002{

    public static int coinChangePermutation_Infi(int[] coins,int tar,String ans){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int ele: coins){
            if(tar - ele >= 0)
               count += coinChangePermutation_Infi(coins,tar-ele,ans + ele + " ");
        }

        return count;
    }

    public static int coinChangeCombination_Infi(int[] coins,int tar,int idx,String ans){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = idx; i < coins.length; i++){
            if(tar - coins[i] >= 0)
               count += coinChangeCombination_Infi(coins,tar - coins[i],i,ans + coins[i] + " ");
        }

        return count;
    }

    public static int coinChangeCombination(int[] coins,int tar,int idx,String ans){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = idx; i < coins.length; i++){
            if(tar - coins[i] >= 0)
               count += coinChangeCombination(coins,tar - coins[i],i + 1,ans + coins[i] + " ");
        }

        return count;

    }

    
    public static int coinChangePermutation(int[] coins,int tar,String ans){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = 0; i < coins.length; i++){
            int val = coins[i];
            if(tar - val >= 0 && val > 0){
                coins[i] = -coins[i];
                count += coinChangePermutation(coins,tar - val,ans + val + " ");
                coins[i] = -coins[i];
            }
        }

        return count;

    }

    public static int coinChangeCombination2(int[] coins,int tar,int idx,String ans){
        if(tar == 0 || idx == coins.length){
            if(tar == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if(tar - coins[idx] >= 0)
          count += coinChangeCombination2(coins,tar - coins[idx], idx + 1, ans + coins[idx] + " ");
        count += coinChangeCombination2(coins,tar, idx + 1, ans);
    
        return count;
    }

    public static int coinChangeCombination_Infi2(int[] coins,int tar,int idx,String ans){
        if(tar == 0 || idx == coins.length){
            if(tar == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if(tar - coins[idx] >= 0)
          count += coinChangeCombination_Infi2(coins,tar - coins[idx], idx, ans + coins[idx] + " ");
        count += coinChangeCombination_Infi2(coins,tar, idx + 1, ans);
    
        return count;
    }

    public static int coinChangePermutation_Infi2(int[] coins,int tar,int idx,String ans){
        if(tar == 0 || idx == coins.length){
            if(tar == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if(tar - coins[idx] >= 0)
          count += coinChangePermutation_Infi2(coins,tar - coins[idx], 0, ans + coins[idx] + " ");
        count += coinChangePermutation_Infi2(coins,tar, idx + 1, ans);
    
        return count;
    }

    public static int coinChangePermutation2(int[] coins,int tar,int idx,String ans){
        if(tar == 0 || idx == coins.length){
            if(tar == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        int val = coins[idx];
        if(tar - coins[idx] >= 0 && coins[idx] > 0){
            coins[idx] = -val;
            count += coinChangePermutation2(coins,tar - val, 0, ans + val + " ");
            coins[idx] = val;
        }
        count += coinChangePermutation2(coins,tar, idx + 1, ans);
    
        return count;
    }

    //===============================================================================

    // lqpl : last queen placed location.
    public static int queenCombination(int box,int tnq,int lqpl,int qpsf,String ans){
        if(qpsf == tnq){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int i = lqpl;i < box;i++){
            count += queenCombination(box,tnq,i+1, qpsf + 1,ans + "b"+i + 'q'+qpsf); 
        }

        return count;
    }

    public static int queenPermutation(boolean[] box,int tnq,int lqpl,int qpsf,String ans){
        if(qpsf == tnq){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int i = 0;i < box.length;i++){
            if(!box[i]){
                box[i] = true;
                count += queenPermutation(box,tnq,i+1, qpsf + 1,ans + "b"+i + 'q'+qpsf); 
                box[i] = false;
            }
        
        }

        return count;
    }

    public static int queenCombination2D(boolean[][] box,int tnq,int lqpl,int qpsf,String ans){
        if(qpsf == tnq){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int i = lqpl;i < box.length * box[0].length;i++){
            int r = i / box[0].length;
            int c = i % box[0].length;
            count += queenCombination2D(box,tnq,i+1, qpsf + 1,ans + "("+ r + ','+c +") "); 
        }

        return count;
    }

    public static int queenPermutation2D(boolean[][] box,int tnq,int lqpl,int qpsf,String ans){
        if(qpsf == tnq){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int i = 0;i < box.length * box[0].length;i++){
            int r = i / box[0].length;
            int c = i % box[0].length;
            if( !box[r][c] ){
                box[r][c] = true;
                count += queenPermutation2D(box,tnq,i+1, qpsf + 1,ans + "("+ r + ','+c +") "); 
                box[r][c] = false;
            }
        }

        return count;
    }

    static boolean[] rows;
    static boolean[] cols;
    static boolean[] diag;
    static boolean[] aDiag;
    
    public static int nQueen(boolean[][] box,int tnq,int lqpl,int qpsf,String ans){
        if(qpsf == tnq){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        int n = box.length
        int m = box[0].length;

        for(int i = lqpl;i < n * m;i++){
            int r = i / m;
            int c = i % m

            if( !rows[r] && !cols[c] && !diag[r+c] && !adiag[r - c + m - 1]){
                box[r][c] = true;
                rows[r] = true;
                cols[c] = true;
                diag[r + c] = true;
                aDiag[r - c + m - 1] = true;
                
                count += nQueen(box,tnq,i+1, qpsf + 1,ans + "("+ r + ','+c +") "); 

                box[r][c] = false;
                rows[r] = false;
                cols[c] = false;
                diag[r + c] = false;
                aDiag[r - c + m - 1] = false;
                
            }
        }

        return count;
    }

    
    public static void set1(){
        int[] coins = {2,3,5,7};
        int tar = 10;

        // System.out.println(coinChangePermutation_Infi(coins,tar,""));
        // System.out.println(coinChangeCombination_Infi(coins,tar,0,""));
        // System.out.println(coinChangeCombination(coins,tar,0,""));
        // System.out.println(coinChangePermutation(coins,tar,""));

        System.out.println(coinChangeCombination2(coins,tar,0,""));
    }

    public static void queen(){
        // boolean[] box = new boolean[5];
        // System.out.println(queenCombination(5,3,0,0,""));
        // System.out.println(queenPermutation(box,3,0,0,""));
           
           boolean[][] box = new boolean[4][4];
           int tnq = 4;
           rows = new boolean[4];
           cols = new boolean[4];
           diag = new boolean[4 + 4 -1];
           aDiag = new boolean[4 + 4 -1];
           
           System.out.println(queenCombination2D(box,tnq,0,0,""));

    
    
    }


    public static void main(String[] args){
        queen();
    }






}