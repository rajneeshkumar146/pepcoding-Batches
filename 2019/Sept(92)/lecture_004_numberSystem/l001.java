public class l001{

  public static void main(String[] args){
       System.out.println(OS(56,235));
  } 

  public static int OS(int num1,int num2){
      boolean isNeg=false;

      if(num1<num2){
          isNeg=true;
          int temp=num1;
          num1=num2;
          num2=temp;
      }

      int pow=1;
      int res=0;
      int borr=0;

      while(num1!=0 || num2!=0){
         int rem = num1 % 10 - num2 % 10 + borr;
         num1/=10;
         num2/=10;

          if(rem<0){
              borr=-1;
              rem+=8;
          }else
             borr=0;
             
          res+=rem*pow;
          pow*=10;

      }

      if(isNeg) res*=-1;

      return res;
  }

  public static void OS(int num1,int num2){
      int pow=1;
      int res=0;
      int carry=0;

      while(num1!=0 || num2!=0 || carry!=0){
       int rem = num1 % 10 + num2 % 10 + carry;
       num1/=10;
       num2/=10;
       
       carry=rem/8;
       rem%=8;

       res+=rem*pow;
       pow*=10;
      }

      return res;
  }






}