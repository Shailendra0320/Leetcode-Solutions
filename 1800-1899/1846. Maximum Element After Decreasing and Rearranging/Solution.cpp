#C++ Solution

##Approach 1 — Sorting + Greedy

```cpp
    // Approach-1 (Sorting + Greedy)
    // T.C : O(n log n)
    // S.C : O(1)

    class Solution
{
public:
  int maximumElementAfterDecrementingAndRearranging(vector<int> &arr)
  {

    sort(arr.begin(), arr.end());

    arr[0] = 1;

    for (int i = 1; i < arr.size(); i++)
    {

      arr[i] = min(
          arr[i],
          arr[i - 1] + 1);
    }

    return arr.back();
  }
};
```

    -- -

    ##Approach 2 — Counting Sort /
    Frequency Array

```cpp
    // Approach-2 (Counting Sort / Frequency Array)
    // T.C : O(n)
    // S.C : O(n)

    class Solution
{
public:
  int maximumElementAfterDecrementingAndRearranging(vector<int> &arr)
  {

    int n = arr.size();

    vector<int> frequency(n + 1, 0);

    for (int value : arr)
    {

      frequency[min(value, n)]++;
    }

    int current = 0;

    for (int value = 1; value <= n; value++)
    {

      if (frequency[value] == 0)
      {

        current = min(current, value - 1);
      }
      else
      {

        current = min(current + frequency[value], value);
      }
    }

    return current;
  }
};
```

    -- -

    ##Approach 3 — Sorting +
    Construct New Array

```cpp
    // Approach-3 (Sorting + Construct New Array)
    // T.C : O(n log n)
    // S.C : O(n)

    class Solution
{
public:
  int maximumElementAfterDecrementingAndRearranging(vector<int> &arr)
  {

    sort(arr.begin(), arr.end());

    int n = arr.size();

    vector<int> result(n);

    result[0] = 1;

    for (int i = 1; i < n; i++)
    {

      if (arr[i] >= result[i - 1] + 1)
      {

        result[i] = result[i - 1] + 1;
      }
      else
      {

        result[i] = arr[i];
      }
    }

    return result[n - 1];
  }
};
```