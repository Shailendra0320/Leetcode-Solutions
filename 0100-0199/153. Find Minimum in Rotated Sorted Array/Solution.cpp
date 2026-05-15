class Solution
{
public:
  int findMin(vector<int> &nums)
  {

    priority_queue<int, vector<int>, greater<int>> pq;

    for (int num : nums)
    {
      pq.push(num);
    }

    return pq.top();
  }
};
/*
class Solution {
public:

    int findMin(vector<int>& nums) {

        int low = 0;
        int high = nums.size() - 1;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[high]) {

                low = mid + 1;
            }
            else {

                high = mid;
            }
        }

        return nums[high];
    }
};
*/