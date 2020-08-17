#include <iostream>
#include <unordered_map>
#include <unordered_set>
#include <vector>
#include <queue>

using namespace std;

void freqMap(string str)
{
    unordered_map<char, int> map;

    for (char ch : str)
    {
        map[ch]++;
    }

    cout << map['@'] << endl;

    for (pair<char, int> p : map)
    {
        cout << p.first << " " << p.second << endl;
    }
}

void freqMap_pos(string str)
{
    unordered_map<char, vector<int>> map;

    for (int i = 0; i < str.length(); i++)
    {
        map[str[i]].push_back(i);
    }

    for (auto key : map)
    {
        cout << key.first << " -> ";
        for (int ele : key.second)
            cout << ele << " ";
        cout << endl;
    }
}

//Leetcode 349
vector<int> intersection(vector<int> &nums1, vector<int> &nums2)
{
    unordered_set<int> map;
    for (int ele : nums1)
        map.insert(ele);

    vector<int> ans;
    for (int ele : nums2)
    {
        if (map.find(ele) != map.end())
        {
            ans.push_back(ele);
            map.erase(ele);
        }
    }

    return ans;
}

//Leetcode 451
string frequencySort(string s)
{
    unordered_map<char, int> map;
    for (char ch : s)
        map[ch]++;

    priority_queue<pair<int, char>> pq;

    for (auto key : map)
    {
        pq.push({key.second, key.first});
    }

    string ans = "";
    while (pq.size() != 0)
    {
        pair<int, char> key = pq.top();
        pq.pop();
        int freq = key.first;
        char ch = key.second;

        for (int i = 0; i < freq; i++)
            ans += ch;
    }

    return ans;
}

//Leetcode 380
class RandomizedSet
{
public:
    unordered_map<int, int> map;
    vector<int> arr;

    RandomizedSet()
    {
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    bool insert(int val)
    {
        if (map.find(val) != map.end())
            return false;

        map[val] = arr.size();
        arr.push_back(val);

        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    bool remove(int val)
    {
        if (map.find(val) == map.end())
            return false;

        int idx = map[val];
        swap(arr[idx], arr[arr.size() - 1]);

        map[arr[idx]] = idx;

        arr.pop_back();
        map.erase(val);

        return true;
    }

    /** Get a random element from the set. */
    int getRandom()
    {
        int idx = rand() % arr.size();
        return arr[idx];
    }
};

void solve()
{
    freqMap("ajhvcjhjhagsx1321465kasjdbd8445632");
}

int main()
{
    solve();
    return 0;
}
