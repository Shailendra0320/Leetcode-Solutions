class Solution
{
public:
  int getCommon(vector<int> &nums1, vector<int> &nums2)
  {

    int i = 0;
    int j = 0;

    while (i < nums1.size() && j < nums2.size())
    {

      if (nums1[i] == nums2[j])
      {

        return nums1[i];
      }
      else if (nums1[i] < nums2[j])
      {

        i++;
      }
      else
      {

        j++;
      }
    }

    return -1;
  }
};

/*

class Solution {
public:

    int getCommon(vector<int>& nums1, vector<int>& nums2) {

        int n = nums1.size();

        int m = nums2.size();

        for (int i = 0; i < m; i++) {

            int low = 0;
            int high = n - 1;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                if (nums1[mid] == nums2[i]) {

                    return nums2[i];
                }
                else if (nums1[mid] < nums2[i]) {

                    low = mid + 1;
                }
                else {

                    high = mid - 1;
                }
            }
        }

        return -1;
    }
};
 */