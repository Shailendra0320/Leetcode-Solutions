//Approach-1 (Sorting)
//T.C : O(n log n)
//S.C : O(1)   //Ignoring sorting space

import java.util.Arrays;

class Solution {

  public int maxProduct(
      int[] nums) {

    Arrays.sort(
        nums);

    int n = nums.length;

    return (nums[n - 1] - 1) *
        (nums[n - 2] - 1);
  }
}

/*
 * //Approach-2 (Single Pass)
 * //T.C : O(n)
 * //S.C : O(1)
 * 
 * class Solution {
 * 
 * public int maxProduct(
 * int[] nums
 * ) {
 * 
 * int max1 =
 * Integer.MIN_VALUE;
 * 
 * int max2 =
 * Integer.MIN_VALUE;
 * 
 * for (
 * int num : nums
 * ) {
 * 
 * if (
 * num > max1
 * ) {
 * 
 * max2 =
 * max1;
 * 
 * max1 =
 * num;
 * 
 * } else if (
 * num > max2
 * ) {
 * 
 * max2 =
 * num;
 * }
 * }
 * 
 * return
 * (max1 - 1) *
 * (max2 - 1);
 * }
 * }
 */