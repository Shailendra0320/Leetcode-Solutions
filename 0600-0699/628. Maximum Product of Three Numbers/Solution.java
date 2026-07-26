//Approach-1 (Sorting + Compare Two Candidate Products)
//T.C : O(n log n)
//S.C : O(1)   //Ignoring sorting space

import java.util.Arrays;

class Solution {

  public int maximumProduct(
      int[] nums) {

    Arrays.sort(
        nums);

    int n = nums.length;

    int case1 = nums[n - 1] *
        nums[n - 2] *
        nums[n - 3];

    int case2 = nums[0] *
        nums[1] *
        nums[n - 1];

    return Math.max(
        case1,
        case2);
  }
}

/*
 * //Approach-2 (Single Pass - Without Sorting)
 * //T.C : O(n)
 * //S.C : O(1)
 * 
 * class Solution {
 * 
 * public int maximumProduct(
 * int[] nums
 * ) {
 * 
 * int max1 = Integer.MIN_VALUE;
 * int max2 = Integer.MIN_VALUE;
 * int max3 = Integer.MIN_VALUE;
 * 
 * int min1 = Integer.MAX_VALUE;
 * int min2 = Integer.MAX_VALUE;
 * 
 * for (
 * int num : nums
 * ) {
 * 
 * if (
 * num > max1
 * ) {
 * 
 * max3 = max2;
 * max2 = max1;
 * max1 = num;
 * 
 * } else if (
 * num > max2
 * ) {
 * 
 * max3 = max2;
 * max2 = num;
 * 
 * } else if (
 * num > max3
 * ) {
 * 
 * max3 = num;
 * }
 * 
 * if (
 * num < min1
 * ) {
 * 
 * min2 = min1;
 * min1 = num;
 * 
 * } else if (
 * num < min2
 * ) {
 * 
 * min2 = num;
 * }
 * }
 * 
 * return Math.max(
 * max1 * max2 * max3,
 * min1 * min2 * max1
 * );
 * }
 * }
 */