//Approach-1 (Recursion + Memoization)
//T.C : O(n * (maxJump - minJump))
//S.C : O(n)

class Solution {

  int n;

  int[] t;

  int solve(int idx, String s, int minJump, int maxJump) {

    if (idx == n - 1) {

      return 1;
    }

    if (t[idx] != -1) {

      return t[idx];
    }

    for (int jump = minJump; jump <= maxJump; jump++) {

      int j = idx + jump;

      if (j >= n) {

        break;
      }

      if (s.charAt(j) == '0') {

        if (solve(j, s, minJump, maxJump) == 1) {

          return t[idx] = 1;
        }
      }
    }

    return t[idx] = 0;
  }

  public boolean canReach(String s, int minJump, int maxJump) {

    n = s.length();

    t = new int[n];

    Arrays.fill(t, -1);

    return solve(0, s, minJump, maxJump) == 1;
  }
}

/*
 * //Approach-2 (Bottom Up DP)
 * //T.C : O(n * (maxJump - minJump))
 * //S.C : O(n)
 * 
 * class Solution {
 * 
 * public boolean canReach(String s, int minJump, int maxJump) {
 * 
 * int n = s.length();
 * 
 * boolean[] t = new boolean[n];
 * 
 * t[n - 1] = true;
 * 
 * for (int i = n - 2; i >= 0; i--) {
 * 
 * for (int jump = minJump; jump <= maxJump; jump++) {
 * 
 * int j = i + jump;
 * 
 * if (j >= n) {
 * 
 * break;
 * }
 * 
 * if (s.charAt(j) == '0') {
 * 
 * if (t[j]) {
 * 
 * t[i] = true;
 * 
 * break;
 * }
 * }
 * }
 * }
 * 
 * return t[0];
 * }
 * }
 */

/*
 * //Approach-3 (Sliding Window + Bottom Up)
 * //T.C : O(n)
 * //S.C : O(n)
 * 
 * class Solution {
 * 
 * public boolean canReach(String s, int minJump, int maxJump) {
 * 
 * int n = s.length();
 * 
 * int[] t = new int[n];
 * 
 * t[0] = 1;
 * 
 * int count = 0;
 * 
 * for (int j = 1; j <= n - 1; j++) {
 * 
 * if (j - minJump >= 0) {
 * 
 * count += t[j - minJump];
 * }
 * 
 * if (j - maxJump - 1 >= 0) {
 * 
 * count -= t[j - maxJump - 1];
 * }
 * 
 * if (count > 0 && s.charAt(j) == '0') {
 * 
 * t[j] = 1;
 * }
 * }
 * 
 * return t[n - 1] > 0;
 * }
 * }
 */