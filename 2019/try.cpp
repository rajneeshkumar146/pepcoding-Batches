#include <iostream>
#include <queue>
#include <vector>
#include <string>
#include <algorithm>
#include <unordered_map>
#include <sstream>
#include <iomanip>
using namespace std;

bool comp(string &s1, string &s2)
{
    return true;
}

int main()
{
    // double pi = 3.14159265359;

    // stringstream stream;
    // stream << fixed << setprecision(5) << pi;
    // string s = stream.str();
    // cout<<s<<endl;

    vector<string> ar{"asd", "sadfsa", "sa", "sdfgsdg"};
    sort(ar.begin(), ar.end(), comp);
    for(string s: ar) cout<<s<<endl;

    return 0;
}