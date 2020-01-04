import java.util.PriorityQueue;
class l002_PQ{

    public static class pair implements Comparable<pair>{
        int rank=0;

         pair(int rank){
             this.rank=rank;
         }

        @Override
        public int compareTo(pair o){
        //   System.out.println(this.rank+ " " + o.rank);
          return -(this.rank-o.rank);
        }
    }


    public static void main(String[] args){
        PriorityQueue<pair> pq=new PriorityQueue();
        
        int[] arr={2,3,4,612,11,3,10,34,6,2,54,56,34,21,1};
        for(int i=0;i<arr.length;i++){
            pq.add(new pair(arr[i]));
        }


        while(!pq.isEmpty()){
        System.out.println(pq.remove().rank);
        }
    }

}