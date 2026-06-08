//Approach-1 (Using Three Lists)
//T.C : O(n)
//S.C : O(n)

class Solution {

  public int[] pivotArray(int[] nums, int pivot) {

    List<Integer> smaller = new ArrayList<>();

    List<Integer> larger = new ArrayList<>();

    List<Integer> equal = new ArrayList<>();

    for (int num : nums) {

      if (num < pivot) {

        smaller.add(num);
      } else if (num > pivot) {

        larger.add(num);
      } else {

        equal.add(num);
      }
    }

    smaller.addAll(equal);

    smaller.addAll(larger);

    for (int i = 0; i < nums.length; i++) {

      nums[i] = smaller.get(i);
    }

    return nums;
  }
}

/*
 * //Approach-2 (Using Result Array)
 * //T.C : O(n)
 * //S.C : O(n)
 * 
 * class Solution {
 * 
 * public int[] pivotArray(int[] nums, int pivot) {
 * 
 * int n = nums.length;
 * 
 * int[] result = new int[n];
 * 
 * int index = 0;
 * 
 * for (int num : nums) {
 * 
 * if (num < pivot) {
 * 
 * result[index++] = num;
 * }
 * }
 * 
 * for (int num : nums) {
 * 
 * if (num == pivot) {
 * 
 * result[index++] = num;
 * }
 * }
 * 
 * for (int num : nums) {
 * 
 * if (num > pivot) {
 * 
 * result[index++] = num;
 * }
 * }
 * 
 * return result;
 * }
 * }
 */