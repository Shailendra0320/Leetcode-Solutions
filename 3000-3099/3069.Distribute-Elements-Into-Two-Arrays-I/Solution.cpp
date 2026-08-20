// Approach-1 (Vector Simulation)
// T.C : O(n)
// S.C : O(n)

#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
  vector<int> resultArray(vector<int> &nums)
  {
    vector<int> arr1;
    vector<int> arr2;

    arr1.push_back(nums[0]);
    arr2.push_back(nums[1]);

    for (int i = 2; i < nums.size(); i++)
    {
      if (arr1.back() > arr2.back())
      {
        arr1.push_back(nums[i]);
      }
      else
      {
        arr2.push_back(nums[i]);
      }
    }

    vector<int> result;

    for (int num : arr1)
    {
      result.push_back(num);
    }

    for (int num : arr2)
    {
      result.push_back(num);
    }

    return result;
  }
};

// Approach-2 (Array Simulation)
// T.C : O(n)
// S.C : O(n)

#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
  vector<int> resultArray(vector<int> &nums)
  {
    int n = nums.size();

    vector<int> arr1(n);
    vector<int> arr2(n);

    int size1 = 0;
    int size2 = 0;

    arr1[size1++] = nums[0];
    arr2[size2++] = nums[1];

    for (int i = 2; i < n; i++)
    {
      if (arr1[size1 - 1] > arr2[size2 - 1])
      {
        arr1[size1++] = nums[i];
      }
      else
      {
        arr2[size2++] = nums[i];
      }
    }

    vector<int> result;
    result.reserve(n);

    for (int i = 0; i < size1; i++)
    {
      result.push_back(arr1[i]);
    }

    for (int i = 0; i < size2; i++)
    {
      result.push_back(arr2[i]);
    }

    return result;
  }
};