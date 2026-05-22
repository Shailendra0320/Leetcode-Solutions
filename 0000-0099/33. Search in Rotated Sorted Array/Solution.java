class Solution {

    public int search(int[] nums, int target) {

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {

                return mid;
            }

            if (nums[low] <= nums[mid]) {

                if (nums[low] <= target && target < nums[mid]) {

                    high = mid - 1;
                }
                else {

                    low = mid + 1;
                }
            }
            else {

                if (nums[mid] < target && target <= nums[high]) {

                    low = mid + 1;
                }
                else {

                    high = mid - 1;
                }
            }
        }

        return -1;
    }
}


/*
class Solution {

    public int search(int[] nums, int target) {

        int n = nums.length;

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

    private int findPivot(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

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

    private int binarySearch(int[] nums, int left, int right, int target) {

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
}
*/


/*
class Solution {

    public int search(int[] nums, int target) {

        List<Integer> list = new ArrayList<>();

        for (int a : nums) {

            list.add(a);
        }

        return list.indexOf(target);
    }
}
*/