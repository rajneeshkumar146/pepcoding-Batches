#include <iostream>
#include <queue>
#include <unordered_map>
#include <list>

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

//Leetcode 380.
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

//Leetcode 295
class MedianFinder
{
public:
    priority_queue<int> leftRegion;                             //MAXPQ
    priority_queue<int, vector<int>, greater<int>> rightRegion; //MINPQ

    MedianFinder()
    {
    }

    void addNum(int num)
    {

        // find suitable region.
        if (leftRegion.empty() || num < leftRegion.top())
            leftRegion.push(num);
        else
            rightRegion.push(num);

        // adjust boundary of left and right region.
        if (leftRegion.size() > rightRegion.size() + 1)
        {
            rightRegion.push(leftRegion.top());
            leftRegion.pop();
        }
        else if (rightRegion.size() > leftRegion.size())
        {
            leftRegion.push(rightRegion.top());
            rightRegion.pop();
        }
    }

    double findMedian()
    {
        if (leftRegion.size() == rightRegion.size())
            return leftRegion.size() == 0 ? 0 : 1.0 * (leftRegion.top() + rightRegion.top()) / 2;
        else
            return leftRegion.top();
    }
};

//Leetcode 407.

int trapRainWater(vector<vector<int>> &heightMap)
{
    if (heightMap.empty() || heightMap[0].empty())
        return 0;

    int n = heightMap.size();
    int m = heightMap[0].size();

    typedef pair<int, int> cell;                          // ( height,i*m+j )
    priority_queue<cell, vector<cell>, greater<cell>> pq; //MIN_PQ

    vector<vector<bool>> vis(n, vector<bool>(m, false));

    for (int i = 0; i < n; i++)
    {
        pq.push({heightMap[i][0], i * m});
        pq.push({heightMap[i][m - 1], i * m + m - 1});

        vis[i][0] = true;
        vis[i][m - 1] = true;
    }

    for (int j = 0; j < m; j++)
    {

        pq.push({heightMap[0][j], j});
        pq.push({heightMap[n - 1][j], (n - 1) * m + j});

        vis[0][j] = true;
        vis[n - 1][j] = true;
    }

    vector<vector<int>> dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int totalWater = 0, MaxTillNow = 0;

    while (pq.size() != 0)
    {
        cell rvtx = pq.top();
        pq.pop();

        int r = rvtx.second / m;
        int c = rvtx.second % m;
        MaxTillNow = max(MaxTillNow, rvtx.first);

        for (int d = 0; d < 4; d++)
        {
            int x = r + dir[d][0];
            int y = c + dir[d][1];

            if (x >= 0 && y >= 0 && x < n && y < m && !vis[x][y])
            {
                totalWater += max(0, MaxTillNow - heightMap[x][y]);
                vis[x][y] = true;
                pq.push(heightMap[x][y], x * m + y);
            }
        }
    }

    return totalWater;
}

//Leetcode 138.
class Solution
{
public:
    class Node
    {
    public:
        int val = 0;
        Node *next;
        Node *random;

        Node(int _val)
        {
            val = _val;
            next = NULL;
            random = NULL;
        }
    };

    unordered_map<Node *, Node *> map;
    Node *copyRandomList2(Node *head)
    {
        Node *curr = head;
        while (curr != nullptr)
        {
            Node *node = new Node(curr->val);
            map[curr] = node;
            curr = curr->next;
        }

        for (auto p : map)
        {
            if (map.find(p.first->next) != map.end())
                p.second->next = map[p.first->next];
            if (map.find(p.first->random) != map.end())
                p.second->random = map[p.first->random];
        }

        return map[head];
    }

    //Solution_02
    Node *copyRandomList(Node *head)
    {

        copyList(head);
        setRandomPointers(head);
        return ExtractNode(head);
    }

    //Step 01 : CopyList
    void copyList(Node *node)
    {
        Node *curr = node;
        while (curr != nullptr)
        {
            Node *forw = curr->next;
            Node *node = new Node(curr->val);

            curr->next = node;
            node->next = forw;

            curr = forw;
        }
    }

    //Step 02:set Random pointers
    void setRandomPointers(Node *node)
    {
        Node *curr = node;
        while (curr != nullptr)
        {
            if (curr->random != nullptr)
                curr->next->random = curr->random->next;
            curr = curr->next->next;
        }
    }

    //Step 03: Extract Your copyied list
    Node *ExtractNode(Node *node)
    {
        Node *curr = node;
        Node *NewList = new Node(-1);
        Node *curr2 = NewList;

        while (curr != nullptr)
        {
            Node *forw = curr->next->next;

            Node *copyNode = curr->next;
            curr->next = forw;
            curr2->next = copyNode;

            curr = forw;
            curr2 = copyNode;
        }

        return NewList->next;
    }
};

int swimInWater(vector<vector<int>> &grid)
{
    if (grid.empty() || grid[0].empty())
        return 0;

    typedef pair<int, int> cell; //{height,i*m+j}
    priority_queue<cell, vector<cell>, greater<cell>> pq;

    int n = grid.size();
    int m = grid[0].size();
    vector<vector<int>> dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    vector<vector<bool>> vis(n, vector<bool>(m, false));

    pq.push({grid[0][0], 0});
    vis[0][0] = true;

    int maxWait = 0;
    while (pq.size() != 0)
    {
        cell rvtx = pq.top();
        pq.pop();

        int height = rvtx.first;
        int x = rvtx.second / m;
        int y = rvtx.second % m;

        maxWait = max(maxWait, height);
        // if (x == n - 1 && y == m - 1)
        //     break;

        for (int d = 0; d < 4; d++)
        {
            int r = x + dir[d][0];
            int c = y + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c])
            {
                vis[r][c] = true;
                pq.push({grid[r][c], r * m + c});
                if (r == n - 1 && c == m - 1)
                    return max(maxWait, grid[r][c]);
            }
        }
    }
    return maxWait;
}

class LRUCache
{
    unordered_map<int, int> map; //{key,value}
    list<int> ll;                //{key}
    int size = 0;

public:
    LRUCache(int capacity)
    {
        this->size = capacity;
    }

    int get(int key)
    {
        if (map.find(key) == map.end())
            return -1;
        else
        {
            ll.remove(key);    // O(n)
            ll.push_back(key); //O(1)
            return map[key];   //O(1)
        }
    }

    void put(int key, int value)
    {
        if (map.find(key) != map.end())
        {
            ll.remove(key);    // O(n)
            ll.push_back(key); //O(1)
            map[key] = value;  //O(1)
        }
        else //O(1)
        {
            map[key] = value;
            
            if (ll.size() == size)
            {
                map.erase(ll.front());
                ll.pop_front();
            }
            
            ll.push_back(key);
        }
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
