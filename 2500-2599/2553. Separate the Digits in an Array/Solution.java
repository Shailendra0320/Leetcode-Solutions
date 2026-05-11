class Solution {
  public int[] separateDigits(int[] nums) {
    String s = "";

    for (int i = 0; i < nums.length; i++) {
      s = s + nums[i];
    }

    int[] result = new int[s.length()];

    for (int i = 0; i < s.length(); i++) {
      result[i] = s.charAt(i) - '0';
    }

    return result;
  }
}