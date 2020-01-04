import java.util.Scanner;

public class l002_function{

    public static Scanner scn=new Scanner(System.in);

    public static void main(String[] args){
        rotateNumber(scn.nextInt(),scn.nextInt());
    }

    public static int numeberOfDigit(int n){
        int digit=0;
        while(n!=0){
            n/=10;
            digit++;
        }

        return digit;
    }

    public static void rotateNumber(int n,int r){
        int digit=numeberOfDigit(n);
        r%=digit;
        if(r<0) r=r+digit;

        int mul=1;
        int div=1;
        for(int i=1;i<=digit;i++){
            if(i<=r)
             mul*=10;
            else
              div*=10;
        }

        int a=n/div;
        int b=n%div;

        int ans=b*mul+a;
        System.out.println(ans);

        // return ans;
    }



}