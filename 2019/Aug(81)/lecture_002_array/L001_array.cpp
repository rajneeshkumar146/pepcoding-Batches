#include <iostream>
#include <vector>

using namespace std;





void maxSubArray(vector<int>& arr){
    int s=0,si=0,ei=0;
    int imax=0,omax=-100000;

    for(int i=0;i<arr.size();i++){
        imax+=arr[i];

        if(omax<imax){
            ei=i;
            si=s;
            omax=imax;
        }

        if(imax < 0){
            s=i+1;
            imax=0;
        }

    }

}



void zerosAndOnes(vector<int>& arr){
   int pt=0;
   int itr=0;

   while(itr<arr.size()){
     if(arr[itr]==0){
         swap(arr[itr],arr[pt]);
         pt++;
     }
      itr++;
   }
}


void zerosAndOnesAndTwo(vector<int>& arr){
   int pt1=0;
   int pt2=arr.size()-1;
   int itr=0;

   while(itr<=pt2){
     if(arr[itr]==0){
         swap(arr[itr],arr[pt1]);
         pt1++;
         itr++;
     }else if(arr[itr]==2){
         swap(arr[itr],arr[pt2]);
         pt2--;
     }else{
         itr++;
     }
   }
}

void print(vector<int>& arr,int si,int ei){
    for(int i=si;i<=ei;i++){
     cout<<arr[i]<<" ";
    }

    cout<<endl;

}

void printSubstrings(vector<int>& arr){
    for(int i=0;i<arr.size();i++){
        for(int j=i;j<arr.size();j++){
              print(arr,i,j);
            //  cout<<arr[j]<<" ";
        }
        cout<<endl;
    }

}



bool isReallyCandidate(vector<int>& arr,int data){
      int count=0;
      for(int i:arr){
          if(i==data){
              count++;
          }
      }

      if(count>arr.size()/2){
          return true;
      }
      return false;
}


int potentialCandidate(vector<int>& arr){
     
     int freq=1;
     int suspect=arr[0];

     for(int i=1;i<arr.size();i++){
        if(suspect==arr[i])
           freq++;
        else
           freq--;
        
        
        if(freq==0){
            freq=1;
            suspect=arr[i];
        }
     }
       
     return suspect;
}

void votingAlgo(){
     vector<int> arr={3,3,4,2,4,4,2,4,4};
     int potiential=potentialCandidate(arr);
     
     bool ans=isReallyCandidate(arr,potiential);
     
     if(ans){
         cout<<"potiential: "<<potiential<<endl;
     }else{
         cout<<"potiential: "<<-1<<endl;
     }

}

// public static boolean find(int[] arr,int data)
bool find(vector<int>& arr,int data){
    for(int i: arr){
        if(i==data){
            return true;
        }
    }
    return false;
}
// public static int maximum(int[] arr)
int maximum(vector<int>& arr){
    int max_=arr[0];
    for(int i: arr){
        if(i>max_){
            max_=i;
        }
    }

    return max_;

}

// public static int minimum(int[] arr)
int minimum(vector<int>& arr){
    int min_=arr[0];
    for(int i: arr){
        if(i<min_){
            min_=i;
        }
    }
    return min_;
}
 
int main(int args, char **argv)
{

    votingAlgo();
    return 0;
}