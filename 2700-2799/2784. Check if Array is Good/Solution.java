class Solution {
  public boolean isGood(int[] nums) {
    int n = nums.length - 1;
    int[] freq = new int[n + 2];
    for (int num : nums) {
      if (num > n)
        return false;
      freq[num]++;
    }
    for (int i = 1; i < n; i++) {
      if (freq[i] != 1)
        return false;
    }
    return freq[n] == 2;
  }
}

/*
 * class Solution {
 * public boolean isGood(int[] nums) {
 * if (nums.length <= 1) return false;
 * Arrays.sort(nums);
 * if (nums[nums.length-1] != nums.length-1 || nums[nums.length-2] !=
 * nums.length-1) return false;
 * for (int i = 1; i < nums.length-1; i++) {
 * if (nums[i-1] != i) return false;
 * }
 * return true;
 * }
 * }
 */