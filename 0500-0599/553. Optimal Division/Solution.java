//Approach-1 (Greedy + String Construction)
//T.C : O(n)
//S.C : O(n)

class Solution {

  public String optimalDivision(int[] nums) {

    int n = nums.length;

    if (n == 1) {

      return String.valueOf(
          nums[0]);
    }

    if (n == 2) {

      return nums[0]
          + "/"
          + nums[1];
    }

    StringBuilder answer = new StringBuilder();

    answer.append(nums[0]);

    answer.append("/(");

    answer.append(nums[1]);

    for (int i = 2; i < n; i++) {

      answer.append("/");

      answer.append(nums[i]);
    }

    answer.append(")");

    return answer.toString();
  }
}

/*
 * //Approach-2 (Recursive DFS / Brute Force)
 * //T.C : Exponential
 * //S.C : O(n)
 * 
 * class Solution {
 * 
 * private static class Node {
 * 
 * double value;
 * 
 * String expression;
 * 
 * Node(
 * double value,
 * String expression
 * ) {
 * 
 * this.value = value;
 * 
 * this.expression = expression;
 * }
 * }
 * 
 * public String optimalDivision(int[] nums) {
 * 
 * return getMaximum(
 * nums,
 * 0,
 * nums.length - 1
 * ).expression;
 * }
 * 
 * private Node getMaximum(
 * int[] nums,
 * int left,
 * int right
 * ) {
 * 
 * if (left == right) {
 * 
 * return new Node(
 * nums[left],
 * String.valueOf(nums[left])
 * );
 * }
 * 
 * Node best =
 * new Node(
 * -1,
 * ""
 * );
 * 
 * for (
 * int mid = left;
 * mid < right;
 * mid++
 * ) {
 * 
 * Node leftPart =
 * getMaximum(
 * nums,
 * left,
 * mid
 * );
 * 
 * Node rightPart =
 * getMinimum(
 * nums,
 * mid + 1,
 * right
 * );
 * 
 * double value =
 * leftPart.value
 * /
 * rightPart.value;
 * 
 * if (value > best.value) {
 * 
 * String expression =
 * leftPart.expression
 * + "/";
 * 
 * if (mid + 1 < right) {
 * 
 * expression +=
 * "("
 * + rightPart.expression
 * + ")";
 * } else {
 * 
 * expression +=
 * rightPart.expression;
 * }
 * 
 * best =
 * new Node(
 * value,
 * expression
 * );
 * }
 * }
 * 
 * return best;
 * }
 * 
 * private Node getMinimum(
 * int[] nums,
 * int left,
 * int right
 * ) {
 * 
 * if (left == right) {
 * 
 * return new Node(
 * nums[left],
 * String.valueOf(nums[left])
 * );
 * }
 * 
 * Node best =
 * new Node(
 * Double.MAX_VALUE,
 * ""
 * );
 * 
 * for (
 * int mid = left;
 * mid < right;
 * mid++
 * ) {
 * 
 * Node leftPart =
 * getMinimum(
 * nums,
 * left,
 * mid
 * );
 * 
 * Node rightPart =
 * getMaximum(
 * nums,
 * mid + 1,
 * right
 * );
 * 
 * double value =
 * leftPart.value
 * /
 * rightPart.value;
 * 
 * if (value < best.value) {
 * 
 * String expression =
 * leftPart.expression
 * + "/";
 * 
 * if (mid + 1 < right) {
 * 
 * expression +=
 * "("
 * + rightPart.expression
 * + ")";
 * } else {
 * 
 * expression +=
 * rightPart.expression;
 * }
 * 
 * best =
 * new Node(
 * value,
 * expression
 * );
 * }
 * }
 * 
 * return best;
 * }
 * }
 */