import java.util.PriorityQueue;
public class l001{
    public static void main(String[] args){

    }

    public class pair{
        int ele,i,j;
        pair(int ele,int i,int j){
            this.ele=ele;
            this.i=i;
            this.j=j;
        }
    }

    public int kthSmallest(int[][] matrix, int k) {
       
     //pair<int,pair<int,int>> pair;
     //priority_queue<pair<int,pair<int,int>>> pq;
     // pq.add({-matrix[i][0],{i,0}});

    //  PriorityQueue<int[]> pq=new PriorityQueue<>((int[] a,int[] b)->{  //a[0]==ele,a[1]==i,a[2]==j; 
    //     return a[0]-b[0];//min PQ;
    //     // return b.ele-a.ele;//min PQ;
    // });

        PriorityQueue<pair> pq=new PriorityQueue<>((pair a,pair b)->{
            return a.ele-b.ele;//min PQ;
            // return b.ele-a.ele;//max PQ;
        });

        int n=matrix.length;
        int m=matrix[0].length;

        for(int i=0;i<n;i++){
            pq.add(new pair(matrix[i][0],i,0));
        }

        while(--k>0){
            pair p=pq.poll();
            int x=p.i;
            int y=p.j;
            if(++y<m) pq.add(new pair(matrix[x][y],x,y));
        }
        
        return pq.poll().ele;
        
    }
}