#include <iostream>
#include <queue>
#include <vector>
#include <string>
#include <algorithm>
#include <unordered_map>
#include <sstream>
#include <iomanip>  
using namespace std;

int main()
{
double pi = 3.14159265359;

stringstream stream;
stream << fixed << setprecision(5) << pi;
string s = stream.str();

cout<<s<<endl;
}