#include <iostream>
#include <queue>
#include <unordered_map>
#include <vector>

using namespace std;

class FreqStack
{
private:
    class pair
    {
    public:
        int val, idx, freq;
        pair(int val, int idx, int freq)
        {
            this->val = val;
            this->freq = freq;
            this->idx = idx;
        }
    };

    struct comp_
    {
        bool operator()(const pair &a, const pair &b) const
        {
            if (a.freq != b.freq)
                return b.freq > a.freq;

            return b.idx > a.idx;
        }
    };

    unordered_map<int, int> map;
    priority_queue<pair, vector<pair>, comp_> pq;
    int idx = 0;

public:
    FreqStack()
    {
    }

    void push(int val)
    {
        map[val]++;
        pq.push(pair(val, idx++, map[val]));
    }

    int pop()
    {
        pair rp = pq.top();
        pq.pop();
        map[rp.val]--;
        if (map[rp.val] == 0)
            map.erase(rp.val);

        return rp.val;
    }
};
