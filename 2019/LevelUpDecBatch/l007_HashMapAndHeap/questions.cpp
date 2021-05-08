#include <iostream>
#include <queue>
#include <uonorderd_map>
using namespace std;

int findKthLargest(vector<int> &nums, int k)
{
    //215
    priority_queue<int, vector<int>, greater<int>> pq; // min
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
    priority_queue<int> pq; // max
    for (int ele : nums)
    {
        pq.push(ele);
        if (pq.size() > k)
            pq.pop();
    }

    return pq.top();
}

//703
class KthLargest
{
public:
    priority_queue<int, vector<int>, greater<int>> pq;
    int K = 0;

    KthLargest(int k, vector<int> &nums)
    {
        this->K = k;
        for (int ele : nums)
        {
            this->pq.push(ele);
            if (this->pq.size() > this->K)
                this->pq.pop();
        }
    }

    int add(int val)
    {
        this->pq.push(val);
        if (this->pq.size() > this->K)
            this->pq.pop();

        return this->pq.top();
    }
};

//350
vector<int> intersect(vector<int> &nums1, vector<int> &nums2)
{
    unordered_map<int, int> map;
    for (int ele : nums1)
        map[ele]++;

    vector<int> ans;
    for (int ele : nums2)
    {
        if (map.find(ele) != map.end())
        {
            ans.push_back(ele);
            map[ele]--;
            if (map[ele] == 0)
                map.erase(ele);
        }
    }

    return ans;
}

//347
vector<int> topKFrequent(vector<int> &nums, int k)
{

    unordered_map<int, int> map;
    for (int ele : nums)
        map[ele]++;

    //{freq,val}
    priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> pq;

    for (pair<int, int> key : map)
    {
        int val = key.first;
        int freq = key.second;

        pq.push({freq, val});
        if (pq.size() > k)
            pq.pop();
    }

    vector<int> ans;
    while (pq.size() != 0)
    {
        vector<int> p = pq.top();
        pq.pop();

        int freq = p[0];
        int val = p[1];

        ans.push_back(val);
    }

    return ans;
}

//973
class comp
{
public:
    bool operator()(const vector<int> &a, const vector<int> &b) const
    {
        int d1 = a[0] * a[0] + a[1] * a[1]; // x1^2 + y1^2
        int d2 = b[0] * b[0] + b[1] * b[1]; // x2^2 + y2^2

        return d2 > d1;
    }
};

vector<vector<int>> kClosest(vector<vector<int>> &points, int k)
{
    priority_queue<vector<int>, vector<vector<int>>, comp> pq;

    for (vector<int> &p : points)
    {
        pq.push(p);
        if (pq.size() > k)
            pq.pop();
    }

    vector<vector<int>> ans;
    while (pq.size() != 0)
    {
        ans.push_back(pq.top());
        pq.pop();
    }

    return ans;
}

vector<vector<int>> kClosest(vector<vector<int>> &points, int k)
{
    //{d,x,y}
    priority_queue<vector<int>> pq; // maxPQ

    for (vector<int> &p : points)
    {
        int x = p[0];
        int y = p[1];
        pq.push({x * x + y * y, x, y});
        if (pq.size() > k)
            pq.pop();
    }

    vector<vector<int>> ans;
    while (pq.size() != 0)
    {
        vector<int> p = pq.top();
        pq.pop();
        int x = p[1];
        int y = p[2];

        ans.push_back({x, y});
    }

    return ans;
}

int kthSmallest(vector<vector<int>> &matrix, int k)
{

    int n = matrix.size(), m = matrix[0].size();
    //{val,x,y};
    priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> pq;

    for (int i = 0; i < n; i++)
    {
        pq.push({matrix[i][0], i, 0});
    }

    int ans = 0;
    while (k-- > 0)
    {
        vector<int> rv = pq.top();
        pq.pop();
        int val = rv[0];
        int x = rv[1];
        int y = rv[2];

        ans = val;
        y++;
        if (y < m)
            pq.push({matrix[x][y], x, y});
    }

    return ans;
}

//380
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

        int idx = map[val];
        int lidx = list.size() - 1;

        list[idx] = list[lidx];
        map[list[lidx]] = idx;

        list.pop_back();
        map.erase(val);

        return true;
    }

    /** Get a random element from the set. */
    int getRandom()
    {
        int idx = rand() % list.size();
        return list[idx];
    }
};

class FreqStack
{
private:
    class pair
    {
    public:
        int val = 0;
        int freq = 0;
        int idx = 0;

        pair(int val, int freq, int idx)
        {
            this->val = val;
            this->freq = freq;
            this->idx = idx;
        }
    };

    class comp
    {
    public:
        bool operator()(const pair &a, const pair &b) const
        {
            if (a.freq == b.freq)
                return b.idx > a.idx;
            return b.freq > a.freq;
        }
    };

    unordered_map<int, int> freqMap;
    priority_queue<pair, vector<pair>, comp> pq;
    int idx = 0;

public:
    FreqStack()
    {
    }

    void push(int val)
    { // Log(n)
        freqMap[val]++;
        pq.push(pair(val, freqMap[val], idx++));
    }

    int pop()
    { // Log(n)
        pair p = pq.top();
        pq.pop();
        freqMap[p.val]--;
        if (freqMap[p.val] == 0)
            freqMap.erase(p.val);

        return p.val;
    }
};

// Trapping Rain Water II
int trapRainWater(vector<vector<int>> &heightMap)
{
    int n = heightMap.size(), m = heightMap[0].size();
    //{height, i,j}
    priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> pq;

    vector<vector<bool>> vis(n, vector<bool>(m, false));
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (i == 0 || j == 0 || i == n - 1 || j == m - 1)
            {
                vis[i][j] = true;
                pq.push({heightMap[i][j], i, j});
            }
        }
    }

    int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int minBoun = 0, water = 0;

    while (pq.size() != 0)
    {
        vector<int> rp = pq.top(), pq.pop();
        int height = rp[0];
        int i = rp[1], j = rp[2];

        minBoun = Math.max(minBoun, height);
        water += minBoun - height;

        for (int d = 0; d < 4; d++)
        {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && !vis[r][c])
            {
                vis[r][c] = true;
                pq.push({heightMap[r][c], r, c});
            }
        }
    }
    return water;
}

class comp
{
public:
    bool operator()(const ListNode *a, const ListNode *b) const
    {
        return a->val > b->val;
    }
};

ListNode *mergeKLists(vector<ListNode *> lists)
{
    priority_queue<ListNode *, vector<ListNode *>, comp> pq;

    for (int i = 0; i < lists.size(); i++)
        if (lists[i] != nullptr)
            pq.push(lists[i]);

    ListNode *dummy = new ListNode(-1);
    ListNode *prev = dummy;

    while (pq.size() != 0)
    {
        ListNode *rn = pq.top();
        pq.pop();
        prev->next = rn;
        prev = prev->next;

        if (rn->next != nullptr)
            pq.push(rn->next);
    }
    return dummy->next;
}
