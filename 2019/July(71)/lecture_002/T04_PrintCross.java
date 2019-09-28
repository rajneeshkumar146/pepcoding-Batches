public class T04_PrintCross{
    public static void main(String[] args){
        int n=9;

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if((i==j) || ((i+j)==(n+1))) 
                   System.out.print("*");
                else 
                   System.out.print(" ");
            }

            System.out.println();
        }

    }
}