#include <iostream>
#include <vector>

using namespace std;

int *input1d(int n)
{
    int *arr = new int[n];
    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
    }

    return arr;
}

void output1d(int *arr, int n)
{
    for (int i = 0; i < n; i++)
    {
        cout << arr[i] << " ";
    }
}

void test2(int *arr, int n)
{
    for (int i = 0; i < n; i++)
    {
        cout << arr[i] << endl;
    }
}

void inputForStack(int n)
{
    int arr[n] = {0};
    for (int i = 0; i < n; i++)
    {

        cin >> arr[i];
    }
}

void test1()
{
    int n;
    cin >> n;

    int *a = new int(10);
    // int arr[n] = {0};
    // test2(arr, n);

    inputForStack(n);
}

void test2()
{
    int n;
    cin >> n;
    int *arr = input1d(n);
    output1d(arr, n);
}

// for 2d Arrays.
void test3()
{
    int n, m;
    cin >> n >> m;

    int **arr = new int *[n];
    for (int i = 0; i < n; i++)
    {
        int *ar = new int[m];
        arr[i] = ar;
    }

    int ***arrr = new int **[3];
    for (int i = 0; i < 3; i++)
    {
        arrr[i] = new int *[5];
        for (int j = 0; j < 5; j++)
        {
            arrr[i][j] = new int[7];
            for (int k = 0; k < 7; k++)
                arrr[i][j][k] = 0;
        }
    }
}

//vector  =============================================

void inputVector(vector<int> *arr)
{
    for (int i = 0; i < arr->size(); i++)
        cin >> arr->at(i); 
    for (int i = 0; i < arr->size(); i++)
        cout << arr->at(i) << " ";
    cout << endl;
}

void test4()
{
    vector<int> *arr = new vector<int>(10, 0);
    inputVector(arr);
    for (int i = 0; i < arr->size(); i++)
        cout << arr->at(i) << " ";
}

// & -======================================================================


int main()
{
    test4();
}
