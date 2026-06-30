//Approach-1 (Frequency HashMap) - MAIN SOLUTION
//T.C : O(n log log M)
//S.C : O(n)

class Solution {

  public int maximumLength(int[] nums) {

    Map<Long, Integer> count = new HashMap<>();

    for (int num : nums) {

      count.put(
          (long) num,
          count.getOrDefault((long) num, 0) + 1);
    }

    int answer = 1;

    if (count.containsKey(1L)) {

      int ones = count.get(1L);

      answer = Math.max(
          answer,
          (ones % 2 == 1) ? ones : ones - 1);
    }

    for (long value : count.keySet()) {

      if (value == 1) {
        continue;
      }

      int length = 0;

      long current = value;

      while (count.containsKey(current)
          &&
          count.get(current) >= 2) {

        length += 2;

        current *= current;
      }

      if (count.containsKey(current)) {

        length++;

      } else {

        length--;
      }

      answer = Math.max(answer, length);
    }

    return answer;
  }
}

/*
 * //Approach-2 (Chain Simulation)
 * //T.C : O(n log log M)
 * //S.C : O(n)
 * 
 * import java.util.ArrayList;
 * import java.util.HashMap;
 * import java.util.HashSet;
 * import java.util.List;
 * import java.util.Map;
 * import java.util.Set;
 * 
 * class Solution {
 * 
 * public int maximumLength(int[] nums) {
 * 
 * Map<Long, Integer> frequency = new HashMap<>();
 * 
 * Set<Long> values = new HashSet<>();
 * 
 * for (int num : nums) {
 * 
 * long value = num;
 * 
 * frequency.put(
 * value,
 * frequency.getOrDefault(value, 0) + 1
 * );
 * 
 * values.add(value);
 * }
 * 
 * int answer = 1;
 * 
 * if (frequency.containsKey(1L)) {
 * 
 * int ones = frequency.get(1L);
 * 
 * answer = Math.max(
 * answer,
 * (ones % 2 == 1) ? ones : ones - 1
 * );
 * }
 * 
 * for (long start : values) {
 * 
 * if (start == 1) {
 * continue;
 * }
 * 
 * List<Long> chain =
 * new ArrayList<>();
 * 
 * long current = start;
 * 
 * while (values.contains(current)) {
 * 
 * chain.add(current);
 * 
 * current *= current;
 * }
 * 
 * int length = 0;
 * 
 * for (
 * int index = 0;
 * index < chain.size();
 * index++
 * ) {
 * 
 * long value =
 * chain.get(index);
 * 
 * if (
 * index < chain.size() - 1
 * ) {
 * 
 * if (
 * frequency.get(value) >= 2
 * ) {
 * 
 * length += 2;
 * 
 * } else {
 * 
 * break;
 * }
 * 
 * } else {
 * 
 * length++;
 * }
 * }
 * 
 * answer =
 * Math.max(answer, length);
 * }
 * 
 * return answer;
 * }
 * }
 * 
 * //Approach-3 (Greedy Frequency Expansion)
 * //T.C : O(n log log M)
 * //S.C : O(n)
 * 
 * class Solution {
 * 
 * public int maximumLength(int[] nums) {
 * 
 * Map<Long, Integer> count = new HashMap<>();
 * 
 * for (int num : nums) {
 * count.merge((long) num, 1, Integer::sum);
 * }
 * 
 * int oneCount = count.getOrDefault(1L, 0);
 * 
 * int answer =
 * (oneCount & 1) == 1
 * ? oneCount
 * : oneCount - 1;
 * 
 * count.remove(1L);
 * 
 * for (long start : count.keySet()) {
 * 
 * int length = 0;
 * 
 * long current = start;
 * 
 * while (
 * count.containsKey(current)
 * &&
 * count.get(current) > 1
 * ) {
 * 
 * length += 2;
 * 
 * current *= current;
 * }
 * 
 * answer = Math.max(
 * answer,
 * length +
 * (
 * count.containsKey(current)
 * ? 1
 * : -1
 * )
 * );
 * }
 * 
 * return answer;
 * }
 * }
 */