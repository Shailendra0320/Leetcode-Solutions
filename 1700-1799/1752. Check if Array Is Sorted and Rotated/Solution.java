class Solution {
  public boolean check(int[] numbers) {
    int length = numbers.length;

    int[] rotatedArray = new int[length];

    for (int rotation = 0; rotation < length; rotation++) {

      int currentIndex = 0;

      for (int rightPart = rotation; rightPart < length; rightPart++) {
        rotatedArray[currentIndex] = numbers[rightPart];
        currentIndex++;
      }

      for (int leftPart = 0; leftPart < rotation; leftPart++) {
        rotatedArray[currentIndex] = numbers[leftPart];
        currentIndex++;
      }

      boolean sortedCheck = true;

      for (int compareIndex = 0; compareIndex < length - 1; compareIndex++) {
        if (rotatedArray[compareIndex] > rotatedArray[compareIndex + 1]) {
          sortedCheck = false;
          break;
        }
      }

      if (sortedCheck) {
        return true;
      }
    }

    return false;
  }
}

/*
 * class Solution {
 * public boolean check(int[] nums) {
 * int n = nums.length;
 * 
 * int[] sorted = nums.clone();
 * Arrays.sort(sorted);
 * 
 * for (int r = 0; r < n; r++) {
 * boolean isSorted = true;
 * for (int i = 0; i < n; i++) {
 * if (sorted[i] != nums[(i + r) % n]) {
 * isSorted = false;
 * break;
 * }
 * }
 * 
 * if (isSorted) {
 * return true;
 * }
 * }
 * 
 * return false;
 * }
 * }
 */

/*
 * class Solution {
 * public boolean check(int[] nums) {
 * int n = nums.length;
 * 
 * int peak = 0;
 * for (int i = 0; i < n; i++) {
 * if (nums[i] > nums[(i + 1) % n]) {
 * peak++;
 * }
 * }
 * 
 * return peak <= 1;
 * }
 * }
 * 
 */