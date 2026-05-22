class Solution
{
public:
  int search(vector<int> &nums, int target)
  {

    int low = 0;
    int high = nums.size() - 1;

    while (low <= high)
    {

      int mid = low + (high - low) / 2;

      if (nums[mid] == target)
      {

        return mid;
      }

      if (nums[low] <= nums[mid])
      {

        if (nums[low] <= target && target < nums[mid])
        {

          high = mid - 1;
        }
        else
        {

          low = mid + 1;
        }
      }
      else
      {

        if (nums[mid] < target && target <= nums[high])
        {

          low = mid + 1;
        }
        else
        {

          high = mid - 1;
        }
      }
    }

    return -1;
  }
};

/*
class Solution {
public:

    int search(vector<int>& nums, int target) {

        int n = nums.size();

        int pivot = findPivot(nums);

        if (nums[pivot] == target) {

            return pivot;
        }

        int idx = binarySearch(nums, pivot, n - 1, target);

        if (idx != -1) {

            return idx;
        }

        return binarySearch(nums, 0, pivot - 1, target);
    }

    int findPivot(vector<int>& nums) {

        int left = 0;
        int right = nums.size() - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {

                left = mid + 1;
            }
            else {

                right = mid;
            }
        }

        return left;
    }

    int binarySearch(vector<int>& nums, int left, int right, int target) {

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {

                return mid;
            }
            else if (nums[mid] < target) {

                left = mid + 1;
            }
            else {

                right = mid - 1;
            }
        }

        return -1;
    }
};
*/

/*
class Solution {
public:

    int search(vector<int>& nums, int target) {

        vector<int> list;

        for (int a : nums) {

            list.push_back(a);
        }

        for (int i = 0; i < list.size(); i++) {

            if (list[i] == target) {

                return i;
            }
        }

        return -1;
    }
};
*/