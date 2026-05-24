class Solution {
  public int sumFourDivisors(int[] nums) {
    int sum = 0;

    for (int i = 0; i < nums.length; i++) {
      sum = sum + getContributions(nums[i]);
    }
    return sum;
  }

  public int getContributions(int num) {
    int count = 0;
    int sum = 0;

    for (int i = 1; i * i <= num; i++) {
      if (num % i == 0) {
        if (i * i == num) {
          return 0;
        }
        count = count + 2;
        sum = sum + i;
        sum = sum + num / i;
      }

    }
    return count == 4 ? sum : 0;
  }
}