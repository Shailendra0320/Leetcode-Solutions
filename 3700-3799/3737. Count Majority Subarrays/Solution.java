//Approach-1 (Brute Force)
//T.C : O(n²)
//S.C : O(1)

class Solution {

  public int countMajoritySubarrays(int[] nums, int target) {

    int n = nums.length;

    int answer = 0;

    for (int start = 0; start < n; start++) {

      int targetCount = 0;

      for (int end = start; end < n; end++) {

        if (nums[end] == target) {
          targetCount++;
        }

        int length = end - start + 1;

        if (targetCount > length / 2) {
          answer++;
        }
      }
    }

    return answer;
  }
}

/*
 * //Approach-2 (Prefix Sum)
 * //T.C : O(n²)
 * //S.C : O(n)
 * 
 * class Solution {
 * 
 * public int countMajoritySubarrays(int[] nums, int target) {
 * 
 * int n = nums.length;
 * 
 * int[] prefix = new int[n + 1];
 * 
 * for (int i = 0; i < n; i++) {
 * 
 * if (nums[i] == target) {
 * prefix[i + 1] = prefix[i] + 1;
 * } else {
 * prefix[i + 1] = prefix[i] - 1;
 * }
 * }
 * 
 * int answer = 0;
 * 
 * for (int left = 0; left < n; left++) {
 * 
 * for (int right = left + 1; right <= n; right++) {
 * 
 * if (prefix[right] - prefix[left] > 0) {
 * answer++;
 * }
 * }
 * }
 * 
 * return answer;
 * }
 * }
 */

/*
 * //Approach-3 (Prefix Sum + Fenwick Tree)
 * //T.C : O(n log n)
 * //S.C : O(n)
 * 
 * class Solution {
 * 
 * class Fenwick {
 * 
 * int[] bit;
 * 
 * Fenwick(int n) {
 * bit = new int[n + 2];
 * }
 * 
 * void update(int idx) {
 * while (idx < bit.length) {
 * bit[idx]++;
 * idx += idx & -idx;
 * }
 * }
 * 
 * int query(int idx) {
 * int sum = 0;
 * while (idx > 0) {
 * sum += bit[idx];
 * idx -= idx & -idx;
 * }
 * return sum;
 * }
 * }
 * 
 * public int countMajoritySubarrays(int[] nums, int target) {
 * 
 * int n = nums.length;
 * 
 * int[] prefix = new int[n + 1];
 * 
 * for (int i = 0; i < n; i++) {
 * 
 * prefix[i + 1] =
 * prefix[i] + (nums[i] == target ? 1 : -1);
 * }
 * 
 * int[] sorted = prefix.clone();
 * Arrays.sort(sorted);
 * 
 * Map<Integer, Integer> compress = new HashMap<>();
 * 
 * int id = 1;
 * 
 * for (int value : sorted) {
 * 
 * if (!compress.containsKey(value)) {
 * compress.put(value, id++);
 * }
 * }
 * 
 * Fenwick tree = new Fenwick(id + 2);
 * 
 * long answer = 0;
 * 
 * for (int value : prefix) {
 * 
 * int index = compress.get(value);
 * 
 * answer += tree.query(index - 1);
 * 
 * tree.update(index);
 * }
 * 
 * return (int) answer;
 * }
 * }
 */