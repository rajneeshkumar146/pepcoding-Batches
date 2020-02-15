#include <iostream>
#include <vector>

using namespace std;

void fun(vector<int>& arr)
{
    for (int i = 0; i < arr.size(); i++)
    {
        arr[i] = i + 1;
    }
}

int main(int args, char **argv)
{
    vector<int> arr(10, 0);

    for (int i = 0; i < arr.size(); i++)
    {
        cout << arr[i] << " ";
    }

    cout << endl;
    fun(arr);
    
    for (int i = 0; i < arr.size(); i++)
    {
        cout << arr[i] << " ";
    }

    return 0;
}