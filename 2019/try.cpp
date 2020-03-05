#include <iostream>
#include <queue>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;


class pair_
{
public:
    int age;
    int wt;

    pair_(int age, int wt)
    {
        this->age = age;
        this->wt = wt;
    }

    bool operator < (pair_ const &p1) const
    {
        
        return this->age < p1.age;
    }
};

int main()
{
    vector<pair_> pq;
    
    pair_ p1(10, 10);
    pq.push_back(p1);

    pair_ p2(2, 16);
    pq.push_back(p2);

    pair_ p3(5, 560);
    pq.push_back(p3);

    pair_ p4(20, 2340);
    pq.push_back(p4);

    pair_ p5(-8, 340);
    pq.push_back(p5);

    sort(pq.begin(),pq.end());

    for(pair_ p:pq){
       cout << p.age << " " << p.wt<<endl;
    }

}