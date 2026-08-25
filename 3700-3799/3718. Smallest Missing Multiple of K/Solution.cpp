// Approach-1 (Boolean Array)
// T.C : O(n)
// S.C : O(1)

class Solution
{
public:
  int missingMultiple(
      vector<int> &nums,
      int k)
  {

    bool present[101] = {};

    for (int num : nums)
    {
      present[num] = true;
    }

    for (int i = 1;; i++)
    {

      int multiple =
          i * k;

      if (
          multiple >= 101 ||
          !present[multiple])
      {
        return multiple;
      }
    }
  }
};

// Approach-2 (HashSet)
// T.C : O(n)
// S.C : O(n)

class Solution
{
public:
  int missingMultiple(
      vector<int> &nums,
      int k)
  {

    unordered_set<int> st;

    for (int num : nums)
    {
      st.insert(num);
    }

    for (int i = 1;; i++)
    {

      int multiple =
          i * k;

      if (!st.count(multiple))
      {
        return multiple;
      }
    }
  }
};