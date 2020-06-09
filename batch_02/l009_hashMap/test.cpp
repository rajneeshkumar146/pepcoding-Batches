#include <iostream>
#include <unordered_map>
#include <vector>
using namespace std;

void longestSeries(vector<int> &arr)
{
    unordered_map<int, bool> map;
    for (int ele : arr)
        map[ele] = true;//map.put(ele,true);

    int size = -1;
    int number = -1;

    for (auto keys : map)
    { // for(int keys:map.keySet())
        if (map.find(keys.first - 1) != map.end())  //if(map.containsKey(keys-1)) map.put(keys,false);
            map[keys.first] = false;
    }

    for (auto keys : map)
    {                    // for(int keys:map.keySet())
        if (keys.second) //if(map.get(keys))
        {
            int num = keys.first + 1;
            int smallSize = 1;
            while (map.find(num) != map.end())  
            {
                num++;
                smallSize++;
            }

            if (smallSize > size)
            {
                size = smallSize;
                number = keys.first;
            }
        }
    }

    for(int i=0;i<size;i++){
        cout<<number+i<<" ";
    }
    cout<<endl;
}

int main()
{
    vector<int> arr{0,1,2,6,7,100,99,98,97,96,95,94,12,3,4,5,10,8};
    longestSeries(arr);

    return 0;
}