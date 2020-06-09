import java.util.Stack;
import java.util.Arrays;
public class questions{

   public static void main(String[] args){


   }



     //leetcode 20.
    public static boolean isBalance(String str){
        Stack<Character> st=new Stack<>();
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);

            if(ch=='(' || ch=='[' || ch=='{'){  //opening bracket.
                st.push(ch);
            }else{
                if(st.size()==0) return false;  // more clode brackets.
                else if(st.peek() == '(' && ch !=')') return false;
                else if(st.peek() == '[' && ch !=']') return false;
                else if(st.peek() == '{' && ch !='}') return false;
                else st.pop();
            }
        }

        return st.size()==0?true:false;  // more open bracket.

    }

    public static int[] nextGreaterOnRight(int[] arr){
        Stack<Integer> st=new Stack<>();
        int[] ans=new int[arr.length];
        Arrays.fill(ans,arr.length);

        for(int i=0;i<arr.length;i++){
            while(st.size()!=0  && arr[st.peek()]<arr[i]){
                 int idx=st.pop();
                 ans[idx]=i;
            }
            st.push(i);
        }

    //     while(st.size()!=0 ){
    //         int idx=st.pop();
    //         ans[idx]=arr.length;
    //    }
    }

    public static int[] nextGreaterOnLeft(int[] arr){
        Stack<Integer> st=new Stack<>();
        int[] ans=new int[arr.length];
        Arrays.fill(ans,-1);

        for(int i=arr.length-1;i>=0;i--){
            while(st.size()!=0  && arr[st.peek()]<arr[i]){
                 int idx=st.pop();
                 ans[idx]=i;
            }
            st.push(i);
        }
    }

    public static int[] nextSmallerOnRight(int[] arr){
        Stack<Integer> st=new Stack<>();
        int[] ans=new int[arr.length];
        Arrays.fill(ans,arr.length);

        for(int i=0;i<arr.length;i++){
            while(st.size()!=0  && arr[st.peek()]>arr[i]){
                 int idx=st.pop();
                 ans[idx]=i;
            }
            st.push(i);
        }

    }

    public static int[] nextSmallerOnLeft(int[] arr){
        Stack<Integer> st=new Stack<>();
        int[] ans=new int[arr.length];
        Arrays.fill(ans,-1);

        for(int i=arr.length-1;i>=0;i--){
            while(st.size()!=0  && arr[st.peek()]>arr[i]){
                 int idx=st.pop();
                 ans[idx]=i;
            }
            st.push(i);
        }
    }

    //leetcode 84
    public static int largestRectangleArea(int[] heights) {
        int[] ngol=nextSmallerOnLeft(heights);
        int[] ngor=nextSmallerOnRight(heights);

        int max_=0;
        for(int i=0;i<heights.length;i++){
            int w=ngor[i]-ngol[i]-1;
            int area=heights[i]*w;

            max_=Math.max(max_,area);
        }
        return max_;
    }

    static class MyQueue {

        Stack<Integer> st;  // push,peek,pop
        Stack<Integer> reverse;
        public MyQueue() {
            st=new Stack<>();
            reverse=new Stack<>();
        }
        
        
        public void push(int x) {
            reverseStack(st,reverse);
            st.push(x);
            reverseStack(reverse,st);
        }

        private void reverseStack(Stack<Integer> one,Stack<Integer> two){
            while(one.size()!=0){
                two.push(one.pop());
            }
        }
        
       
        public int pop() {
            return st.pop();
        }
        
      
        public int peek() {
            return st.peek();
        }
        
        
        public boolean empty() {
            return st.size()==0;
        }
    }


}