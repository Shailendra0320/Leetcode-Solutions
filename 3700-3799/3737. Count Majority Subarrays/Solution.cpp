// Approach-1 (Brute Force)
// T.C : O(n²)
// S.C : O(1)

class Solution
{
public:
  int countMajoritySubarrays(
      vector<int> &nums,
      int target)
  {

    int n = nums.size();

    int answer = 0;

    for (int start = 0; start < n; start++)
    {

      int targetCount = 0;

      for (int end = start; end < n; end++)
      {

        if (nums[end] == target)
        {
          targetCount++;
        }

        int length = end - start + 1;

        if (targetCount > length / 2)
        {
          answer++;
        }
      }
    }

    return answer;
  }
};

/*
//Approach-2 (Prefix Sum)
//T.C : O(n²)
//S.C : O(n)

class Solution {
public:

    int countMajoritySubarrays(
        vector<int>& nums,
        int target
    ) {

        int n = nums.size();

        vector<int> prefix(n + 1, 0);

        for (int i = 0; i < n; i++) {

            if (nums[i] == target)
                prefix[i + 1] = prefix[i] + 1;
            else
                prefix[i + 1] = prefix[i] - 1;
        }

        int answer = 0;

        for (int left = 0; left < n; left++) {

            for (int right = left + 1; right <= n; right++) {

                if (prefix[right] - prefix[left] > 0)
                    answer++;
            }
        }

        return answer;
    }
};
*/

/*
//Approach-3 (Prefix Sum + Fenwick Tree)
//T.C : O(n log n)
//S.C : O(n)

class Solution {
public:

    struct Fenwick {

        vector<int> bit;

        Fenwick(int n) {
            bit.assign(n + 2, 0);
        }

        void update(int idx) {

            while (idx < bit.size()) {
                bit[idx]++;
                idx += idx & -idx;
            }
        }

        int query(int idx) {

            int sum = 0;

            while (idx > 0) {
                sum += bit[idx];
                idx -= idx & -idx;
            }

            return sum;
        }
    };

    int countMajoritySubarrays(
        vector<int>& nums,
        int target
    ) {

        int n = nums.size();

        vector<int> prefix(n + 1);

        for (int i = 0; i < n; i++) {

            prefix[i + 1] =
                prefix[i] +
                (nums[i] == target ? 1 : -1);
        }

        vector<int> values = prefix;

        sort(values.begin(), values.end());

        values.erase(
            unique(values.begin(), values.end()),
            values.end()
        );

        Fenwick tree(values.size() + 2);

        long long answer = 0;

        for (int value : prefix) {

            int index =
                lower_bound(
                    values.begin(),
                    values.end(),
                    value
                ) - values.begin() + 1;

            answer += tree.query(index - 1);

            tree.update(index);
        }

        return (int)answer;
    }
};
*/