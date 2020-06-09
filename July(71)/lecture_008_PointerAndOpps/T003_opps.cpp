#include <iostream>
#include <string>
using namespace std;

class Student
{
public:
    int rollno = 2332;
    string Name;
    string Branch;

    Student(string Name, string Branch)
    {
        this->Name = Name;
        this->Branch = Branch;
    }
};

int main(int args, char **argv)
{
    // Student s("abc","def");
    // cout<<s.Name<<" "<<s.Branch<<endl;
    Student *s = new Student("abc", "def");
    cout << s->Name << " " << s->Branch << endl;
}