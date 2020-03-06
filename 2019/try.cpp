#include <iostream>
#include <queue>
#include <vector>
#include <string>
#include <algorithm>
#include <unordered_map>
#include <sstream>

using namespace std;

unordered_map<string, vector<int>> map;

int main()
{
    int n = 4;
    int arr1[] = {1, 1, 2, 2, 2};
    int arr2[] = {2, 2, 3, 3, 4};
    int arr3[] = {2, 3, 1, 3, 4};

    for (int i = 1; i <= n; i++)
    {
        string edge = to_string(arr1[i]) + "@" + to_string(arr2[i]);
        map[edge].push_back(arr2[i]);
    }

    string edge = "";
    int maxIntrest = 0;
    for (auto &p : map)
    {
        if (map[p.first].size() > maxIntrest)
        {
            edge = p.first;
            maxIntrest = map[p.first].size();
        }
    }

    int ans = 1;
    string s = "";
    for (int i = 0; i < edge.length(); i++)
    {
        if (edge[i] != '@')
            s += edge[i];
        else
        {
            stringstream val_(s);
            int val = 0;
            val_ >> val;
            ans *= val;

            s = "";
        }
    }

    stringstream val_(s);
    int val = 0;
    val_ >> val;
    ans *= val;

    cout << ans << endl;

    return 0;
}