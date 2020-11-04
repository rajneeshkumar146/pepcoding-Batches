public class l005_StockBuyAndSell{
     
    //121
    public int maxProfit(int[] arr) {
        if(arr.length==0) return 0;
        
        int Ti0 = 0;
        int Ti1 = -(int)1e9;

        for(int ele: arr){
            Ti0 = Math.max(Ti0,Ti1 + ele);
            Ti1 = Math.max(Ti1,0 - ele);
        }

        return Ti0;
    }

    //123
    public int maxProfit(int[] arr) {
        if(arr.length==0) return 0;

        int Ti20 = 0;
        int Ti21 = -(int)1e9;
        
        int Ti10 = 0;
        int Ti11 = -(int)1e9;

        for(int ele : arr){
            Ti20 = Math.max(Ti20, Ti21 + ele);
            Ti21 = Math.max(Ti21, Ti10 - ele);

            Ti10 = Math.max(Ti10, Ti11 + ele);
            Ti11 = Math.max(Ti11, 0 - ele);
        }

        return Ti20;
    }

    //122
    public int maxProfit(int[] arr) {
        if(arr.length==0) return 0;
        
        int Ti0 = 0;
        int Ti1 = -(int)1e9;

        for(int ele: arr){
            Ti0 = Math.max(Ti0,Ti1 + ele);
            Ti1 = Math.max(Ti1,Ti0 - ele);
        }

        return Ti0;
    }

    //714
    public int maxProfit(int[] prices, int fee) {
        int T0 = 0;
        int T1 = -(int)1e9;

        
        for(int ele: arr){
            int prevSell = Ti0;
            Ti0 = Math.max(Ti0,Ti1 + ele);
            Ti1 = Math.max(Ti1,prevSell - ele - fee);
        }

        return Ti0;
    }

    //309
    public int maxProfit(int[] arr) {
        if(arr.length==0) return 0;
    
        int Ti0 = 0;
        int Ti1 = -(int)1e9;
        int Ti2 = 0;
    
        for(int val : arr){
            int temp = Ti0;
            Ti0 = Math.max(Ti0,Ti1 + val);
            Ti1 = Math.max(Ti1,Ti2 - val);
            Ti2 = temp;
        }
    
        return T0;        
    }

    //188



}