#include <iostream>
#include <vector>
#include <sstream>
using namespace std;

class students
{
public:
    string name = "";
    int marks = 0;

    students(string name, int marks)
    {
        this->name = name;
        this->marks = marks;
    }

    friend ostream &operator<<(ostream &out, const students &s);
};

ostream &operator<<(ostream &out, const students &s)
{
    out << "(" + s.name + "=" + to_string(s.marks) + ")";
    return out;
}

template <typename T>
ostream &operator<<(ostream &out, const vector<T> &arr)
{
    out << "[";
    for (int i = 0; i < arr.size(); i++)
    {
        out << arr[i];
        if (i != arr.size() - 1)
            out << ",";
    }

    out << "]";
    return out;
}

void fun1()
{
    vector<students> arr;
    string str = "Explanation: The above comparator function operator() class take two pair of objects at a time and return true if data members of the two operators are the same. There can be any condition as per the need of the problem in the comparator function. In the above example, the function returns true if data members are the same.'", word;
    int n = str.length();
    stringstream ss(str);
    vector<string> strArr;
    while (ss >> word)
        strArr.push_back(word);

    for (int i = 0; i <= 10; i++)
        arr.push_back(students(strArr[rand() % strArr.size()], rand()));

    cout << arr << endl;
}

int main()
{
    fun1();
    return 0;
}