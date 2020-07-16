#include <iostream>
#include <queue>
#include <unordered_map>

using namespace std;

//Leetcode 215.
int findKthLargest(vector<int> &nums, int k)
{
    priority_queue<int, vector<int>, greater<int>> pq; // for java, default is MIN_PQ
    for (int ele : nums)
    {
        pq.push(ele);
        if (pq.size() > k)
            pq.pop();
    }
    return pq.top();
}

int findKthSmallest(vector<int> &nums, int k)
{
    priority_queue<int> pq; // for java, use Collections.reverseOrder()
    for (int ele : nums)
    {
        pq.push(ele);
        if (pq.size() > k)
            pq.pop();
    }
    return pq.top();
}

class KthLargest
{
public:
    priority_queue<int, vector<int>, greater<int>> pq;
    int size = -1;
    KthLargest(int k, vector<int> &nums)
    {
        size = k;
        for (int ele : nums)
        {
            pq.push(ele);
            if (pq.size() > size)
                pq.pop();
        }
    }

    int add(int val)
    {
        pq.push(val);
        if (pq.size() > size)
            pq.pop();
        return pq.top();
    }
};

int kthSmallest(vector<vector<int>> &matrix, int k)
{
    int n = matrix.size();
    int m = matrix[0].size();

    // priority_queue<vector<int>,vector<vector<int>>,greater<vector<int>>> pq;
    priority_queue<vector<int>> pq;

    for (int i = 0; i < n; i++)
        pq.push({-matrix[i][0], i, 0});

    while (k-- > 1)
    {
        vector<int> rvtx = pq.top();
        pq.pop();
        if (++rvtx[2] < m)
            pq.push({-matrix[rvtx[1]][rvtx[2]], rvtx[1], rvtx[2]});
    }

    return -pq.top()[0];
}

//Leetcode 349.============================================================

vector<int> intersection(vector<int> &nums1, vector<int> &nums2)
{
    if (nums1.size() == 0 || nums2.size() == 0)
        return {};

    vector<int> ans;
    unordered_map<int, int> map;
    for (int ele : nums1)
        map[ele]++;

    for (int ele : nums2)
    {
        if (map.count(ele) != 0)
        {
            ans.push_back(ele);
            map.erase(ele);
        }
    }

    return ans;
}

//Leetcode 350.
vector<int> intersect(vector<int> &nums1, vector<int> &nums2)
{

    if (nums1.size() == 0 || nums2.size() == 0)
        return {};

    vector<int> ans;
    unordered_map<int, int> map;
    for (int ele : nums1)
        map[ele]++;

    for (int ele : nums2)
    {
        if (map.count(ele) != 0)
        // if(map.find(ele) !=map.end())
        {
            ans.push_back(ele);
            map[ele]--;
            if (map[ele] == 0)
                map.erase(ele);
        }
    }

    return ans;
}

//Leetcode 128
int longestConsecutive(vector<int> &nums)
{
    unordered_map<int, int> map;

    for (int ele : nums)
        map[ele]++;

    int len = 0;
    for (int ele : nums)
    {
        if (map.find(ele) == map.end())
            continue;

        map.erase(ele);
        int prevEle = ele - 1;
        int nextEle = ele + 1;

        while (map.find(prevEle) != map.end())
            map.erase(prevEle--);
        while (map.find(nextEle) != map.end())
            map.erase(nextEle++);

        len = max(len, nextEle - prevEle - 1);
    }

    return len;
}

//Leetcode 347.
vector<int> topKFrequent(vector<int> &nums, int k)
{
    unordered_map<int, int> map;
    for (int ele : nums)
        map[ele]++;

    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> que;
    for (pair<int, int> p : map)
    {
        que.push({p.second, p.first});
        if (que.size() > k)
            que.pop();
    }

    vector<int> ans;
    while (que.size() != 0)
    {
        ans.push_back(que.top().second);
        que.pop();
    }

    return ans;
}

vector<vector<string>> groupAnagrams(vector<string> &strs)
{
    unordered_map<string, vector<string>> map; // HashMap<String,ArrayList<String>> map=new ..;
    for (string &str : strs)
    {
        vector<int> freq(26, 0);
        for (int i = 0; i < str.length(); i++)
            freq[str[i] - 'a']++;

        string code = "";
        for (int i = 0; i < 26; i++)
            if (freq[i] > 0)
                code += string(1, (char)(i + 'a')) + "" + to_string(freq[i]);

        // for java: map.putIfAbsent(code,new ArrayList<>());
        map[code].push_back(str);
    }

    vector<vector<string>> ans;
    for (auto keys : map)
        ans.push_back(keys.second);

    return ans;
}

class RandomizedSet
{
public:
    unordered_map<int, int> map;
    vector<int> list;
    RandomizedSet()
    {
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    bool insert(int val)
    {
        if (map.find(val) != map.end())
            return false;

        list.push_back(val);
        map[val] = list.size() - 1;
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    bool remove(int val)
    {
        if (map.find(val) == map.end())
            return false;

        int index = map[val];
        swap(list[index], list[map.size() - 1]);
        map[list[index]] = index;

        map.erase(val);
        list.pop_back();
        return true;
    }

    /** Get a random element from the set. */
    int getRandom()
    {
        int loc = rand() % list.size();
        return list[loc];
    }
};

void solve()
{
}

int main()
{
    solve();
    return 0;
}
