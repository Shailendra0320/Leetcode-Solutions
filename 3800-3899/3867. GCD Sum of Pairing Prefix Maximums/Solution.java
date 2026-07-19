//Approach-1 (Prefix GCD + Sorting)
//T.C : O(n log n)
//S.C : O(n)

import java.util.Arrays;

class Solution {

  public long gcdSum(int[] nums) {

    int n = nums.length;

    int[] prefixGcd = new int[n];

    int maximum = 0;

    for (int i = 0; i < n; i++) {

      maximum = Math.max(
          maximum,
          nums[i]);

      prefixGcd[i] = gcd(
          nums[i],
          maximum);
    }

    Arrays.sort(
        prefixGcd);

    long answer = 0;

    for (int i = 0; i < n / 2; i++) {

      answer += gcd(
          prefixGcd[i],
          prefixGcd[n - i - 1]);
    }

    return answer;
  }

  private int gcd(
      int a,
      int b) {

    while (b != 0) {

      int temp = a % b;

      a = b;

      b = temp;
    }

    return a;
  }
}

/*
 * //Approach-2 (Brute Force Simulation)
 * //T.C : O(n log n)
 * //S.C : O(n)
 * 
 * import java.util.*;
 * 
 * class Solution {
 * 
 * public long gcdSum(int[] nums) {
 * 
 * List<Integer> values =
 * new ArrayList<>();
 * 
 * int maximum = 0;
 * 
 * for (int value : nums) {
 * 
 * maximum =
 * Math.max(
 * maximum,
 * value
 * );
 * 
 * values.add(
 * gcd(
 * value,
 * maximum
 * )
 * );
 * }
 * 
 * Collections.sort(
 * values
 * );
 * 
 * long answer = 0;
 * 
 * int left = 0;
 * 
 * int right =
 * values.size() - 1;
 * 
 * while (left < right) {
 * 
 * answer +=
 * gcd(
 * values.get(left),
 * values.get(right)
 * );
 * 
 * left++;
 * 
 * right--;
 * }
 * 
 * return answer;
 * }
 * 
 * private int gcd(
 * int a,
 * int b
 * ) {
 * 
 * while (b != 0) {
 * 
 * int temp =
 * a % b;
 * 
 * a = b;
 * 
 * b = temp;
 * }
 * 
 * return a;
 * }
 * }
 */