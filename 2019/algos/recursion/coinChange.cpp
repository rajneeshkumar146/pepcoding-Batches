/*Given an array of integers. and a target sum, find all possible pairs from the array which will give the desired target sum  */

using namespace std;
#include <iostream>
#include <vector>

// =====================NORMAL METHOD ====================

int NoRep_Combination_NormalMethod(vector<int> &arr, int i, int rem, string ans)
{ // Coin Change problem WITHOUT REPETITION to find all possible COMBINATION by normal recursive approach

    // base cases
    if (rem == 0)
    {
        cout << ans << endl;
        return 1;
    }
    if (i == arr.size())
        return 0;

    int count = 0;

    // Recursively calling  all elements in array ahead of presently used number
    for (int j = i; j < arr.size(); j++)
    {
        if (arr[j] <= rem)
        {
            count += NoRep_Combination_NormalMethod(arr, j + 1, rem - arr[j], ans + to_string(arr[j]));
        }
    }
    return count;
}

int NoRep_Permutation_NormalMethod(vector<int> &arr, vector<int> &visited, int rem, string ans)
{ // Coin change problem WITHOUT REPETITION to find all possible PERMUTATION  by normal recursive approach

    if (rem == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;

    for (int i = 0; i < arr.size(); i++)
    {
        if (visited[i] != 1 && arr[i] <= rem)
        {
            visited[i] = 1;
            count += NoRep_Permutation_NormalMethod(arr, visited, rem - arr[i], ans + to_string(arr[i]) + ' ');
            visited[i] = 0;
        }
    }
    return count;
}

int Rep_Combination_NormalMethod(vector<int> &arr, int i, int rem, string ans)
{ // Coin Change problem WITH REPITITION to find all possible COMBINATION by normal recursive approach

    if (rem == 0)
    {
        cout << ans << endl;
        return 1;
    }
    if (i == arr.size())
        return 0;

    int count = 0;

    for (int j = i; j < arr.size(); j++)
    {
        if (arr[j] <= rem)
            count += Rep_Combination_NormalMethod(arr, j, rem - arr[j], ans + to_string(arr[j]) + ' ');
    }
    return count;
}

int Rep_Permutation_NormalMethod(vector<int> &arr, int rem, string ans)
{ // Coin Change problem WITH REPITITION to find all possible COMBINATION by normal recursive approach

    if (rem == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;

    for (int i = 0; i < arr.size(); i++)
    {
        if (arr[i] <= rem)
            count += Rep_Permutation_NormalMethod(arr, rem - arr[i], ans + to_string(arr[i]) + ' ');
    }
    return count;
}

// =================== SUBSEQUENCE METHOD =========================

int NoRep_Combination_SubsequenceMethod(vector<int> &arr, int i, int rem, string ans)
{ // Coin Change problem WITHOUT REPITITION to find all possible COMBINATION by subsequence approach
    if (rem == 0)
    {
        cout << ans << endl;
        return 1;
    }

    if (i == arr.size())
        return 0;

    int count = 0;
    if (arr[i] <= rem)
    {
        count += NoRep_Combination_SubsequenceMethod(arr, i + 1, rem - arr[i], ans + to_string(arr[i]) + ' ');
    }
    count += NoRep_Combination_SubsequenceMethod(arr, i + 1, rem, ans);

    return count;
}

int NoRep_Permutation_SubsequenceMethod(vector<int> &arr, vector<int> &visited, int i, int rem, string ans)
{ // Coin Change problem WITHOUT REPITITION to find all possible PERMUTATION by subsequence approach

    if (rem == 0)
    {
        cout << ans << endl;
        return 1;
    }
    if (i == arr.size())
        return 0;

    int count = 0;

    if (visited[i] != 1 && arr[i] <= rem)
    {
        visited[i] = 1;
        count += NoRep_Permutation_SubsequenceMethod(arr, visited, 0, rem - arr[i], ans + to_string(arr[i]) + ' ');
        visited[i] = 0;
    }
    count += NoRep_Permutation_SubsequenceMethod(arr, visited, i + 1, rem, ans);

    return count;
}

int Rep_Combination_SubsequenceMethod(vector<int> &arr, int i, int rem, string ans)
{ // Coin Change problem WITH REPITITION to find all possible COMBINATION by Subsequence approach
    if (rem == 0)
    {
        cout << ans << endl;
        return 1;
    }
    if (i == arr.size())
        return 0;

    int count = 0;
    if (arr[i] <= rem)
        count += Rep_Combination_SubsequenceMethod(arr, i, rem - arr[i], ans + to_string(arr[i]) + ' ');

    count += Rep_Combination_SubsequenceMethod(arr, i + 1, rem, ans);

    return count;
}

int Rep_Permutation_SubsequenceMethod(vector<int> &arr, int i, int rem, string ans)
{ // Coin Change problem WITH REPITITION to find all possible PERMUTATION bySubsequence approach
    if (rem == 0)
    {
        cout << ans << endl;
        return 1;
    }
    if (i == arr.size())
        return 0;

    int count = 0;

    if (arr[i] <= rem)
        count += Rep_Permutation_SubsequenceMethod(arr, 0, rem - arr[i], ans + to_string(arr[i]) + ' ');

    count += Rep_Permutation_SubsequenceMethod(arr, i + 1, rem, ans);

    return count;
}

int main()
{
    vector<int> arr{2, 3, 5, 7};
    vector<int> visited{0, 0, 0, 0};
    cout << "NoRep_Combination_NormalMethod:" << endl
         << NoRep_Combination_NormalMethod(arr, 0, 10, "") << endl;
    cout << "NoRep_Permutation_NormalMethod:" << endl
         << NoRep_Permutation_NormalMethod(arr, visited, 10, "") << endl;
    cout << "Rep_Combination_NormalMethod:" << endl
         << Rep_Combination_NormalMethod(arr, 0, 10, "");
    cout << "Rep_Permutation_NormalMethod:" << endl
         << Rep_Permutation_NormalMethod(arr, 10, "");
    cout << "NoRep_Combination_SubsequenceMethod:" << endl
         << NoRep_Combination_SubsequenceMethod(arr, 0, 10, "") << endl;
    cout << "NoRep_Permutation_SubsequenceMethod:" << endl
         << NoRep_Permutation_SubsequenceMethod(arr, visited, 0, 10, "") << endl;
    cout << "Rep_Combination_SubsequenceMethod:" << endl
         << Rep_Combination_SubsequenceMethod(arr, 0, 10, "") << endl;
    cout << "Rep_Permutation_SubsequenceMethod:" << endl
         << Rep_Permutation_SubsequenceMethod(arr, 0, 10, "") << endl;
    return 0;
}